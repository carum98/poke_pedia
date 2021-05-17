package com.example.pokepedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentHomeBinding
import com.example.pokepedia.viewmodels.HomeViewModel
import com.example.pokepedia.viewmodels.LoginViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private  val  viewModelHome: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.inner_navhost_fragment) as NavHostFragment
        val navControl = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navControl)

        val user = viewModelHome.getUser()

        if (user === null) {
            findNavController().navigate(R.id.action_homeFragment3_to_loginFragment2)
        }
    }
}