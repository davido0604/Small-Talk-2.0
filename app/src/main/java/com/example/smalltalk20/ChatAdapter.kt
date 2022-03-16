package com.example.smalltalk20

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smalltalk20.database.ChatMessage
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(
    private var dataSet: List<ChatMessage>,
    private val loggedInUsername: String)
    : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    lateinit var context: Context

    open class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
        open lateinit var senderNameText: TextView
        open lateinit var profileImage: ImageView
        open lateinit var messageText: TextView
        open lateinit var dateText: TextView

    }

    inner class LeftChatViewHolder(view: View): ChatViewHolder(view) {
        override var senderNameText: TextView = view.findViewById(R.id.left_chat_bubble_name)
        override var profileImage: ImageView = view.findViewById(R.id.left_chat_bubble_image)
        override var messageText: TextView = view.findViewById(R.id.left_chat_bubble_message_text)
        override var dateText: TextView = view.findViewById(R.id.left_chat_bubble_date_text)
    }

    inner class RightChatViewHolder(view: View): ChatViewHolder(view) {
        override var senderNameText: TextView = view.findViewById(R.id.right_chat_bubble_name)
        override var profileImage: ImageView = view.findViewById(R.id.left_chat_bubble_image)
        override var messageText: TextView = view.findViewById(R.id.right_chat_bubble_message_text)
        override var dateText: TextView = view.findViewById(R.id.right_chat_bubble_date_text)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isChatFromLoggedInUser(position)) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        context = parent.context

        val cellView = if (viewType == 0) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.right_chat_cell_item, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.left_chat_cell_item, parent, false)
        }

        val params: ViewGroup.LayoutParams = cellView.layoutParams
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        cellView.layoutParams = params

        return if (viewType == 0) {
            RightChatViewHolder(cellView)
        } else {
            LeftChatViewHolder(cellView)
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatObject = dataSet[position]

        holder.senderNameText.text = chatObject.username
        holder.messageText.text = chatObject.message

        val simpleDate = SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.getDefault())
        holder.dateText.text = simpleDate.format(chatObject.date)
    }

    override fun getItemCount() = dataSet.size

    private fun isChatFromLoggedInUser(position: Int): Boolean {
        return dataSet[position].username == loggedInUsername
    }
    fun updateData(updatedlist: MutableList<ChatMessage>) {
        dataSet = updatedlist
        notifyDataSetChanged()
    }
}