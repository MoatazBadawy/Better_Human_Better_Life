package com.moataz.better_human.better_life.presentation.habits.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.moataz.better_human.better_life.core.enums.HabitType
import com.moataz.better_human.better_life.core.extensions.postEvent
import com.moataz.better_human.better_life.core.util.event.Event
import com.moataz.better_human.better_life.data.local.entity.habit.HabitEntity
import com.moataz.better_human.better_life.data.model.habit.Habit
import com.moataz.better_human.better_life.data.repository.habit.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitsViewModel @Inject constructor(
    private val repository: HabitRepository
) : ViewModel(), HabitsClicksListener {

    private val _habitsList = MutableLiveData<List<Habit>>()
    val habitsList: LiveData<List<Habit>> get() = _habitsList

    private var currentHabitType = HabitType.POSITIVE
    val addHabitClickedEvent = MutableLiveData(Event(false))

    val displayNotification = repository.getAllHabitsByNotCompleted(false).asLiveData()

    val habitName = MutableLiveData("")
    private val habitType = MutableLiveData(HabitType.POSITIVE.pathName)

    private val _isNameValid = MutableLiveData(true)
    val isNameValid: LiveData<Boolean> = _isNameValid

    private val _isCancelClicked = MutableLiveData(Event(false))
    val isCancelClicked: LiveData<Event<Boolean>> = _isCancelClicked

    private val _isAddClicked = MutableLiveData(Event(false))
    val isAddClicked: LiveData<Event<Boolean>> = _isAddClicked

    init {
        getHabitsByType(currentHabitType)
    }

    private fun getHabitsByType(habitType: HabitType) {
        viewModelScope.launch {
            repository.getAllHabitsByType(habitType).collectLatest { habits ->
                Log.d("HabitsViewModel", habits.size.toString())
                _habitsList.postValue(habits)
            }
        }
    }

    override fun updateHabitByCompleted(habit: Habit, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.updateHabitByCompleted(habit, isCompleted)
            getHabitsByType(currentHabitType)
        }
    }

    fun onChipTypeClick(habitType: HabitType) {
        currentHabitType = habitType
        getHabitsByType(habitType)
    }

    fun onAddHabitClick() {
        addHabitClickedEvent.postEvent(true)
    }

    private fun insertHabit() {
        viewModelScope.launch {
            repository.insertHabit(
                HabitEntity(
                    name = habitName.value!!,
                    type = habitType.value!!,
                    isCompleted = false,
                )
            )
            clearFields()
        }
    }

    fun updateHabitsList() {
        getHabitsByType(currentHabitType)
    }

    fun addHabitDialogClicked() {
        if (habitName.value.toString().isNotEmpty()) {
            insertHabit()
            onCloseDialogClick()
            viewModelScope.launch {
                delay(3000)
                _isAddClicked.postValue(Event(true))
            }
        } else {
            _isNameValid.value = false
        }
    }

    fun onChipChooseHabitType(type: String) {
        habitType.postValue(type)
    }

    private fun clearFields() {
        habitName.value = ""
        habitType.value = HabitType.POSITIVE.pathName
        _isNameValid.value = true
    }

    fun onCloseDialogClick() {
        clearFields()
        _isCancelClicked.postValue(Event(true))
    }

    override fun onHabitsClick(habit: Habit) {}
}