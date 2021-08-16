package com.sevenlearn.nikestore.data.repo

import com.example.nikeshop.data.TokenContainer
import com.example.nikeshop.data.TokenResponse
import com.sevenlearn.nikestore.data.repo.source.UserDataSource
import com.sevenlearn.nikestore.data.repo.source.UserLocalDataSource
import io.reactivex.Completable

class UserRepositoryImpl(
    val userRemoteDataSource: UserDataSource,
    val userLocalDataSource: UserDataSource
) : UserRepository {
    override fun login(username: String, password: String): Completable {
        return userRemoteDataSource.login(username, password).doOnSuccess {
            onSuccessfulLogin(it)
        }.ignoreElement()
    }

    override fun signUp(username: String, password: String): Completable {
        return userRemoteDataSource.signUp(username, password).flatMap {
            userRemoteDataSource.login(username, password)
        }.doOnSuccess {
            onSuccessfulLogin(it)
        }.ignoreElement()
    }

    override fun loadToken() {
        userLocalDataSource.loadToken()
    }

    fun onSuccessfulLogin(tokenResponse: TokenResponse) {
        TokenContainer.update(tokenResponse.access_token, tokenResponse.refresh_token)
        userLocalDataSource.saveToken(tokenResponse.access_token, tokenResponse.refresh_token)
    }
}