package com.example.pokepedia.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentLoginBinding
import com.example.pokepedia.db.emtities.UserEntity
import com.example.pokepedia.viewmodels.LoginViewModel
import com.jakewharton.rxbinding4.view.focusChanges
import com.jakewharton.rxbinding4.widget.checkedChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private  val  viewModelLogin: LoginViewModel by viewModels()
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposable.add(
            binding.name.focusChanges().subscribe{
                hideKeyboard()

            }
        )
        disposable.add(
            binding.gender.checkedChanges().subscribe{
                hideKeyboard()
                binding.name.clearFocus()
            }
        )
        binding.btnLogin.setOnClickListener {
            val name = binding.name.text.toString()
            val gender = if(binding.gender.checkedRadioButtonId == R.id.male) "M" else "F"

            val user = UserEntity(name = name, gender = gender, isActive = true);
            val userDB = viewModelLogin.findUser(user)

            if (userDB != null) {
                userDB.isActive = true
                viewModelLogin.updateUser(userDB)
            } else {
                viewModelLogin.insertUser(user)
            }

            findNavController().popBackStack()
        }

    }
    fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
        _binding = null
    }
}