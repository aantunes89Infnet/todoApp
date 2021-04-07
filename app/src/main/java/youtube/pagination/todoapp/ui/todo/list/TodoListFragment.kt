package youtube.pagination.todoapp.ui.todo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.todo_list_fragment.*
import youtube.pagination.todoapp.R
import youtube.pagination.todoapp.adapter.RecyclerTodoList
import youtube.pagination.todoapp.database.TodoUtils
import youtube.pagination.todoapp.database.UserFireBaseDao

class TodoListFragment : Fragment() {

    private lateinit var todoListViewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        todoListViewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        todoListViewModel.todos.observe(viewLifecycleOwner) { todos ->
            rv_todo_list.adapter = RecyclerTodoList(todos, actionClick = { todo ->
                TodoUtils.selectedTodo = todo
                findNavController().navigate(R.id.todoFormFragment)
            })

            rv_todo_list.layoutManager = LinearLayoutManager(requireContext())
        }

        todoListViewModel.refreshList()

        return inflater.inflate(R.layout.todo_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        new_todo_btn.setOnClickListener {
            findNavController().navigate(R.id.todoFormFragment)
        }

        logout_btn.setOnClickListener {
            UserFireBaseDao.logout()
            findNavController().navigate(R.id.loginFragment)
        }
    }

}