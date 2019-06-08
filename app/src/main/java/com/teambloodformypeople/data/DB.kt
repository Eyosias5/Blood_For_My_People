package com.teambloodformypeople.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.teambloodformypeople.data.daos.*
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.data.models.User

@Database(entities = [
    DonationHistory::class,
    Donor::class,
    Recepient::class,
    Report::class,
    User::class], version = 1, exportSchema = false)
abstract class DB : RoomDatabase() {
    abstract fun donationHistoryDao(): DonationHistoryDao
    abstract fun donorDao(): DonorDao
    abstract fun recipientDao(): RecepientDao
    abstract fun reportDao(): ReportDao
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: com.teambloodformypeople.data.DB? = null
        fun getDatabase(context: Context): com.teambloodformypeople.data.DB {
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "DB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

