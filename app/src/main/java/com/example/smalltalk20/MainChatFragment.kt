package com.example.smalltalk20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace


class MainChatFragment : Fragment() {

    lateinit var settingsButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_chat, container, false)

        /*
        settingsButton = view.findViewById(R.id.settings_button)

        settingsButton.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace<SettingsFragment>(R.id.fragmentContainerView)
            }
        }

         */


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsButton = view.findViewById(R.id.settings_button)

        settingsButton.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace<SettingsFragment>(R.id.fragmentContainerView)
                addToBackStack(null)
            }
        }

    }

}