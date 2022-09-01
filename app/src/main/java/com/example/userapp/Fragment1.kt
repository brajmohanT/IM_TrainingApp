package com.example.userapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async

class Fragment1 : Fragment() {
    private lateinit var database: PersonDB
    private lateinit var updatedPerson: LiveData<Person>
//    private var newId: Long? = null
    private val model: SharedViewModel by activityViewModels()
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

        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getFromstore(view)

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

//        model.id.observe(viewLifecycleOwner) { id ->
//            Log.d("Brajmohan", id.toString())
//                Log.d("Brajmohan", "its Runing")
//             //   updatedPerson = database.personDao().getPerson(id)
//             //   Log.d("Brajmohan", updatedPerson.toString())
//             //  fname.text = updatedPerson.value?.fName

//            }
        }
    }

