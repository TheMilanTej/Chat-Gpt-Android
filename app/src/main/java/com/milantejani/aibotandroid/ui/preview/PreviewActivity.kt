package com.milantejani.aibotandroid.ui.preview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.milantejani.aibotandroid.data.model.AiCategoryModel
import com.milantejani.aibotandroid.databinding.ActivityPreviewBinding
import com.milantejani.aibotandroid.ui.chat.MainActivity
import com.milantejani.aibotandroid.utils.AppConstant

class PreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model = intent.getParcelableExtra<AiCategoryModel>(AppConstant.MODEL_KEY)!!

        binding.txtPreviewTitle.text = model.aiCategory
        binding.txtPreviewDesc.text = model.aiDescription
        binding.txtExampleQuestion.text = model.aiCategoryExample[0]


        binding.btnSampleQuestion.setOnClickListener {
            val intent = Intent(this@PreviewActivity, MainActivity::class.java)
            intent.putExtra(AppConstant.MODEL_KEY, model)
            startActivity(intent)
        }

        binding.btnYourSampleQuestion.setOnClickListener {
        /*    val intent = Intent(this@PreviewActivity, Create::class.java)
            intent.putExtra(AppConstant.MODEL_KEY, model)
            startActivity(intent)*/
        }
    }
}