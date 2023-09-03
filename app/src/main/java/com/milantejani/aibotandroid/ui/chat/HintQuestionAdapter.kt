package com.milantejani.aibotandroid.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milantejani.aibotandroid.databinding.ItemHintQuestionBinding

class HintQuestionAdapter(
    val mContext: Context,
    val aiCategoryExample: List<String>,
    val onClick: ((String) -> Unit)? = null
) : RecyclerView.Adapter<HintQuestionAdapter.HintQuestionViewHolder>() {


    inner class HintQuestionViewHolder(val binding: ItemHintQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindAll(s: String) {
            binding.txtHintQuestion.text = s
            binding.rlHintQuestion.setOnClickListener {
                onClick?.invoke(s)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HintQuestionViewHolder {
        val binding = ItemHintQuestionBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return HintQuestionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return aiCategoryExample.size
    }

    override fun onBindViewHolder(holder: HintQuestionViewHolder, position: Int) {
        holder.bindAll(aiCategoryExample[position])
    }

}
