package com.moataz.better_human.better_life.presentation.base.adapter

import androidx.recyclerview.widget.DiffUtil

class RecyclerDiffUtil<T>(
    private val oldList: List<T>, private val newList: List<T>,
    private val areContentsTheSame: (T, T) -> Boolean
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }
}