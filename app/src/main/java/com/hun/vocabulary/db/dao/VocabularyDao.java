package com.hun.vocabulary.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hun.vocabulary.db.entity.VocabularyEntity;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface VocabularyDao {

    @Insert
    Completable insert(VocabularyEntity vocabularyEntity);

    @Query("select * from vocabulary")
    LiveData<List<VocabularyEntity>> getAll();
}
