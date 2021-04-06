package youtube.pagination.todoapp.database

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object UserFireBaseDao {

    val auth = FirebaseAuth.getInstance()

     private val collection =
         FirebaseFirestore
            .getInstance()
            .collection("users")

    fun registerCredentials(email: String, password: String): Task<AuthResult> {
        return auth
            .createUserWithEmailAndPassword(email, password)
    }

    fun verifyCredentials(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun logout() {
        auth.signOut()
    }
}