package com.example.myassignment_alivecor.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myassignment_alivecor.repository.UserRepository

class AppViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(UserRepository()) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}