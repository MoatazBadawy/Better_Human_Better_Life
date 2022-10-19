package com.moataz.better_human.better_life.presentation.habits.viewmodel

import com.moataz.better_human.better_life.data.model.habit.Habit
import com.moataz.better_human.better_life.presentation.base.interfaces.BaseInteractionListener

interface HabitsClicksListener : BaseInteractionListener {
    fun onHabitsClick(habit: Habit)
    fun updateHabitByCompleted(habit: Habit, isCompleted: Boolean)
}