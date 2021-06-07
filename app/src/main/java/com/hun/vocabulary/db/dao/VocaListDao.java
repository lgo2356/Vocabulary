package com.hun.vocabulary.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hun.vocabulary.db.entity.VocaListEntity;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface VocaListDao {

    @Insert
    Completable insert(VocaListEntity vocaListEntity);

    @Query("select * from vocaList")
    LiveData<List<VocaListEntity>> getAll();
}
