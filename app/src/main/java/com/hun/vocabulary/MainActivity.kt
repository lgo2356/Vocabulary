package com.hun.vocabulary

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hun.vocabulary.adapter.VocaListAdapter
import com.hun.vocabulary.databinding.ActivityMainBinding
import com.hun.vocabulary.db.entity.VocaListEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repo: Repository
    private val vocaListAdapter = VocaListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            recyclerVocabularyList.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerVocabularyList.adapter = vocaListAdapter
        }

        repo = Repository(application)
    }

    override fun onStart() {
        super.onStart()

        repo.getAll().observe(this, {
            vocaListAdapter.setVocaList(it)
        })
    }

    private fun getVocabulary(): VocaListEntity {
        return VocaListEntity().apply {
            title = "테스트"
            memo = "테스트 메모"
            vocaId = 0
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.fab -> {
                CompositeDisposable().add(repo.insert(getVocabulary())
                    .subscribeOn(Schedulers.io())
                    .subscribe()
                )
            }
        }
    }
}