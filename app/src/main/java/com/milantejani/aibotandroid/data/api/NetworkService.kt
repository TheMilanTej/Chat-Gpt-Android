package com.milantejani.aibotandroid.data.api

import com.milantejani.aibotandroid.data.model.CompletionRequestData
import com.milantejani.aibotandroid.data.model.CompletionResponceModel
import retrofit2.http.Body
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