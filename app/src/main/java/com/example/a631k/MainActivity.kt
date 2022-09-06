package com.example.a631k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a631k.Adapters.NoteRVAdapter
import com.example.a631k.Model.Note
import com.example.a631k.Model.NoteViewModal
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity:  AppCompatActivity() {
    private lateinit var mUserViewModel: NoteViewModal
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fab = findViewById(R.id.fab)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mUserViewModel = ViewModelProvider(this)[NoteViewModal::class.java]
        val adapter = NoteRVAdapter()
        recyclerView.adapter = adapter
        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })

        fab.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val mView = inflater.inflate(R.layout.activity_dialog, null)
            val etEmail: EditText = mView.findViewById(R.id.etEmail)

            dialogBuilder.setView(mView)
            dialogBuilder.setPositiveButton(
                "SAVE"
            ) { p0, p1 ->

                val title = etEmail.text.toString()
                val user = Note(0, title,  "${Date().hours} ${Date().minutes}")
                mUserViewModel.addUser(user)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

            }
            dialogBuilder.setNegativeButton(
                "CANCEL"
            ) { p0, p1 ->

            }
            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }

    }
}