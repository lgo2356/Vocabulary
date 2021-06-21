package com.hun.vocabulary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hun.vocabulary.R
import com.hun.vocabulary.pojo.Word

class WordListAdapter : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    var wordList: List<Word> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_word_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = wordList[position]

        holder.apply {
            tvYomigana.text = word.yomigana
            tvWord.text = word.word
            tvMeaning.text = word.meaningWord
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvYomigana: TextView = itemView.findViewById(R.id.tv_yomigana)
        val tvWord: TextView = itemView.findViewById(R.id.tv_word)
        val tvMeaning: TextView = itemView.findViewById(R.id.tv_meaning)
    }
}
