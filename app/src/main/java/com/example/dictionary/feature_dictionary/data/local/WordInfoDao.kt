package com.example.dictionary.feature_dictionary.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(infos: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN (:words)")
    suspend fun deleteWordInfos(words: List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word ||'%'")//this checks if the word entered is in the db where || means concatenats and % is a placeholder
    //thisquery searchs for the word entered in the db
    suspend fun getWordInfos(words: String): List<WordInfoEntity>

}