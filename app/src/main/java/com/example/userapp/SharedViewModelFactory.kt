package com.example.userapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProvider.Factory

class SharedViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }
}