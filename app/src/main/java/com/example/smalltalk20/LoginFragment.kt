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
        var loginKey = "LOGIN_KEY"
        login_button.setOnClickListener{

            if (username_input.text.toString() == "" && userpassword_input.text.toString() == "") {
                //(requireActivity() as MainActivity).menybar.selectedItemId = R.id.home
                var sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
                sharedPreferences
                    .edit()
                    .putBoolean(loginKey, true)
                    .apply()

                fragmentManager.commit {
                    setReorderingAllowed(true)
                    // Replace whatever is in the fragment_container view with this fragment
                    replace<ExampleFragment>(R.id.fragment_container)
                }
            }
        }
    }
}