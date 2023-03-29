package com.ai.domain.chatgpt.model

data class UIMessage(
    val messageId: Int,
    val message: String,
    val messageSender: MessageSender,
)
