package com.ufab.github.data.repository.abs

import com.ufab.github.data.model.user.ProfileResponse
import com.ufab.github.data.model.user.User
import com.ufab.github.global.enumeration.Optional
import io.reactivex.Single

interface UserRepository {

    fun isLoggedInWithDelay(delay: Long): Single<Optional<User>>

    fun signInAndCache(email: String, password: String): Single<ProfileResponse>

    fun signUp(firstName: String, lastName: String, email: String, password: String, option: String): Single<ProfileResponse>

    fun isUserLoggedIn():Single<Boolean>
}
