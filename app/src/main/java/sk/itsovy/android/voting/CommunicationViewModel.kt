package sk.itsovy.android.voting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CommunicationViewModel : ViewModel() {

    val votes : MutableLiveData<List<Vote>> = MutableLiveData(emptyList())

    init {
        getVotes()
    }

    fun sendVote(name: String, choice: String) {
        viewModelScope.launch {
            RestApi.REST_SERVICE.sendVote(name, choice)
        }
    }

    fun getVotes() {
        viewModelScope.launch {
            val votesResult = RestApi.REST_SERVICE.getVotes()
            votes.postValue(votesResult)
        }
    }

}