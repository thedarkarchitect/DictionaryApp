package com.example.dictionary.feature_dictionary.data.repository

import com.example.dictionary.core.util.Resources
import com.example.dictionary.domain.model.WordInfo
import com.example.dictionary.domain.repository.WordInfoRepository
import com.example.dictionary.feature_dictionary.data.local.WordInfoDao
import com.example.dictionary.feature_dictionary.data.remote.DictionaryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val dao: WordInfoDao,
    private val api: DictionaryApi
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resources<List<WordInfo>>> = flow {
//        onLoading the word is first checked in the db
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }//this will return info from an entered word and maps it to the wordinfo in the model
        emit(Resources.Loading(data = wordInfos))

        try {
            //then an api call is made word is saved to db then cached incase you need to look it up when offline
            val remoteWordInfos = api.getWordInfo(word)
            //word is then saved to the db
            dao.deleteWordInfos(remoteWordInfos.map{ it.word }) // if word exists in the db the one just searched will be deleted from the db on insert
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException){
            emit(Resources.Error(
                message = "Ooops, something went wrong!",
                data = wordInfos
            ))
        } catch (e: IOException) {
            emit(Resources.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = wordInfos
            ))
        }

        //After api call and adding word to db then on Success we need to get the word online o offline
        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resources.Success(newWordInfos))//onSuccess we get the word data
    }
}