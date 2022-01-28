package sk.itsovy.android.voting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CommunicationViewModel : ViewModel() {

    fun sendVote(name: String, choice: String) {
        viewModelScope.launch {
            RestApi.REST_SERVICE.sendVote(name, choice)
        }
    }

}