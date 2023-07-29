package ir.miare.challenge.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.miare.core.data.extension.toFakeDataDelegatedItems
import ir.miare.challenge.ui.bottomsheet.PlayerInfoBottomSheet

import ir.miare.core.data.source.entity.fake.PlayerEntity

import ir.miare.core.util.MainCompositeAdapter
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.challenge.R
import ir.miare.challenge.databinding.FragmentRankingBinding
import ir.miare.challenge.ui.adapter.LeagueAdapter
import ir.miare.challenge.ui.adapter.PlayerAdapter
import ir.miare.core.data.ResponseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RankingFragment() : Fragment() {

    private val TAG = RankingFragment::class.java.simpleName

    private val viewModel: RankingFragmentVm by viewModels()
    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!

    private val rankAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(LeagueAdapter())
            .add(PlayerAdapter(onItemClick = ::playerInfoClicked))
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        _binding = FragmentRankingBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        init()
        initObserver()
    }

    private fun initObserver() {
        Log.d(TAG, "initObserver: ")
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userdata.collect {
                    when (it) {
                        is ResponseState.Success -> {
                            Log.d(TAG, "initObserver:  success")
                            binding.pbLoading.hide()
                            changeRadioButtonsClickableStatus(true)
                            it.data?.let { list ->
                                rankAdapter.submitList(list.toFakeDataDelegatedItems())
                            }
                        }

                        is ResponseState.Loading -> {
                            Log.d(TAG, "initObserver:  loading")
                            binding.pbLoading.show()
                        }

                        is ResponseState.Error -> {
                            Log.d(TAG, "initObserver:  failed")
                            binding.pbLoading.hide()
                        }
                    }
                }
            }
        }
    }

    private fun init() {
        Log.d(TAG, "init: ")

        changeRadioButtonsClickableStatus(false)

        viewModel.getDataFromRemote()

        binding.recycler.apply {
            adapter = rankAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        initRadioGroupListener()

    }

    private fun initRadioGroupListener() {

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {

                R.id.radio_team -> rankAdapter.submitList(viewModel.getTeamAndLeagueSortData())

                R.id.radio_most -> rankAdapter.submitList(viewModel.getMostGoalsScoredByAPlayerSortData())

                R.id.radio_average -> rankAdapter.submitList(viewModel.getAverageGoalsPerMatchSortData())

                R.id.radio_none -> rankAdapter.submitList(viewModel.getFakeData())

            }

            lifecycleScope.launch{
                delay(200)
                binding.recycler.scrollToPosition(0)
            }

        }

    }

    private fun changeRadioButtonsClickableStatus(clickable: Boolean) {
        binding.apply {
            radioTeam.isClickable = clickable
            radioTeam.isEnabled = clickable

            radioMost.isClickable = clickable
            radioMost.isEnabled = clickable

            radioAverage.isClickable = clickable
            radioAverage.isEnabled = clickable

            radioNone.isClickable = clickable
            radioNone.isEnabled = clickable
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    private fun playerInfoClicked(item: PlayerEntity){
        PlayerInfoBottomSheet(item).show(
            childFragmentManager,
            PlayerInfoBottomSheet::class.java.simpleName
        )

    }
}