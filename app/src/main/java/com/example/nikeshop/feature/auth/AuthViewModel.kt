package com.example.nikeshop.feature.auth

import com.example.nikeshop.common.NikeViewModel
import com.sevenlearn.nikestore.data.repo.UserRepository
import io.reactivex.Completable

class AuthViewModel(private val userRepository: UserRepository): NikeViewModel() {

    fun login(email: String, password: String): Completable {
        progressBarLiveData.value = true
        progressBarLiveData.postValue(false)
        return userRepository.login(email, password)
    }

    fun signUp(email: String, password: String): Completable {
        progressBarLiveData.value = true
        progressBarLiveData.postValue(false)
        return userRepository.signUp(email, password)
    }
}