package com.hun.vocabulary.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hun.vocabulary.db.dao.VocabularyDao;
import com.hun.vocabulary.db.entity.VocabularyEntity;

@Database(
        version = 1,
        entities = {
                VocabularyEntity.class
        },
        exportSchema = false
)
public abstract class DatabaseManager extends RoomDatabase {

    public abstract VocabularyDao vocabularyDao();
    private static DatabaseManager INSTANCE;

    public static DatabaseManager getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseManager.class) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        DatabaseManager.class,
                        "vocabulary.db"
                )
                        .build();
            }
        }

        return INSTANCE;
    }
}
