package com.example.smalltalk20

import android.content.Context
import android.graphics.Insets.add
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.smalltalk20.database.AppDatabase
import com.example.smalltalk20.database.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var loginKey = "LOGIN_KEY"

class LoginFragment : Fragment() {

    lateinit var username_input: EditText
    lateinit var userpassword_input: EditText
    lateinit var login_button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username_input = view.findViewById(R.id.username_input)
        userpassword_input = view.findViewById(R.id.userpassword_input)
        login_button = view.findViewById(R.id.login_button)

        login()


    }
    private fun login() {
        login_button.setOnClickListener{

            if (username_input.text.toString() == "" && userpassword_input.text.toString() == "") {
                var sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
                sharedPreferences
                    .edit()
                    .putBoolean(loginKey, true)
                    .apply()

                val user = User(
                    username = "Daweedo",
                    fullName = "Weed Daweed"
                )

                val database = AppDatabase.getInstance(requireContext())
                val userDAO = database.userDao()

                CoroutineScope(Dispatchers.IO).launch {
                    userDAO.removeAllUsers()
                    userDAO.saveUser(user)

                    activity?.supportFragmentManager?.commit {
                        setReorderingAllowed(true)
                        replace<MainChatFragment>(R.id.fragmentContainerView)
                    }
                }
            } else {
                Toast.makeText(activity, "Feil brukernavn eller passord", Toast.LENGTH_SHORT).show()
            }
        }
    }
}