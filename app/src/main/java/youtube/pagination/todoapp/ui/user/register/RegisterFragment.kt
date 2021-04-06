package youtube.pagination.todoapp.ui.user.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.register_fragment.*
import youtube.pagination.todoapp.R

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        registerViewModel.let {
            it.status.observe(viewLifecycleOwner, { status ->
                if (status) {
                    findNavController().navigate(R.id.loginFragment)
                }
            })

            it.msg.observe(viewLifecycleOwner, { msg -> showToaster(msg) })
        }

        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit_registration_btn.setOnClickListener {
            val password = et_reg_password.text.toString()
            val rePassword = et_reg_re_password.text.toString()

            if(password == rePassword) {
                val name = et_reg_name.text.toString()
                val email = et_reg_email.text.toString()

                registerViewModel.registerUser(email, password, name)
            } else {
                showToaster("Senha não confere")
            }
        }

    }

    private fun showToaster(msg: String) {
        Toast.makeText(
                requireContext(),
                msg,
                Toast.LENGTH_LONG)
            .show()
    }

}