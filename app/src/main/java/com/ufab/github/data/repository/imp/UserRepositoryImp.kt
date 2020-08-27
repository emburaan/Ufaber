package com.ufab.github.data.repository.imp

import com.ufab.github.base.BaseRepository
import com.ufab.github.data.db.Database
import com.ufab.github.data.model.user.ProfileResponse
import com.ufab.github.data.model.user.User
import com.ufab.github.data.repository.abs.UserRepository
import com.ufab.github.data.retrofit.APIClient
import com.ufab.github.global.enumeration.Optional
import com.ufab.github.global.helper.SharedPreferences
import io.reactivex.Single
import javax.inject.Inject


class UserRepositoryImp @Inject constructor(
    apiClient: APIClient,
    sharedPreferences: SharedPreferences,
    database: Database
) :
    BaseRepository(apiClient, sharedPreferences, database), UserRepository {

    override fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        option: String
    ): Single<ProfileResponse> {
        return apiClient.signUpAndCache(email, password, firstName, lastName, option).map {
            sharedPreferences.saveUser(it.data)
            it
        }
    }

    override fun isUserLoggedIn(): Single<Boolean> {
        return Single.fromCallable {
            sharedPreferences.isConnected()

        }
    }

    override fun signInAndCache(email: String, password: String): Single<ProfileResponse> {
        return apiClient.signIn(email, password).map {
            sharedPreferences.saveUser(it.data)
            it
        }
    }

    override fun isLoggedInWithDelay(delay: Long): Single<Optional<User>> {
        return Single.fromCallable {
            Thread.sleep(delay)
            if (sharedPreferences.isConnected()) {
                Optional.Some(sharedPreferences.getUser())
            } else {
                Optional.None
            }
        }
    }
}
