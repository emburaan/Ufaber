package com.ufab.github.data.repository.abs

import com.ufab.github.data.model.contributor.ContributorModelItem
import com.ufab.github.data.model.home.HomeModel2
import io.reactivex.Single

interface HomeRepository {
    fun gethome():Single<List<HomeModel2>>

    fun getContributor(repo_name:String):Single<List<ContributorModelItem>>
}