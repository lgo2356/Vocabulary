package com.hun.vocabulary

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hun.vocabulary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()

        binding.progressLearningRate.apply {
            setProgress(50)
//            setProgressColor(Color.BLUE)
        }
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