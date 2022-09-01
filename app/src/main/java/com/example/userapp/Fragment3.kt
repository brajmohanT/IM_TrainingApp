package com.example.userapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log
import androidx.fragment.app.FragmentManager


class Fragment3 : Fragment() {


//    private lateinit var database: PersonDB
    private lateinit var contextView :View
    private lateinit var signUpBtn: Button
    private lateinit var logInBtn: Button
    private lateinit var fName: EditText
    private lateinit var lName: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var city: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpBtn = view.findViewById(R.id.signUp_btn)
        logInBtn = view.findViewById(R.id.login_btn)
        fName = view.findViewById(R.id.signUp_fName)
        lName = view.findViewById(R.id.signUp_lName)
        phone = view.findViewById(R.id.signUp_number)
        email = view.findViewById(R.id.signUp_email)
        city = view.findViewById(R.id.signUp_city)
        contextView = view.findViewById(R.id.frag3)

//        database = PersonDB.getDB(requireActivity().applicationContext)


        signUpBtn.setOnClickListener {
            saveData()
        }

        logInBtn.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_cv,LogInFragment())
                addToBackStack(null)
                commit()
            }
        }

    }

    private fun saveData() {
        val fNameValue = fName.text.toString()
        val lNameValue = lName.text.toString()
        val phoneValue = phone.text.toString()
        val emailValue = email.text.toString()
        val cityValue = city.text.toString()

        val sharedPref = activity?.getSharedPreferences(
            "user_info", Context.MODE_PRIVATE
        ) ?: return
        with(sharedPref.edit()) {
            putString("fName", fNameValue)
            putString("lName", lNameValue)
            putString("phone", phoneValue)
            putString("email", emailValue)
            putString("city", cityValue)
            apply()
        }

//        GlobalScope.launch {
//            if (!database.personDao().mailExist(emailValue)) {
//                database.personDao().insertPerson(
//                    Person(
//                        0,
//                        fNameValue,
//                        lNameValue,
//                        phoneValue,
//                        emailValue,
//                        cityValue
//                    )
//                )
//
//                Snackbar.make(contextView, "Successfully Signed Up: Please Login", Snackbar.LENGTH_SHORT)
//                    .show()
//            }
//            else {
//                Log.d("Brajmohan", "USER ALREADY EXists")
//                Snackbar.make(contextView, "User Alreday hai", Snackbar.LENGTH_SHORT)
//                    .show()
//            }
//
//        }


    }
}