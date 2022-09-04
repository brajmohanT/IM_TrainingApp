package com.example.userapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.ViewModelProvider
import com.example.userapp.databinding.Fragment3Binding


class Fragment3 : Fragment() {

    private var _binding: Fragment3Binding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var contextView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.appBarLayout).visibility = View.GONE
        _binding = Fragment3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contextView = view.findViewById(R.id.frag3)

        val dao = PersonDB.getDB(requireActivity().applicationContext).personDao()
        val repository = Repository(dao)
        sharedViewModel =
            ViewModelProvider(requireActivity(), SharedViewModelFactory(repository)).get(
                SharedViewModel::class.java
            )

        binding.signUpBtn.setOnClickListener {
            saveUser()
        }

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
                        Log.d("Brajmohan", "USER ALREADY Exists")
                        Snackbar.make(contextView, "User Already Exists", Snackbar.LENGTH_SHORT)
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