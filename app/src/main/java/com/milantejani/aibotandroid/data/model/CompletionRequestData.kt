package com.milantejani.aibotandroid.data.model


import com.google.gson.annotations.SerializedName

/*{
  "model": "text-davinci-003",
  "prompt": "how old is islam?",
  "max_tokens": 100,
  "temperature": 0,
  "top_p": 1,
  "frequency_penalty":0.0,
  "presence_penalty":0.0
}*/

data class CompletionRequestData(
    @SerializedName("model") val model: String,
    @SerializedName("prompt") val prompt: String,
    @SerializedName("max_tokens") val maxTokens: Int,
    @SerializedName("temperature") val temperature: Int,
    @SerializedName("top_p") val topP: Int,
    @SerializedName("frequency_penalty") val frequency_penalty: Double,
    @SerializedName("presence_penalty") val presence_penalty: Double
)

