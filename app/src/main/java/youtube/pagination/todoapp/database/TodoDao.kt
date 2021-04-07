package youtube.pagination.todoapp.database

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import youtube.pagination.todoapp.model.Todo

interface TodoDao {
    fun createOrUpdate(todo: Todo): Task<Void>
    fun all(): CollectionReference
    fun delete(todo: Todo): Task<Void>
}