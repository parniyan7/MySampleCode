package ir.miare.challenge.ui.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.miare.core.data.ResponseState
import ir.miare.core.data.source.entity.fake.FakeDataEntity
import ir.miare.core.domain.FakeUseCase
import ir.miare.core.util.DelegateAdapterItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RankingFragmentVm @Inject constructor(
    private val fakeUseCase: FakeUseCase
) : ViewModel() {


    private val TAG = RankingFragmentVm::class.java.simpleName

    private val _userData = MutableSharedFlow<ResponseState<List<FakeDataEntity>>>()
    val userdata: SharedFlow<ResponseState<List<FakeDataEntity>>> = _userData.asSharedFlow()


    fun getDataFromRemote() {
        Log.d(TAG, "getUsers: ")
        viewModelScope.launch {
            fakeUseCase.getAllDataFromRemote().collect {
                _userData.emit(it)
                Log.i(TAG, "getUsers: emitted : ${it.data}")
            }
        }
    }


    fun getFakeData(): List<DelegateAdapterItem> = fakeUseCase.getFakeData()

    fun getTeamAndLeagueSortData(): List<DelegateAdapterItem> = fakeUseCase.getTeamAndLeagueSortData()

    fun getMostGoalsScoredByAPlayerSortData(): List<DelegateAdapterItem> = fakeUseCase.getMostGoalsScoredByAPlayerSortData()

    fun getAverageGoalsPerMatchSortData(): List<DelegateAdapterItem> = fakeUseCase.getAverageGoalsPerMatchSortData()



}