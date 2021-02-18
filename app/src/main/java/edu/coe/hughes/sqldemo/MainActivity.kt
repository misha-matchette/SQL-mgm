package edu.coe.hughes.sqldemo


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.strictmode.SqliteObjectLeakedViolation
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var btnInsert: Button? = null
    var btnRead : Button? = null
    var tvResult :TextView? = null
    var editTextName: EditText?= null
    var editTextSize: EditText?= null
    var db:SQLHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val context = this
        //val db = SQLHelper(context)
        initView(this)

    }

    private fun initView(c: Context){
        btnInsert = findViewById<Button>(R.id.btnInsert)
        btnRead = findViewById<Button>(R.id.btnRead)
        tvResult = findViewById<TextView>(R.id.tvResult)
        editTextName  = findViewById<EditText>(R.id.editTextName)
        editTextSize = findViewById<EditText>(R.id.editTextSize)
        db = SQLHelper(c)
    }

    fun Insert(v: View){
        if (editTextName!!.text.toString().isNotEmpty() &&
            editTextSize!!.text.toString().isNotEmpty()
        ) {
            val item = Item(editTextName!!.text.toString(), editTextSize!!.text.toString().toInt())
            db!!.insertData(item)
            clearField()
        }
        else {
            Toast.makeText(this, "Please Fill All Data", Toast.LENGTH_SHORT).show()
        }
    }

    fun Read(v:View){
        val data = db!!.readData()
        tvResult!!.text = ""
        for (i in 0 until data.size) {
            tvResult!!.append(
                data[i].name + " " + data[i].size + "\n"
            )
        }
    }

    fun update(v:View){
        if (editTextName!!.text.toString().isNotEmpty() &&
            editTextSize!!.text.toString().isNotEmpty()
        ) {
            val item = Item(editTextName!!.text.toString(), editTextSize!!.text.toString().toInt())
            db!!.updateData(item)
            clearField()
        }
        else {
            Toast.makeText(this, "Please Fill All Data", Toast.LENGTH_SHORT).show()
        }
    }

    fun delete(v: View){

    }

    private fun clearField() {
        editTextName!!.text.clear()
        editTextSize!!.text.clear()
    }

    fun ClearDB(v:View){
        db!!.clearDB()
        tvResult!!.text = ""
    }

}