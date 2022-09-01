package com.example.userapp

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PersonDAO {

    @Insert
    suspend fun insertPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT * FROM personTable WHERE id= :id" )
    fun getPerson(id :Long): LiveData<Person>

    @Query("SELECT EXISTS (SELECT * FROM personTable WHERE email=:email)")
    fun mailExist(email : String):LiveData<Int>

    @Query("SELECT EXISTS (SELECT * FROM personTable WHERE email=:email AND fName=:fName)")
    fun personExist(email: String, fName: String):LiveData<Int>

    @Query("SELECT id FROM personTable WHERE email= :email")
    fun getId(email:String):LiveData<Long>

}