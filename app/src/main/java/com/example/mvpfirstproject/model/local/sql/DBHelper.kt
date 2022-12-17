package com.example.mvpfirstproject.model.local.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.mvpfirstproject.model.remote.Constants

class DBHelper (context: Context): SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.let {
                it.execSQL(Constants.CREATE_PRODUCT_TABLE)
            }
        }catch (e: SQLiteException){
            e.printStackTrace()
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}
