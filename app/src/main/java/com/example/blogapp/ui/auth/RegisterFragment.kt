package com.example.blogapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.blogapp.R
import com.example.blogapp.data.remote.auth.AuthDataSource
import com.example.blogapp.domain.home.auth.AuthRepoImpl
import com.example.blogapp.presentation.auth.AuthViewModel


class RegisterFragment : Fragment(R.layout.fragment_register) {


    private val ViewModel by viewModels<AuthViewModel> {
        AuthViewModel.AuthViewModelFactory(AuthRepoImpl(AuthDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


