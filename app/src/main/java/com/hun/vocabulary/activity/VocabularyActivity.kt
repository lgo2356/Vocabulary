package com.hun.vocabulary.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hun.vocabulary.R
import com.hun.vocabulary.adapter.WordListAdapter
import com.hun.vocabulary.databinding.ActivityVocabularyBinding
import com.hun.vocabulary.dialog.WordAddDialog

class VocabularyActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityVocabularyBinding
    private val wordListAdapter = WordListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_vocabulary)
        binding.apply {
            lifecycleOwner = this@VocabularyActivity
            recyclerWordList.layoutManager = LinearLayoutManager(this@VocabularyActivity)
            recyclerWordList.adapter = wordListAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        setListener()
    }

    private fun setListener() {
        binding.apply {
            fab.setOnClickListener(this@VocabularyActivity)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.fab -> {
                WordAddDialog().show(supportFragmentManager, "word_add_dialog")
            }
        }
    }
}
