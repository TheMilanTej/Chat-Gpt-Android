package com.example.chatgptbotandroid.data.api

import com.example.chatgptbotandroid.data.model.CompletionRequestData
import com.example.chatgptbotandroid.data.model.CompletionResponceModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("Content-Type: application/json", "Authorization: Bearer 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")
    @POST("completions")
    suspend fun getGptCompletionResponse(@Body rawJsonData: CompletionRequestData
    ): CompletionResponceModel

}