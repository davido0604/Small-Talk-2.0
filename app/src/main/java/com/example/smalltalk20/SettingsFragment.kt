package com.example.smalltalk20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class SettingsFragment : Fragment() {

    lateinit var logOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getPreferences(AppCompatActivity.MODE_PRIVATE)

        logOut = view.findViewById(R.id.logout_button)

        logOut.setOnClickListener{
            sharedPreferences
                .edit()
                .putBoolean(loginKey, false)
                .apply()

            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace<LoginFragment>(R.id.fragmentContainerView)
            }
        }

    }

}