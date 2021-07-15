package org.abubaker.a7minutesworkout

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteOpenHelper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {

    /**
     * This override function is used to execute when the class is called once where the database tables are created.
     */
    override fun onCreate(db: SQLiteDatabase?) {

        // Create History Table Query.
        val CREATE_HISTORY_TABLE =
            ("CREATE TABLE " + TABLE_HISTORY + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_COMPLETED_DATE + " TEXT)")

        // If there is a DATABASE then execute the query, otherwise return null
        db?.execSQL(CREATE_HISTORY_TABLE)

    }

    /**
     * On Database Change
     * Note: We are recreating the database, after dropping the old database
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        // It drops the existing history table
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")

        // Calls the onCreate function so all the updated tables will be created.
        onCreate(db)

    }

    /**
     * Function is used to insert the date in Database History table.
     */
    fun addDate(date: String) {
        val values =
            ContentValues() // Creates an empty set of values using the default initial size
        values.put(
            COLUMN_COMPLETED_DATE,
            date
        ) // Putting the value to the column along with the value.
        val db =
            this.writableDatabase // Create and/or open a database that will be used for reading and writing.
        db.insert(TABLE_HISTORY, null, values) // Insert query is return
        db.close() // Database is closed after insertion.
    }

    // Static Variables
    companion object {

        // This DATABASE Version
        private const val DATABASE_VERSION = 1

        // Name of the DATABASE
        private const val DATABASE_NAME = "SevenMinutesWorkout.db"

        // Table Name
        private const val TABLE_HISTORY = "history"

        // Column Id
        private const val COLUMN_ID = "_id"

        // Column for Completed Date
        private const val COLUMN_COMPLETED_DATE = "completed_date"

    }


}