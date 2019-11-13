package me.aartikov.fruitcounter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.aartikov.fruitcounter.domain.FruitCounter

class FruitCounterViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FruitCounterViewModel::class.java)) {
            return FruitCounterViewModel(FruitCounter()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}