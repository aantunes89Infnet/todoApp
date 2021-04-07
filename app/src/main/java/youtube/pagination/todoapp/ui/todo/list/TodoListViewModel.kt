package youtube.pagination.todoapp.ui.todo.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import youtube.pagination.todoapp.database.TodoFireStore
import youtube.pagination.todoapp.model.Todo

class TodoListViewModel : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun refreshList() {
        TodoFireStore.all()
            .addSnapshotListener { snapshot, error ->
                if(error != null) {
                    _msg.value = "Algo de errado aconteceu"
                }

                if (snapshot != null && !snapshot.isEmpty && error == null) {
                    Log.i("ola", "ola")
                    _todos.value = snapshot.toObjects(Todo::class.java)
                }
            }
    }

}