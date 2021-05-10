package com.example.pokepedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentDetailBinding
import com.example.pokepedia.databinding.FragmentEmptyBinding
import com.example.pokepedia.databinding.FragmentEmptyPrincipalBinding

class EmptyFragment:Fragment(R.layout.fragment_empty_principal)  {
    private val losArgumentos: EmptyFragmentArgs by navArgs()
    private var _binding: FragmentEmptyPrincipalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEmptyPrincipalBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}