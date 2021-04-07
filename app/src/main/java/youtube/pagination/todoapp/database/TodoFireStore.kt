package youtube.pagination.todoapp.database

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import youtube.pagination.todoapp.model.Todo

object TodoFireStore: TodoDao {

    private val collection =
        FirebaseFirestore
            .getInstance()
            .collection("todos")


    override fun createOrUpdate(todo: Todo): Task<Void> {
            return collection
                .document(todo.description!!)
                .set(todo)
    }

    override fun all(): CollectionReference {
        return collection
    }

    override fun delete(todo: Todo): Task<Void> {
        return collection
            .document(todo.description!!)
            .delete()
    }
}