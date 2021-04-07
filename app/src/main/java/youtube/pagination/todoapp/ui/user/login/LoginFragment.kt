package youtube.pagination.todoapp.ui.user.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.login_fragment.*
import youtube.pagination.todoapp.R

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.let {
            it.status.observe(viewLifecycleOwner, { status ->
                if (status) {
                    Log.i("haloooo", "halooooo")
                    findNavController().navigate(R.id.todoListFragment)
                }
            })

            it.msg.observe(viewLifecycleOwner, { msg -> showToaster(msg) })
        }

        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_btn.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        submit_login_btn.setOnClickListener {
            val email = et_login_email.text.toString()
            val password = et_login_password.text.toString()

            loginViewModel.tryToLogin(email, password)
        }
    }

    private fun showToaster(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

}