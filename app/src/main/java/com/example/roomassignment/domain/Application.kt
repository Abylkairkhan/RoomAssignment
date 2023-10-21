package com.example.roomassignment.domain

import android.app.Application
import com.example.roomassignment.data.room.WordRepository
import com.example.roomassignment.data.room.WordRoomDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { WordRoomDataBase.getDataBase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }

}