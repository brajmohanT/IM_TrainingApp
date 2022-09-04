package com.example.userapp

import androidx.lifecycle.LiveData

class Repository(private val personDAO: PersonDAO) {


    suspend fun insertPerson(person: Person){
        personDAO.insertPerson(person)
    }

    suspend fun updatePerson(person: Person){
        personDAO.updatePerson(person)
    }



    suspend fun deletePerson(person: Person){
        personDAO.deletePerson(person)
    }


    fun getPerson(id :Long): LiveData<Person> {

        return personDAO.getPerson(id)
    }


     fun mailExist(email : String):LiveData<Int>{
        return personDAO.mailExist(email)
    }


     fun personExist(email: String, fName: String):LiveData<Int> {
        return personDAO.personExist(email, fName)
    }

    fun getId(email:String): LiveData<Long>{
        return personDAO.getId(email)
    }
}