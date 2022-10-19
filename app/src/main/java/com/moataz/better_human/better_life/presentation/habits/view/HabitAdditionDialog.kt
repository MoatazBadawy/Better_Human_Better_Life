package com.moataz.better_human.better_life.presentation.habits.view

import com.moataz.better_human.better_life.R
import com.moataz.better_human.better_life.core.util.event.EventObserver
import com.moataz.better_human.better_life.databinding.DialogHabitAddingBinding
import com.moataz.better_human.better_life.presentation.base.fragment.BaseDialogFragment
import com.moataz.better_human.better_life.presentation.habits.viewmodel.HabitsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HabitAdditionDialog :
    BaseDialogFragment<DialogHabitAddingBinding, HabitsViewModel>(R.layout.dialog_habit_adding) {
    override val viewModelClass = HabitsViewModel::class.java

    override fun onStart() {
        super.onStart()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.run {
            isCancelClicked.observe(viewLifecycleOwner, EventObserver {
                if (it) {
                    dismiss()
                }
            })

            isAddClicked.observe(viewLifecycleOwner, EventObserver {
                if (it) {
                    this.updateHabitsList()
                }
            })
        }
    }
}