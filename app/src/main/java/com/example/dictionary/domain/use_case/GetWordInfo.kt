package com.example.dictionary.domain.use_case

import com.example.dictionary.core.util.Resources
import com.example.dictionary.domain.model.WordInfo
import com.example.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resources<List<WordInfo>>> {
        if(word.isBlank()) { //if empty word is parsed then it return a flow
            return flow{ }
        }
        return repository.getWordInfo(word)//return the word info if word parsed
    }
}