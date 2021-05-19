package com.example.pokepedia.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentLoginBinding
import com.example.pokepedia.db.emtities.UserEntity
import com.example.pokepedia.viewmodels.LoginFunViewModel
import com.example.pokepedia.viewmodels.LoginViewModel
import com.jakewharton.rxbinding4.view.focusChanges
import com.jakewharton.rxbinding4.widget.checkedChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxRadioGroup
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding4.widget.checked
import io.reactivex.schedulers.Schedulers

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
//    private  val  viewModelLogin: LoginViewModel by viewModels()
    private val disposable = CompositeDisposable()
    lateinit var logicViewModel: LoginFunViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logicViewModel= ViewModelProvider(this).get(LoginFunViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.isEnabled = false

        disposable.add(
            RxView.focusChanges(binding.name)
                .subscribe{
                    hideKeyboard()
                }
        )
        disposable.add(
            RxRadioGroup.checkedChanges(binding.gender)
                .map { it}
                .subscribe{
                    hideKeyboard()
                    binding.name.clearFocus()
                    val gender = if(binding.gender.checkedRadioButtonId == R.id.male) "M" else "F"
                    logicViewModel.inputs.gender.onNext(gender)
                    logicViewModel.inputs.isChecked.onNext(binding.gender.checkedRadioButtonId != -1)
                }
        )
        disposable.add(
            RxTextView.textChanges(binding.name)
                .skipInitialValue()
                .map { it.toString() }
                .subscribe {
                    logicViewModel.inputs.name.onNext(it)
                }
        )
        disposable.add(
            RxView.clicks(binding.btnLogin)
                .subscribe{
                    logicViewModel.inputs.saveClicked.onNext(Unit)
                }
        )

        disposable.add(
            logicViewModel.outputs.isButtonEnabled
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
           /*         if(binding.gender.checkedRadioButtonId<=0){//Grp is your radio group object
                        binding.male.setError( "Selección requerida")
                    }*/
                    binding.btnLogin.isEnabled = it
                }
        )
        disposable.add(
            logicViewModel.outputs.nameError
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.name.error = if (it) "Campo requerido" else null
                }
        )
        disposable.add(
            logicViewModel.outputs.insertNewUser
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(it){
                        findNavController().popBackStack()
                    }
                }
        )
    /*    binding.btnLogin.setOnClickListener {
            val name = binding.name.text.toString()


            val user = UserEntity(name = name, gender = gender, isActive = true)



        }*/

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


