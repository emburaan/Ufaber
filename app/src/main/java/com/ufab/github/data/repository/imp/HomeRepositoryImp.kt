package com.ufab.github.data.repository.imp

import android.util.Log
import com.ufab.github.base.BaseRepository
import com.ufab.github.data.db.Database
import com.ufab.github.data.model.contributor.ContributorModelItem
import com.ufab.github.data.model.home.HomeModel2
import com.ufab.github.data.model.repo.RepoModel
import com.ufab.github.data.model.repo.RepoModelItem
import com.ufab.github.data.repository.abs.HomeRepository
import com.ufab.github.data.retrofit.APIClient
import com.ufab.github.global.helper.SharedPreferences
import io.reactivex.Single
import javax.inject.Inject

class HomeRepositoryImp @Inject
constructor(apiClient: APIClient, sharedPreferences: SharedPreferences, database: Database) :
    BaseRepository(apiClient, sharedPreferences, database), HomeRepository {
    override fun gethome(): Single<List<HomeModel2>> {
        return apiClient.gethome().map {
            var count = 1
            val tempList: ArrayList<HomeModel2> = ArrayList()
            for (i in it) {
                if (count <= 20) {
                    tempList.add(i)
                    count++
                }
            }
            tempList
        }
    }

    override fun getContributor(repo_name: String): Single<List<ContributorModelItem>> {
        return apiClient.getcontributor(repo_name).map {
            it
        }
    }

    override fun getUser(name: String): Single<List<RepoModelItem>> {
        return apiClient.getUser(name).map {
            Log.d("tagg1234", "123456")
            it
        }
    }
}