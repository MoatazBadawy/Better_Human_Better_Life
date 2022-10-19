package com.moataz.better_human.better_life.data.repository.habit

import com.moataz.better_human.better_life.core.enums.HabitType
import com.moataz.better_human.better_life.data.local.entity.habit.HabitEntity
import com.moataz.better_human.better_life.data.model.habit.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    suspend fun insertHabit(habit: HabitEntity)
    fun getAllHabitsByType(type: HabitType): Flow<List<Habit>>
    fun getAllHabitsByNotCompleted(isCompleted: Boolean): Flow<List<Habit>>
    suspend fun updateHabitByCompleted(habit: Habit, isCompleted: Boolean)
}