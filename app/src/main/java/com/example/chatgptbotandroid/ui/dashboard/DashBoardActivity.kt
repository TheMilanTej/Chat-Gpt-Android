package com.example.chatgptbotandroid.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgptbotandroid.databinding.ActivityDashBoardBinding
import com.example.chatgptbotandroid.ui.chat.MainActivity
import com.example.chatgptbotandroid.ui.preview.PreviewActivity
import com.example.chatgptbotandroid.utils.AppConstant

class DashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvQuestion.layoutManager = LinearLayoutManager(this)
        binding.rvQuestion.adapter =
            AiAssistanceAdapter(this@DashBoardActivity, AppConstant.aiCategory) { model ->
                val goToNext = Intent(this@DashBoardActivity, PreviewActivity::class.java)
                goToNext.putExtra(AppConstant.MODEL_KEY, model)
                startActivity(goToNext)
            }
    }

}