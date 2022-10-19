package com.moataz.better_human.better_life.data.local.entity.habit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HABIT_TABLE")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val habitId: Long = 0,
    val name: String,
    val type: String,
    val isCompleted: Boolean
)