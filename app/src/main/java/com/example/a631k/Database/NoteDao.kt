package com.example.a631k.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.a631k.Model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: Note)

    @Query("SELECT*FROM note_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>
}