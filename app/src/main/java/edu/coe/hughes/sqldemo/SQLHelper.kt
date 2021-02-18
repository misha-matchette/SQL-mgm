package edu.coe.hughes.sqldemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
val DATABASENAME = "MY DATABASE"
val TABLENAME = "Items"
val COL_NAME = "name"
val COL_SIZE = "size"
val COL_ID = "id"
class SQLHelper(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
       createTable(db)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    private fun createTable(db:SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(256)," + COL_SIZE + " INTEGER)"
        db?.execSQL(createTable)
    }

    fun insertData(item:Item) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, item.name)
        contentValues.put(COL_SIZE, item.size)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
    fun readData(): MutableList<Item> {
        val list: MutableList<Item> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val item = Item()
                item.name = result.getString(result.getColumnIndex(COL_NAME)).toString()
                item.size = result.getString(result.getColumnIndex(COL_SIZE)).toInt()
                list.add(item)
            }
            while (result.moveToNext())
        }
        return list
    }

    fun clearDB(){
        val db = this.readableDatabase
        val dropTable = "DROP TABLE IF EXISTS "+ TABLENAME
        db.execSQL(dropTable)
        createTable(db)
    }
}