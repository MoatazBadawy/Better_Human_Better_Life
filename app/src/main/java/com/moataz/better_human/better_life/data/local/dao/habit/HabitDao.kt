package com.moataz.better_human.better_life.data.local.dao.habit

import androidx.room.*
import com.moataz.better_human.better_life.data.local.entity.habit.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habitEntity: HabitEntity)

    @Query("SELECT * FROM HABIT_TABLE WHERE type = :type ORDER BY isCompleted ASC")
    suspend fun getAllHabitsByType(type: String): List<HabitEntity>

    @Query("SELECT * FROM HABIT_TABLE WHERE (isCompleted != :isCompleted OR isCompleted = 0)")
    suspend fun getAllHabitsByNotCompleted(isCompleted: Boolean): List<HabitEntity>

    @Query("UPDATE HABIT_TABLE SET isCompleted = :isCompleted WHERE habitId = :habitId")
    suspend fun updateHabitByCompleted(habitId: Long, isCompleted: Boolean)

    @Query("DELETE FROM HABIT_TABLE WHERE habitId = :habitId")
    suspend fun deleteHabit(habitId: Long)
}