package com.example.colabtasks_app.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.colabtasks_app.DB.Dao.AuthTokenDao
import com.example.colabtasks_app.DB.Entity.AuthToken

@Database(entities = [AuthToken::class], version = 1, exportSchema = false)
abstract class CoLabTasksDataBase : RoomDatabase() {
    abstract fun authTokenDao(): AuthTokenDao

    companion object {
        @Volatile
        private var INSTANCE: CoLabTasksDataBase? = null

        fun getDatabase(context: Context): CoLabTasksDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoLabTasksDataBase::class.java,
                    "colabtask_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}