package com.android.vkeducation.baskaeva.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.vkeducation.baskaeva.domain.applist.GetAppListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getAppListUseCase: GetAppListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        AppListState()
    )
    val state: StateFlow<AppListState> = _state

    private val _events = Channel<AppListEvent>()
    val events = _events.receiveAsFlow()

    init{
        loadAppList()
    }

    private fun loadAppList(){
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try{
                val apps = getAppListUseCase()
                _state.value = _state.value.copy(apps = apps, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun onLogoClick() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowSnackbar("Бебебе с бабаба"))
        }
    }
}