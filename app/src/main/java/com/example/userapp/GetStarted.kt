package com.example.userapp

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.userapp.databinding.FragmentEditBinding
import com.example.userapp.databinding.FragmentGetStartedBinding


class GetStarted : Fragment() {

    private var _binding: FragmentGetStartedBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().findViewById<View>(R.id.appBarLayout).visibility = View.GONE
        _binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gLogIn.setOnClickListener{
            fragmentChange(LogInFragment())
        }
        binding.gSignUp.setOnClickListener{
            fragmentChange(Fragment3())
        }
    }

    private fun fragmentChange(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_cv, fragment )
            addToBackStack(null)
            commit()
        }
    }
}