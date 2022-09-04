package com.example.userapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class SharedViewModel(private val repository: Repository) : ViewModel() {

    var _id = MutableLiveData<Long>(1)


    fun personExist(email: String, fName: String): LiveData<Int> {
        return repository.personExist(email, fName)
    }

    fun getId(email: String): LiveData<Long> {
        return repository.getId(email)
    }

    fun getPerson(newId: Long): LiveData<Person> {
        return repository.getPerson(newId)
    }


    fun updatePerson(person: Person) {
        viewModelScope.launch {
            repository.updatePerson(person)
        }
    }

    fun mailExist(email: String): LiveData<Int> {
        return repository.mailExist(email)
    }

    fun insertPerson(person: Person) {
        viewModelScope.launch {
            repository.insertPerson(person)
        }
    }


}