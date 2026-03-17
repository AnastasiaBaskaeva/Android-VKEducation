import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.vkeducation.baskaeva.presentation.applist.AppListEvent
import com.android.vkeducation.baskaeva.presentation.applist.AppListState
import com.android.vkeducation.baskaeva.presentation.applist.hardcodedAppList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        AppListState(apps = hardcodedAppList)
    )
    val state: StateFlow<AppListState> = _state

    private val _events = Channel<AppListEvent>()
    val events = _events.receiveAsFlow()

    fun onLogoClick() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowSnackbar("Бебебе с бабаба"))
        }
    }
}