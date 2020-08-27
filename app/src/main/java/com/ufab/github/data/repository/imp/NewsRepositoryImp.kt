package com.ufab.github.data.repository.imp

import androidx.lifecycle.LiveData
import com.ufab.github.base.BaseRepository
import com.ufab.github.data.db.Database
import com.ufab.github.data.model.news.News
import com.ufab.github.data.repository.abs.NewsRepository
import com.ufab.github.data.retrofit.APIClient
import com.ufab.github.global.helper.SharedPreferences
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    apiClient: APIClient,
    sharedPreferences: SharedPreferences,
    database: Database
) : BaseRepository(apiClient, sharedPreferences, database), NewsRepository {

    override fun getNewsAndCache(page: Int, loadSize: Int): Single<List<News>> {
        return apiClient.getNews(page, loadSize).map {
            if (!it.isEmpty()) database.newsDao().insertAll(*it.toTypedArray())
            it
        }
    }

    override fun getCountSingle(): Single<Int> {
        return database.newsDao().getCountSingle()
    }

    override fun getAllNewsSingle(): Single<List<News>> {
        return database.newsDao().getAllOffersSingle()
    }

    override fun getAllNewsLiveData(): LiveData<List<News>> {
        return database.newsDao().getAllOffersLiveData()
    }


    override fun insertAll(vararg news: News): Single<Unit> {
        return Single.fromCallable {
            database.newsDao().insertAll(*news)
        }
    }

    override fun delete(news: News): Single<Unit> {
        return Single.fromCallable {
            database.newsDao().delete(news)
        }
    }
}