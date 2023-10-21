package com.example.roomassignment.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomassignment.domain.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDataBase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    class WordDataBaseCallBack(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()
            var word = Word("Android")
            wordDao.insert(word)
            word = Word("Best!")
            wordDao.insert(word)
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: WordRoomDataBase? = null

        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDataBase::class.java,
                    "word_database"
                )
                    .addCallback(WordDataBaseCallBack(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}