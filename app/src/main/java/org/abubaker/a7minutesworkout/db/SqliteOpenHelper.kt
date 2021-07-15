package org.abubaker.a7minutesworkout.db

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

        // Creates an empty set of values using the default initial size
        val values = ContentValues()

        // Inert "today's Date" into the Date Column
        values.put(COLUMN_COMPLETED_DATE, date)

        // Open Database Connection for R/W tasks
        val db = this.writableDatabase

        // Insert query is return
        db.insert(TABLE_HISTORY, null, values)

        // Close Database Connection
        db.close()

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