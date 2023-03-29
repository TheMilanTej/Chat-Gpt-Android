package com.example.chatgptbotandroid.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgptbotandroid.data.model.CompletionRequestData
import com.example.chatgptbotandroid.data.model.CompletionResponceModel
import com.example.chatgptbotandroid.data.repo.ChatGptRepo
import com.example.chatgptbotandroid.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ChatGptViewModel(private val topHeadlineRepository: ChatGptRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<CompletionResponceModel>>(UiState.Loading)

    val uiState: StateFlow<UiState<CompletionResponceModel>> = _uiState

    fun fetchTopHeadlines(body: CompletionRequestData) {
        viewModelScope.launch {
            topHeadlineRepository.getGptCompletionResponse(body).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

}