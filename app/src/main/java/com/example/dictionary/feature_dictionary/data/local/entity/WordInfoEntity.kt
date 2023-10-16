package com.example.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionary.domain.model.Meaning
import com.example.dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val phonetic: String,
    val meanings: List<Meaning>,
    @PrimaryKey val id: Int? = null
){
    fun toWordInfo(): WordInfo {//this changes it to be used by the model then in the UI
        return WordInfo(
            meanings = meanings,
            word = word,
            phonetic = phonetic
        )
    }
}

