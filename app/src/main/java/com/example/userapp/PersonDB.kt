package com.example.userapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Person::class], version = 1)
abstract class PersonDB : RoomDatabase() {

    abstract fun personDao(): PersonDAO

    companion object {

        @Volatile
        private var INSTANCE: PersonDB? = null


        fun getDB(context: Context): PersonDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDB::class.java, "personDataBase"
                    ).build()
                }
            }
            return INSTANCE!!

        }

    }
}