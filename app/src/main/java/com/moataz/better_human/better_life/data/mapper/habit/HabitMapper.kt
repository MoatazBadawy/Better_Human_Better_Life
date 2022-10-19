package com.moataz.better_human.better_life.data.mapper.habit

import com.moataz.better_human.better_life.data.local.entity.habit.HabitEntity
import com.moataz.better_human.better_life.data.mapper.Mapper
import com.moataz.better_human.better_life.data.model.habit.Habit
import javax.inject.Inject

class HabitMapper @Inject constructor() : Mapper<List<HabitEntity>, List<Habit>> {
    override fun map(input: List<HabitEntity>): List<Habit> {
        return input.map {
            Habit(
                id = it.habitId,
                name = it.name,
                type = it.type,
                isCompleted = it.isCompleted,
            )
        }
    }
}