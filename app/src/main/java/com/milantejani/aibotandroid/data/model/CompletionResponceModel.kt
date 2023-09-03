package com.milantejani.aibotandroid.data.model


import com.google.gson.annotations.SerializedName

data class CompletionResponceModel(
    @SerializedName("choices")
    val choices: List<Choice>,
    @SerializedName("created")
    val created: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("object")
    val objectX: String,
    @SerializedName("usage")
    val usage: Usage
) {
    data class Choice(
        @SerializedName("finish_reason")
        val finishReason: String,
        @SerializedName("index")
        val index: Int,
        @SerializedName("logprobs")
        val logprobs: Any,
        @SerializedName("text")
        val text: String
    )

    data class Usage(
        @SerializedName("completion_tokens")
        val completionTokens: Int,
        @SerializedName("prompt_tokens")
        val promptTokens: Int,
        @SerializedName("total_tokens")
        val totalTokens: Int
    )
}