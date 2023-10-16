package com.example.dictionary.feature_dictionary.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictionary.feature_dictionary.data.local.Converters
import com.example.dictionary.feature_dictionary.data.local.WordInfoDao

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase: RoomDatabase() {
    abstract val dao : WordInfoDao
}