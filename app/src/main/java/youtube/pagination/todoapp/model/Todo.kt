package youtube.pagination.todoapp.model

import com.google.firebase.firestore.DocumentId

class Todo(
    var description: String? = null,
    var createdBy: String? = "John Doe",
    var checked: Boolean? = false,
    val reference: String? = null,

    @DocumentId
    var userId: String? = null
) {
    override fun toString(): String {
        return "$description"
    }
}
