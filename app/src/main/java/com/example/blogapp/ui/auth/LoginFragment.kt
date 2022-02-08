package com.example.blogapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.blogapp.R
import com.example.blogapp.core.Result
import com.example.blogapp.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseAuth
import com.example.blogapp.databinding.FragmentLoginBinding
import com.example.blogapp.domain.home.auth.AuthRepoImpl
import com.example.blogapp.presentation.auth.AuthViewModel


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding : FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val ViewModel by viewModels<AuthViewModel>{
        AuthViewModel.AuthViewModelFactory(AuthRepoImpl(AuthDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()
        goToSignUpPage()
    }
    private fun isUserLoggedIn(){
        firebaseAuth.currentUser?.let{user ->
            if(user.displayName.isNullOrEmpty()){
                findNavController().navigate(R.id.action_loginFragment_to_setupProfileFragment)
            }else{
                findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
            }

        }
    }
    private fun doLogin(){
        binding.btnSignin.setOnClickListener{
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validationCredentials(email, password)
        }
    }
    private fun goToSignUpPage(){
        binding.btnSignin.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }
    }

    private fun validationCredentials(email : String, password : String) {
        if(email.isEmpty()){
            binding.editTextEmail.error = "Email is empty"
            return
        }
        if (password.isEmpty()){
            binding.editTextPassword.error = "Password is empty"
        }
    }
    private fun signIn(email : String , password: String){
        ViewModel.signIn(email,password).observe(viewLifecycleOwner, Observer{result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Bienvenido: ${result.data?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    if(result.data?.displayName.isNullOrEmpty()){
                        findNavController().navigate(R.id.action_loginFragment_to_setupProfileFragment)
                    }else{
                        findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
                    }
                }
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignin.isEnabled = true
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}