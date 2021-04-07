package youtube.pagination.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_todo_list.view.*
import kotlinx.android.synthetic.main.todo_form_fragment.view.*
import youtube.pagination.todoapp.R
import youtube.pagination.todoapp.model.Todo

class RecyclerTodoList(
    private val todoList: List<Todo>,
    val actionClick: (todo: Todo) -> Unit
): RecyclerView.Adapter<RecyclerTodoList.TodoViewHolder>() {

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val description: TextView = itemView.tv_todo_value
        val createdBy: TextView = itemView.tv_creator_value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_todo_list, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]

        if(todo != null) {
            holder.description.text = todo.description
            holder.createdBy.text = todo.createdBy

            holder.itemView.setOnClickListener { actionClick(todo) }
        }
    }

    override fun getItemCount(): Int = todoList.size
}