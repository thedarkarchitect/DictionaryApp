package com.example.dictionary.feature_dictionary.data.remote.dto

import com.example.dictionary.domain.model.WordInfo
import com.example.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {//this is changed to be used by the entity
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning()},
            phonetic = phonetic,
            word = word
        )
    }
}