package youtube.pagination.todoapp.ui.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import youtube.pagination.todoapp.database.UserFireBaseDao

class RegisterViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status : LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        _status.value = false
    }


    fun registerUser(email: String, password: String, name: String) {

        val task = UserFireBaseDao.registerCredentials(email, password)

        task.addOnSuccessListener {
            _status.value = true
            _msg.value = "Usuário cadastrado com sucesso!"
        }

        task.addOnFailureListener {
            _status.value = false
            _msg.value = it.message
        }
    }

}