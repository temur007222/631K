package com.example.a631k.Database

import androidx.lifecycle.LiveData
import com.example.a631k.Model.Note

class NoteRepository(private val userDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = userDao.readAllData()

     fun addUser(user: Note){
        userDao.addUser(user)
    }

}