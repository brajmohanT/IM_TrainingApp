package com.example.userapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import com.example.userapp.databinding.Fragment1Binding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.async

class Fragment1 : Fragment() {
    private lateinit var database: PersonDB
    private lateinit var updatedPerson: LiveData<Person>

//    private lateinit var binding : Fragment1Binding
//    private var newId: Long? = null
private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!


    private  lateinit var  person :Person

private lateinit var sharedViewModel: SharedViewModel
    lateinit var fname: TextView
    lateinit var lname: TextView
    private lateinit var phone: TextView
    private lateinit var email: TextView
    private lateinit var city: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = Fragment1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getFromstore(view)


        val dao=  PersonDB.getDB(requireActivity().applicationContext).personDao()
        val repository = Repository(dao)
        sharedViewModel = ViewModelProvider(this, SharedViewModelFactory(repository)).get(SharedViewModel::class.java)


        getFromRoom(view)
    }

    fun getFromstore(view: View) {

        fname = view.findViewById(R.id.ufName)
        lname = view.findViewById(R.id.ulName)
        phone = view.findViewById(R.id.uPhone)
        email = view.findViewById(R.id.uEmail)
        city = view.findViewById(R.id.uCity)


        val sharedPref = activity?.getSharedPreferences("user_info", Context.MODE_PRIVATE) ?: return

        fname.text = sharedPref.getString("fName", "Rickey")
        lname.text = sharedPref.getString("lName", "Ponting")
        phone.text = sharedPref.getString("phone", "9989796656")
        email.text = sharedPref.getString("email", "ponting@au.com")
        city.text = sharedPref.getString("city", "Melbourne")

    }

   fun getFromRoom(view: View) {
        fname = view.findViewById(R.id.ufName)

       sharedViewModel.id.observe(viewLifecycleOwner) {

           binding.person = sharedViewModel.getPerson(it).value!!

       }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

