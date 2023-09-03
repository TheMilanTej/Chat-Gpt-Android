package com.milantejani.aibotandroid.data.repo

import com.milantejani.aibotandroid.data.api.NetworkService
import com.milantejani.aibotandroid.data.model.CompletionRequestData
import com.milantejani.aibotandroid.data.model.CompletionResponceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatGptRepo @Inject constructor(private val networkService: NetworkService) {
    fun getGptCompletionResponse(rawJsonData: CompletionRequestData
    ): Flow<CompletionResponceModel> {
        return flow {
            emit(networkService.getGptCompletionResponse(rawJsonData))
        }
    }
}