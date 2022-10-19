package com.moataz.better_human.better_life.data.repository.habit

import com.moataz.better_human.better_life.core.enums.HabitType
import com.moataz.better_human.better_life.data.local.dao.habit.HabitDao
import com.moataz.better_human.better_life.data.local.entity.habit.HabitEntity
import com.moataz.better_human.better_life.data.mapper.habit.HabitMapper
import com.moataz.better_human.better_life.data.model.habit.Habit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val dao: HabitDao,
    private val habitMapper: HabitMapper
) : HabitRepository {

    override suspend fun insertHabit(habit: HabitEntity) {
        dao.insertHabit(habit)
    }

    override fun getAllHabitsByType(type: HabitType): Flow<List<Habit>> = flow {
        emit(habitMapper.map(dao.getAllHabitsByType(type.pathName)))
    }

    override fun getAllHabitsByNotCompleted(isCompleted: Boolean): Flow<List<Habit>> = flow {
        emit(habitMapper.map(dao.getAllHabitsByNotCompleted(isCompleted)))
    }

    override suspend fun updateHabitByCompleted(habit: Habit, isCompleted: Boolean) {
        dao.updateHabitByCompleted(habit.id, isCompleted)
    }
}