package com.hun.vocabulary

import android.app.Application
import androidx.lifecycle.LiveData
import com.hun.vocabulary.db.DatabaseManager
import com.hun.vocabulary.db.entity.VocaListEntity
import io.reactivex.Completable

class Repository(application: Application) {

    private val vocaListDao = DatabaseManager.getInstance(application).vocaListDao()

    /**
     * DB
     */
    fun insert(vocaList: VocaListEntity): Completable {
        return vocaListDao.insert(vocaList)
    }

    fun getAll(): LiveData<List<VocaListEntity>> {
        return vocaListDao.all
    }
}
