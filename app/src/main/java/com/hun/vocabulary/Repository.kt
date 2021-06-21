package com.hun.vocabulary

import android.app.Application
import androidx.lifecycle.LiveData
import com.hun.vocabulary.db.DatabaseManager
import com.hun.vocabulary.db.entity.VocabularyEntity
import io.reactivex.Completable

class Repository(application: Application) {

    private val vocaListDao = DatabaseManager.getInstance(application).vocaListDao()

    /**
     * DB
     */
    fun insert(vocabulary: VocabularyEntity): Completable {
        return vocaListDao.insert(vocabulary)
    }

    fun getAll(): LiveData<List<VocabularyEntity>> {
        return vocaListDao.all
    }
}
