package com.example.roomassignment.data.room

import androidx.annotation.WorkerThread
import com.example.roomassignment.domain.Word

class WordRepository(
    private val wordDao: WordDao
) {

    var wordList = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}