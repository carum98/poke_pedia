package com.example.pokepedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentUserBinding
import com.example.pokepedia.viewmodels.UserViewModel


class UserFragment : Fragment(R.layout.fragment_user) {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private  val  viewModelUser: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = viewModelUser.getUser()
        binding.name.text = user.name
        binding.imageProfile.setImageDrawable(
            ContextCompat.getDrawable(
            view.context,
            if (user.gender == "M") R.drawable.user_male else R.drawable.user_female
        ))

        binding.btnLogout.setOnClickListener {
            user.isActive = false
            viewModelUser.updateUser(user)
            var action = UserFragmentDirections.actionUserFragmentToLoginFragment()
            this.findNavController().navigate(action)
        }
    }
}