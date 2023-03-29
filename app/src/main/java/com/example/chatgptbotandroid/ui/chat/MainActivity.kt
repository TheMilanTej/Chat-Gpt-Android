package com.example.chatgptbotandroid.ui.chat

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ai.domain.chatgpt.model.MessageSender
import com.ai.domain.chatgpt.model.UIMessage
import com.example.chatgptbotandroid.ChatGptApplication
import com.example.chatgptbotandroid.data.model.AiCategoryModel
import com.example.chatgptbotandroid.data.model.CompletionRequestData
import com.example.chatgptbotandroid.databinding.ActivityMainBinding
import com.example.chatgptbotandroid.di.component.DaggerActivityComponent
import com.example.chatgptbotandroid.di.module.ActivityModule
import com.example.chatgptbotandroid.ui.base.UiState
import com.example.chatgptbotandroid.utils.AppConstant
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var chatGptViewModel: ChatGptViewModel

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        ChatAdapter(this@MainActivity) { optionFlag, url ->

        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as ChatGptApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        setupObserver()

        binding.ivSend.setOnClickListener {
            if (binding.idEdtQuery.text.toString().isNotEmpty()) {
                binding.rlExampleQuestion.visibility = View.GONE
                val query = binding.idEdtQuery.text.toString()
                binding.idEdtQuery.text.clear()
                val pos = adapter.addMessageToList(UIMessage(0, query, MessageSender.ME))
                binding.rv.scrollToPosition(pos)
                getResponse(query)
            } else {
                Toast.makeText(this, "Please enter your query..", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.stackFromEnd = true

        binding.rv.layoutManager = layoutManager
        binding.rv.adapter = adapter

        val model = intent.getParcelableExtra<AiCategoryModel>(AppConstant.MODEL_KEY)!!

        binding.rvQuestion.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvQuestion.adapter = HintQuestionAdapter(this, model.aiCategoryExample) {
            binding.idEdtQuery.text.clear()
            binding.idEdtQuery.setText(it)
            binding.rlExampleQuestion.visibility = View.GONE
        }
    }

    private fun getResponse(query: String) {
        val completionRequestData = CompletionRequestData(
            "text-davinci-003", query, 100, 0, 1, 0.0, 0.0
        )

        chatGptViewModel.fetchTopHeadlines(completionRequestData)
    }


    private fun setupObserver() {
        lifecycleScope.launch {

            chatGptViewModel.uiState.collect {
                when (it) {
                    is UiState.Success -> {
                        val responseMsg = it.data.choices

                        var queryResponse = ""

                        responseMsg.forEach {
                            queryResponse += " ${it.text}"
                        }

                        val pos = adapter.addMessageToList(
                            UIMessage(
                                0, queryResponse, MessageSender.OPPOSITE
                            )
                        )
                        binding.rv.scrollToPosition(pos)
                    }
                    is UiState.Loading -> {
                        //binding.idTVResponse.text = "Loading..."
                    }
                    is UiState.Error -> {
                        //binding.idTVResponse.text = "Error"
                    }
                }
            }

        }
    }

}