package youtube.pagination.todoapp.ui.todo.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.todo_form_fragment.*
import youtube.pagination.todoapp.R
import youtube.pagination.todoapp.database.TodoUtils
import youtube.pagination.todoapp.model.Todo

class TodoFormFragment : Fragment() {

    private lateinit var todoFormViewModel: TodoFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        todoFormViewModel = ViewModelProvider(this).get(TodoFormViewModel::class.java)

        todoFormViewModel.let {
            it.selectedTodo.observe(viewLifecycleOwner, {
                if (it != null) {
                    loadTodo(it)
                }
            })

            it.status.observe(viewLifecycleOwner, { status ->
                if (status) {
                    findNavController().navigate(R.id.todoListFragment)
                }
            })

            it.msg.observe(viewLifecycleOwner, { msg ->
                showToaster(msg)
            })
        }

        return inflater.inflate(R.layout.todo_form_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(TodoUtils.selectedTodo != null) {
            todoFormViewModel.selectedTodo(TodoUtils.selectedTodo!!)
        }

        delete_btn.setOnClickListener {
            if (TodoUtils.selectedTodo != null)
                todoFormViewModel.delete(TodoUtils.selectedTodo!!)
        }

        save_btn.setOnClickListener {
            val description = et_description.text.toString()
            val createdBy = et_created_by.text.toString()
            val checked = cb_done.isChecked

            if (!description.isNullOrBlank() &&
                !createdBy.isNullOrBlank()){

                    todoFormViewModel.addOrUpdate(description, createdBy, checked)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TodoUtils.selectedTodo = null
        todoFormViewModel.selectedTodo(null)
    }

    private fun loadTodo(todo: Todo) {
        if (todo != null) {
            et_description.setText(todo.description)
            et_created_by.setText(todo.createdBy)
            cb_done.setChecked(todo.checked!!)

            delete_btn.visibility = View.VISIBLE
        } else {
            delete_btn.visibility = View.GONE
        }
    }

    private fun showToaster(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}