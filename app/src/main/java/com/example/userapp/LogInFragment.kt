package com.example.userapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class LogInFragment : Fragment() {

//    private lateinit var database: PersonDB



    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var loginName: EditText
    private lateinit var loginEmail: EditText
    private lateinit var loginBtn: Button
    private lateinit var contextView :View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn = view.findViewById(R.id.login_btn)
        loginName = view.findViewById(R.id.login_fName)
        loginEmail = view.findViewById(R.id.login_email)
        contextView = view.findViewById(R.id.login_frag)


//        database = PersonDB.getDB(requireActivity().applicationContext)


        val dao=  PersonDB.getDB(requireActivity().applicationContext).personDao()
        val repository = Repository(dao)
        sharedViewModel = ViewModelProvider(requireActivity(), SharedViewModelFactory(repository)).get(SharedViewModel::class.java)



        loginBtn.setOnClickListener{
            logInExe(it)
//            model.saveId(id)
        }

    }

    private fun logInExe(view: View){

        val fNameValue = loginName.text.toString()
        val emailValue = loginEmail.text.toString()

        sharedViewModel.personExist(emailValue,fNameValue).observe(viewLifecycleOwner) { it ->
            if(it==1){
//                sharedViewModel.id= sharedViewModel.getId(emailValue).value !!

                sharedViewModel.getId(emailValue).observe(viewLifecycleOwner) {id->
                    sharedViewModel._id.value = id
                    Log.d("Id", id.toString())

                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.fragment_cv, Fragment1())
                        addToBackStack(null)
                        commit()
                    }
                }

            }
            else {
                Log.d("Brajmohan", "USER does not  EXists")
                Snackbar.make(contextView, "User Does Not Exists", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }


        }

    }
