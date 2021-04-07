package youtube.pagination.todoapp.ui.todo.form

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import youtube.pagination.todoapp.database.TodoFireStore
import youtube.pagination.todoapp.model.Todo

class TodoFormViewModel : ViewModel() {

    private val _selectedTodo =  MutableLiveData<Todo>()
    var selectedTodo: LiveData<Todo> = _selectedTodo

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _status = MutableLiveData<Boolean>()
    val status : LiveData<Boolean> = _status


    fun addOrUpdate(description: String, createdBy: String, checked: Boolean?) {
        val todo = Todo(description, createdBy, checked)

        TodoFireStore.createOrUpdate(todo)
            .addOnSuccessListener {
                _msg.value = "Task added with success!!"
                _status.value = true
            }.addOnFailureListener {
                _msg.value = it.message
                _status.value = false
            }
    }

    fun delete(todo: Todo) {
        TodoFireStore.delete(todo)
            .addOnSuccessListener {
                _msg.value = "Task deleted with success!!"
                _status.value = true
            }.addOnFailureListener {
                _msg.value = it.message
                _status.value = false
            }
    }

    fun selectedTodo(todo: Todo?) {
        if (todo != null)
            _selectedTodo.value = todo!!
    }

}