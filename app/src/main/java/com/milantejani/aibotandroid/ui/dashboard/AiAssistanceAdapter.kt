package com.milantejani.aibotandroid.ui.dashboard

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.milantejani.aibotandroid.data.model.AiCategoryModel
import com.milantejani.aibotandroid.databinding.ItemAssistanceBinding

class AiAssistanceAdapter(
    val dashBoardActivity: AppCompatActivity,
    val aiCategory: ArrayList<AiCategoryModel>,
    val onClick: ((AiCategoryModel) -> Unit?)? = null
) : RecyclerView.Adapter<AiAssistanceAdapter.AiAssistanceViewHolder>() {

    inner class AiAssistanceViewHolder(val binding: ItemAssistanceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindAll(aiCategoryModel: AiCategoryModel) {
            binding.txtTitle.text = aiCategoryModel.aiCategory
            binding.txtDesc.text = aiCategoryModel.aiDescription
            binding.btnChatNow.setOnClickListener {
                onClick?.invoke(aiCategoryModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AiAssistanceViewHolder {
        val binding = ItemAssistanceBinding.inflate(dashBoardActivity.layoutInflater, parent, false)
        return AiAssistanceViewHolder(binding)
    }

    override fun getItemCount(): Int = aiCategory.size

    override fun onBindViewHolder(holder: AiAssistanceViewHolder, position: Int) {
        holder.bindAll(aiCategory[position])
    }
}
