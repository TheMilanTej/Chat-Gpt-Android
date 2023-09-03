package com.milantejani.aibotandroid.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.recyclerview.widget.RecyclerView
import com.ai.domain.chatgpt.model.MessageSender
import com.ai.domain.chatgpt.model.UIMessage
import com.bumptech.glide.Glide
import com.milantejani.aibotandroid.R
import com.milantejani.aibotandroid.databinding.RowIncomingMessageBinding
import com.milantejani.aibotandroid.databinding.RowOutcomingMessageBinding

class ChatAdapter(val context: Context, val onClick: ((Int, String) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messages: ArrayList<UIMessage> = arrayListOf()

    inner class ChatInViewHolder(val binding: RowIncomingMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindInComingMessage(item: UIMessage) {
            val bindingIn = binding

            bindingIn.rlMessage.setOnClickListener {
                if (URLUtil.isValidUrl(item.message)) {
                    /*StfalconImageViewer.Builder(
                    context, listOf(item.message)
                    ) { view, image ->
                        Glide.with(context).load(image).into(view)
                    }.withTransitionFrom(imageView).withStartPosition(0).show()*/
                }
            }

            bindingIn.ivCopy.setOnClickListener {
                if (URLUtil.isValidUrl(item.message)) {
                    onClick?.invoke(0, item.message)
                } else {
                    onClick?.invoke(1, item.message)
                }
            }

            bindingIn.ivShare.setOnClickListener {
                if (URLUtil.isValidUrl(item.message)) {
                    onClick?.invoke(2, item.message)
                } else {
                    onClick?.invoke(3, item.message)
                }
            }


            if (URLUtil.isValidUrl(item.message)) {
                bindingIn.ivImage.visibility = View.VISIBLE
                bindingIn.tvMessage.visibility = View.GONE
                Glide.with(context).load(item.message).into(bindingIn.ivImage)
                bindingIn.ivCopy.setImageResource(R.drawable.ic_download)
            } else {
                bindingIn.tvMessage.visibility = View.VISIBLE
                bindingIn.ivImage.visibility = View.GONE
                bindingIn.tvMessage.text = item.message.trim()
                bindingIn.ivCopy.setImageResource(R.drawable.ic_copy)
            }
        }
    }

    inner class ChatOutViewHolder(val binding: RowOutcomingMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindOutComingMessage(item: UIMessage) {
            binding.tvMessage.text = item.message.trim()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message: UIMessage = messages[position]
        return message.messageSender.ordinal
    }

    fun addMessageToList(uiMessage: UIMessage): Int {
        val position = messages.size
        messages.add(uiMessage)
        notifyItemInserted(position)

        return (messages.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MessageSender.ME.ordinal -> {
                val binding = RowOutcomingMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ChatOutViewHolder(binding)
            }
            MessageSender.OPPOSITE.ordinal -> {
                val binding = RowIncomingMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ChatInViewHolder(binding)
            }
            else -> {
                val binding = RowIncomingMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ChatInViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MessageSender.ME.ordinal -> {
                (holder as ChatOutViewHolder).bindOutComingMessage(messages[position])
            }
            MessageSender.OPPOSITE.ordinal -> {
                (holder as ChatInViewHolder).bindInComingMessage(messages[position])
            }
        }
    }
}