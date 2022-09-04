package com.example.userapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import retrofit2.Callback
import retrofit2.Response


class Fragment2 : Fragment() {

    private lateinit var userList: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().findViewById<View>(R.id.appBarLayout).visibility = View.VISIBLE
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

    }

    private fun getData() {
        val data = UserService.userInstance.getUsers(1)

        data.enqueue(object : Callback<ApiData> {
            override fun onResponse(call: retrofit2.Call<ApiData>, response: Response<ApiData>) {
                val data: ApiData? = response.body()
                if (data != null) {
                    userList = view!!.findViewById(R.id.userList)

                    userList.adapter = UserAdapter(this@Fragment2, data.users)

                    userList.layoutManager = LinearLayoutManager(this@Fragment2.context)
                }
            }

            override fun onFailure(call: retrofit2.Call<ApiData>, t: Throwable) {
                Log.d("BRAJMOHAN", "Getting some error", t)
                userList = view!!.findViewById(R.id.userList)
                Snackbar.make(userList, "Error Occurred", Snackbar.LENGTH_SHORT)
                    .show()
            }
        })
    }


}