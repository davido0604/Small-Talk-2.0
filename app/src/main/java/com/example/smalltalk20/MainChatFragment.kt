package com.example.smalltalk20

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smalltalk20.database.ChatMessage
import java.util.*


class MainChatFragment : Fragment() {

    lateinit var settingsButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    lateinit var sendMessageButton: ImageView
    lateinit var messageText: TextView
    private lateinit var adapter: ChatAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_chat, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsButton = view.findViewById(R.id.settings_button)
        recyclerView = view.findViewById(R.id.main_chat_recview)
        sendMessageButton = view.findViewById(R.id.imageButton_send)
        messageText = view.findViewById(R.id.message_text)
        layoutManager = LinearLayoutManager(activity)

        val chatList = mutableListOf(
            ChatMessage(message = "111111111111111111111111111111111111111111111111", username = "Otzi", date = Date().time),
            ChatMessage(message = "222222222222222222222222222222222222222222222222", username = "Anders", date = Date().time),
            ChatMessage(message = "333333333333333333333333333333333333333333333333", username = "Otzi", date = Date().time),
            ChatMessage(message = "444444444444444444444444444444444444444444444444", username = "Anders", date = Date().time),
            ChatMessage(message ="555555555555555555555555555555555555555555555555", username = "Otzi", date = Date().time),
            ChatMessage(message ="666666666666666666666666666666666666666666666666", username = "Anders", date = Date().time),
            ChatMessage(message = "777777777777777777777777777777777777777777777777", username = "Otzi", date = Date().time),
            ChatMessage(message = "888888888888888888888888888888888888888888888888", username = "Anders", date = Date().time),

            ChatMessage(message =
                "Hello. Her går det også ganske bra. Hva skal vi ha om denne uka?",
                username = "Ida",
                date = Date().time
            ),
            ChatMessage(
                message = "Jeg har blitt syk, så det går ikke så bra med meg. Håper på at jeg kan bli frisk veldig snart!",
                username = "Per",
                date = Date().time
            ),
            ChatMessage(
                message ="Fint Anders og Ida! Vi skal lære om databaser denne uka, det tror jeg blir veldig bra! Masse god bedring, Per. :)",
                username = "Otzi",
                date = Date().time
            )
        )

        fun hideKeyboard() {
            val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(getView()?.windowToken, 0)
        }

        sendMessageButton.setOnClickListener {
            chatList.add(ChatMessage(message = messageText.text.toString(), username = "Otzi", date = Date().time))
            adapter.updateData(chatList)
            messageText.text = ""
            hideKeyboard()
            recyclerView.scrollToPosition(chatList.size - 1)
        }

        adapter = ChatAdapter(
            chatList,
            "Otzi"
       )
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // Scrolle til bunn av recyclerView så siste meldinger vises
        recyclerView.scrollToPosition(chatList.size - 1)


        settingsButton.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace<SettingsFragment>(R.id.fragmentContainerView)
                addToBackStack(null)
            }
        }
    }
}