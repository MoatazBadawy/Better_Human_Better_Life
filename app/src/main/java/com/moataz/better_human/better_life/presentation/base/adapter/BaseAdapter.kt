package com.moataz.better_human.better_life.presentation.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.better_human.better_life.BR
import com.moataz.better_human.better_life.presentation.base.interfaces.BaseInteractionListener

abstract class BaseAdapter<T>(
    private var itemsList: List<T>,
    private val listener: BaseInteractionListener
) : RecyclerView.Adapter<BaseAdapter.ItemViewHolder>() {
    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    fun setItems(newItems: List<T>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(RecyclerDiffUtil(itemsList, newItems, ::areContentsTheSame))
        itemsList = newItems
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId(),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.run {
            setVariable(BR.item, itemsList[position])
            setVariable(BR.listener, listener)
        }
    }

    class ItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}