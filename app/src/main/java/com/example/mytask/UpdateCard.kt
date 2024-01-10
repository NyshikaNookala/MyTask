package com.example.mytask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val description = DataObject.getData(pos).description
            val dueDate: String =DataObject.getData(pos).dueDate
            val priority: String=DataObject.getData(pos).priority
            val category: String = DataObject.getData(pos).category
            var taskStatus: String = DataObject.getData(pos).taskStatus
            create_title.setText(title)
            create_description.setText(description)
            create_DueDate.setText(dueDate)
            create_Category.setText(category)
            create_Priority.setText(priority)
            create_Status.setText(taskStatus)

            delete_button.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            create_title.text.toString(),
                            create_description.text.toString(),
                            dueDate,
                            create_Priority.text.toString(),
                            create_Category.text.toString(),
                            create_Status.text.toString()
                        )
                    )
                }
                myIntent()
            }

            update_button.setOnClickListener {
                DataObject.updateData(
                    pos,
                    create_title.text.toString(),
                    create_description.text.toString(),
                    dueDate,
                    create_Priority.text.toString(),
                    create_Category.text.toString(),
                    create_Status.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1, create_title.text.toString(),
                            create_description.text.toString(),
                            dueDate,
                            create_Priority.text.toString(),
                            create_Category.text.toString(),
                            create_Status.text.toString()
                        )
                    )
                }
                myIntent()
            }

        }
    }

    fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}