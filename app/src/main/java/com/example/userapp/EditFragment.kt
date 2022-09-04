package com.example.userapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.example.userapp.databinding.FragmentEditBinding
import com.google.android.material.snackbar.Snackbar

class EditFragment : Fragment() {

    private lateinit var contextView: View

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.appBarLayout).visibility = View.VISIBLE
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contextView = view.findViewById(R.id.editFrag)

        val dao = PersonDB.getDB(requireActivity().applicationContext).personDao()
        val repository = Repository(dao)
        sharedViewModel =
            ViewModelProvider(requireActivity(), SharedViewModelFactory(repository)).get(
                SharedViewModel::class.java
            )
        getFromRoom()

        binding.saveBtn.setOnClickListener {
            upDateProfile()
        }
    }

    private fun upDateProfile() {


        sharedViewModel._id.observe(viewLifecycleOwner) { it ->

            if (binding.ufName.text.length > 2 &&
                binding.ulName.text.length > 2 &&
                binding.uPhone.text.length == 10 &&
                binding.uCity.text.length > 2
            ) {
                sharedViewModel.updatePerson(
                    Person(
                        it,
                        binding.ufName.text.toString(),
                        binding.ulName.text.toString(),
                        binding.uPhone.text.toString(),
                        binding.uEmail.text.toString(),
                        binding.uCity.text.toString(),
                    )
                )
                goToProfile()
            } else {
                Snackbar.make(contextView, "use correct formats", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

    }


    private fun goToProfile() {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_cv, Fragment1())
            addToBackStack(null)
            commit()
        }
    }

    private fun getFromRoom() {
        sharedViewModel._id.observe(viewLifecycleOwner) { it ->

            Log.d("Id", it.toString())
            sharedViewModel.getPerson(it).observe(viewLifecycleOwner) {
                binding.person = it
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

