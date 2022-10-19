package com.moataz.better_human.better_life.core.util.helper

import androidx.room.TypeConverter
import com.moataz.better_human.better_life.core.enums.HabitType

class Converters {
    @TypeConverter
    fun toHabitType(value: String) = HabitType.valueOf(value)
}