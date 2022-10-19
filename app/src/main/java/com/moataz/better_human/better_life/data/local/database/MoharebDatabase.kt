package com.moataz.better_human.better_life.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moataz.better_human.better_life.core.util.helper.Converters
import com.moataz.better_human.better_life.data.local.dao.habit.HabitDao
import com.moataz.better_human.better_life.data.local.entity.habit.HabitEntity

@Database(
    entities = [HabitEntity::class], version = 1
)
@TypeConverters(Converters::class)
abstract class MoharebDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {
        private const val DATABASE_NAME = "mohareb.db"

        @Volatile
        private var instance: MoharebDatabase? = null

        fun getInstance(context: Context): MoharebDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MoharebDatabase {
            return Room.databaseBuilder(context, MoharebDatabase::class.java, DATABASE_NAME)
                .createFromAsset("database/habits.db")
                .build()
        }
    }
}