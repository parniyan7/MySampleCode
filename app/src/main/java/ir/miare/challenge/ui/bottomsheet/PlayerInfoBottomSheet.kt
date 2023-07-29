package ir.miare.challenge.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.miare.challenge.R
import ir.miare.challenge.databinding.BottomSheetPlayerInfoBinding
import ir.miare.core.data.source.entity.fake.PlayerEntity

class PlayerInfoBottomSheet(
    private val player: PlayerEntity
) : BottomSheetDialogFragment() {

    private var binding: BottomSheetPlayerInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_player_info, container, false)
        binding = BottomSheetPlayerInfoBinding.bind(view)

        binding!!.tvPlayerName.text = player.name
        binding!!.tvTeamName.text = player.team.name
        binding!!.tvTotalGoals.text = player.totalGoal.toString()

        binding!!.btnBack.setOnClickListener {
            dismiss()
        }

        return view
    }
}
