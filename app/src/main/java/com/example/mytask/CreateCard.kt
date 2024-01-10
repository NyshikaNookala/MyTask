package com.example.mytask


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateCard : AppCompatActivity() {
    private lateinit var selectdate: TextView


    private var formattedDate:String?=null
    private val calendar = Calendar.getInstance()

    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        selectdate = findViewById(R.id.create_DueDate)
        selectdate.setOnClickListener {
            showDatePicker()

        }

        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_description.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_Category.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_Status.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_Priority.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                var title = create_title.getText().toString()
                var description = create_description.getText().toString()

                var prio = create_Priority.getText().toString()
                var cate = create_Category.getText().toString()
                var stat = create_Status.getText().toString()


                DataObject.setData(title,description,formattedDate!!,prio,cate,stat)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title,description,formattedDate,prio,cate,stat))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun showDatePicker() {

        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this, {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                selectdate.text = "$formattedDate"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

}

