package com.moataz.better_human.better_life.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}