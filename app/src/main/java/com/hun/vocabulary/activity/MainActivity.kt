package com.hun.vocabulary.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hun.vocabulary.R
import com.hun.vocabulary.Repository
import com.hun.vocabulary.adapter.VocabularyListAdapter
import com.hun.vocabulary.databinding.ActivityMainBinding
import com.hun.vocabulary.db.entity.VocabularyEntity
import com.hun.vocabulary.dialog.VocabularyAddDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repo: Repository
    private val vocabularyListAdapter =
        VocabularyListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            recyclerVocabularyList.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerVocabularyList.adapter = vocabularyListAdapter
        }

        repo = Repository(application)
    }

    override fun onStart() {
        super.onStart()

        setListener()

        repo.getAll().observe(this, {
            vocabularyListAdapter.setVocabularyList(it)
        })
    }

    private fun setListener() {
        binding.apply {
            fab.setOnClickListener(this@MainActivity)
        }
    }

    private fun getVocabulary(): VocabularyEntity {
        return VocabularyEntity().apply {
            title = "테스트"
            memo = "테스트 메모"
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.fab -> {
                VocabularyAddDialog().show(supportFragmentManager, "dialog_add_vocabulary")
//                CompositeDisposable().add(repo.insert(getVocabulary())
//                    .subscribeOn(Schedulers.io())
//                    .subscribe()
//                )
            }
        }
    }
}