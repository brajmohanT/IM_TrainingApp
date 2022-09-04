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
import androidx.lifecycle.ViewModelProvider
import com.example.userapp.databinding.Fragment3Binding
import com.example.userapp.databinding.FragmentEditBinding


class Fragment3 : Fragment() {

    private var _binding: Fragment3Binding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: SharedViewModel

    //    private lateinit var database: PersonDB
    private lateinit var contextView: View
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
        _binding = Fragment3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpBtn = view.findViewById(R.id.signUp_btn)
//        logInBtn = view.findViewById(R.id.login_btn)
        fName = view.findViewById(R.id.signUp_fName)
        lName = view.findViewById(R.id.signUp_lName)
        phone = view.findViewById(R.id.signUp_number)
        email = view.findViewById(R.id.signUp_email)
        city = view.findViewById(R.id.signUp_city)
        contextView = view.findViewById(R.id.frag3)

//        database = PersonDB.getDB(requireActivity().applicationContext)
        val dao = PersonDB.getDB(requireActivity().applicationContext).personDao()
        val repository = Repository(dao)
        sharedViewModel =
            ViewModelProvider(requireActivity(), SharedViewModelFactory(repository)).get(
                SharedViewModel::class.java
            )

        signUpBtn.setOnClickListener {
//            saveData()
            saveUser()
        }

//        logInBtn.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()?.apply {
//                replace(R.id.fragment_cv, LogInFragment())
//                addToBackStack(null)
//                commit()
//            }
//        }

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

    private fun saveUser() {

        if(binding.signUpFName.text.length>2 &&
        binding.signUpLName.text.length>2 &&
        binding.signUpNumber.text.length==10 &&
        binding.signUpEmail.text.length>5&&
        binding.signUpCity.text.length>2)
        {
            sharedViewModel.mailExist(binding.signUpEmail.text.toString())
                .observe(viewLifecycleOwner) {
//           Log.d("Exist", it.toString())
                    if (it == 0) {
                        sharedViewModel.insertPerson(
                            Person(
                                0,
                                binding.signUpFName.text.toString(),
                                binding.signUpLName.text.toString(),
                                binding.signUpNumber.text.toString(),
                                binding.signUpEmail.text.toString(),
                                binding.signUpCity.text.toString()
                            )
                        )
                        fragmentChange()

                    } else {
                        Log.d("Brajmohan", "USER ALREADY EXists")
                        Snackbar.make(contextView, "User Alreday hai", Snackbar.LENGTH_SHORT)
                            .show()
                    }

                }
        }else {
            Snackbar.make(contextView, "use correct formats", Snackbar.LENGTH_SHORT)
                .show()
        }

    }

    private fun fragmentChange() {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_cv, LogInFragment())
            addToBackStack(null)
            commit()
        }
    }
}