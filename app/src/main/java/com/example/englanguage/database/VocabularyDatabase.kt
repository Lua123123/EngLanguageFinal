package com.example.englanguage.database

import android.content.Context
import androidx.room.Database
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import androidx.room.RoomDatabase
import kotlin.jvm.Synchronized
import androidx.room.Room
import com.example.englanguage.model.topic.Success

@Database(entities = [Success::class, SuccessVocabulary::class], version = 1)
abstract class VocabularyDatabase : RoomDatabase() {
    abstract fun topicDAO(): TopicDAO?
    abstract fun vocabularyDAO(): VocabularyDAO?
    abstract fun VocabularyOfTopicDAO(): VocabularyOfTopicDAO?

    companion object {
        private const val DATABASE_NAME = "iii.db"
        private var instance: VocabularyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): VocabularyDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    VocabularyDatabase::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}