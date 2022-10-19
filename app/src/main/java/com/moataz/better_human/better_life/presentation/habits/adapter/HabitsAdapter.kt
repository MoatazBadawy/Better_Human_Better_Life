package com.moataz.better_human.better_life.presentation.habits.adapter

import com.moataz.better_human.better_life.R
import com.moataz.better_human.better_life.data.model.habit.Habit
import com.moataz.better_human.better_life.presentation.base.adapter.BaseAdapter
import com.moataz.better_human.better_life.presentation.habits.viewmodel.HabitsClicksListener

class HabitsAdapter(
    movies: List<Habit>,
    listener: HabitsClicksListener
) : BaseAdapter<Habit>(movies, listener) {
    override fun layoutId(): Int = R.layout.item_habit
    override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        return oldItem.isCompleted == newItem.isCompleted
    }
}