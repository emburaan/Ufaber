package com.ufab.github.data.repository.abs

import com.ufab.github.data.model.home.HomeModel
import com.ufab.github.data.model.home.HomeModel2
import io.reactivex.Single

interface HomeRepository {
    fun gethome():Single<HomeModel2>
}