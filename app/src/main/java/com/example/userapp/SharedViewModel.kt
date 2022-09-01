package com.example.userapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


class SharedViewModel(private val repository: Repository) : ViewModel() {
//    private val _isExists = MutableLiveData<Int>(0)
//    val isExists: LiveData<Int>
//    get() = _isExists

    fun personExist(email: String, fName: String): LiveData<Int> {
       return  repository.personExist(email, fName)
    }

}