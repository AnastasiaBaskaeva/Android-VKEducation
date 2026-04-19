package com.android.vkeducation.baskaeva.presentation.appdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.android.vkeducation.baskaeva.domain.appdetails.GetAppDetailsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val appId: String = checkNotNull(savedStateHandle["appId"])

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadAppDetails()
        observeAppDetails()
    }

    fun loadAppDetails() {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            try {
                getAppDetailsUseCase(appId)
                // стейт не обновляем — это сделает observeAppDetails()
            } catch (e: Exception) {
                _state.value = AppDetailsState.Error
                Log.d("HOHOHO", "ERROR $e")
            }
        }
    }

    // Подписка на поток из БД — обновляет UI при любом изменении
    private fun observeAppDetails() {
        viewModelScope.launch {
            getAppDetailsUseCase.observe(appId)
                .catch { _state.value = AppDetailsState.Error }
                .collect { appDetails ->
                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        descriptionCollapsed = false,
                    )
                }
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            getAppDetailsUseCase.toggleWishlist(appId)
            // стейт не трогаем — Flow из БД сам обновит UI
        }
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else currentState
        }
    }
}