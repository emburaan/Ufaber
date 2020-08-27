package com.ufab.github.data.repository.imp

import android.util.Log
import com.ufab.github.base.BaseRepository
import com.ufab.github.data.db.Database
import com.ufab.github.data.model.home.HomeModel
import com.ufab.github.data.model.home.HomeModel2
import com.ufab.github.data.repository.abs.HomeRepository
import com.ufab.github.data.retrofit.APIClient
import com.ufab.github.global.helper.SharedPreferences
import io.reactivex.Single
import javax.inject.Inject

class HomeRepositoryImp @Inject
constructor(apiClient: APIClient, sharedPreferences: SharedPreferences, database: Database) :
    BaseRepository(apiClient, sharedPreferences, database),HomeRepository {
    override fun gethome(): Single<HomeModel2> {
        return apiClient.gethome().map {
            Log.d("tagg1234","123456")
            it
        }
    }
}