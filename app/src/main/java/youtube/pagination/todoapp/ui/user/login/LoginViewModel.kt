package youtube.pagination.todoapp.ui.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import youtube.pagination.todoapp.database.UserFireBaseDao

class LoginViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status : LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        _status.value = false
    }

    fun tryToLogin(email: String, password: String) {

        val task = UserFireBaseDao.verifyCredentials(email, password)

        task.addOnSuccessListener {
            _status.value = true
            _msg.value = "Usuário logado com sucesso!"
        }

        task.addOnFailureListener {
            _status.value = false
            _msg.value = it.message
        }
    }

}