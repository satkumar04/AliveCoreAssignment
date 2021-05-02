package com.example.myassignment_alivecor.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassignment_alivecor.model.User
import com.example.myassignment_alivecor.repository.UserRepository

class SharedViewModel(private val userRepository: UserRepository) : ViewModel() {
    val dobLiveData = MutableLiveData<User>()
    var user: User? = null
    var year: Int = 0
    var monthOfYear: Int = 0
    var dayOfMonth: Int = 0

    fun getAge() {
        userRepository.getAge(year, monthOfYear, dayOfMonth)
        { userage ->
            user?.apply {
                dob = userage
            }
            dobLiveData.value = user
        }

    }
}