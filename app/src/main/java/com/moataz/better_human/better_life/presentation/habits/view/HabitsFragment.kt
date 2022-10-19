package com.moataz.better_human.better_life.presentation.habits.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.moataz.better_human.better_life.R
import com.moataz.better_human.better_life.core.notification.HabitAlertNotification
import com.moataz.better_human.better_life.core.util.event.EventObserver
import com.moataz.better_human.better_life.databinding.FragmentHabitBinding
import com.moataz.better_human.better_life.presentation.base.fragment.BaseFragment
import com.moataz.better_human.better_life.presentation.habits.adapter.HabitsAdapter
import com.moataz.better_human.better_life.presentation.habits.viewmodel.HabitsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HabitsFragment : BaseFragment<FragmentHabitBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_habit
    override val viewModel: HabitsViewModel by viewModels()
    lateinit var habitsAdapter: HabitsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerVIew()
        observeEvents()
    }

    private fun initRecyclerVIew() {
        habitsAdapter = HabitsAdapter(emptyList(), viewModel)
        binding.habitsRecyclerView.adapter = habitsAdapter
    }

    private fun observeEvents() {
        viewModel.apply {
            addHabitClickedEvent.observe(viewLifecycleOwner, EventObserver {
                if (it) {
                    findNavController().navigate(
                        R.id.action_habits_fragment_to_habitAdditionDialog
                    )
                }
            })

            displayNotification.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    HabitAlertNotification().setupHabitAlertNotification(requireContext())
                }
            }

            habitsList.observe(viewLifecycleOwner) {
                habitsAdapter.setItems(it)
            }
        }
    }
}