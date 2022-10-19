package com.moataz.better_human.better_life.data.model.habit

data class Habit(
    val id: Long,
    val name: String,
    val type: String,
    val isCompleted: Boolean
)