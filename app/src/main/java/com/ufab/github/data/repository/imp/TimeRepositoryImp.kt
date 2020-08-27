package com.ufab.github.data.repository.imp

import com.ufab.github.base.BaseRepository
import com.ufab.github.data.db.Database
import com.ufab.github.data.repository.abs.TimeRepository
import com.ufab.github.data.retrofit.APIClient
import com.ufab.github.global.helper.SharedPreferences
import com.ufab.github.global.utils.TIMER_PERIOD_SECOND
import com.ufab.github.global.utils.TIMER_TIME_SECOND
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class TimeRepositoryImp @Inject constructor(
        apiClient: APIClient,
        sharedPreferences: SharedPreferences,
        database: Database
) :
        BaseRepository(apiClient, sharedPreferences, database), TimeRepository {

    override fun timer(): Observable<Long> {
        return Observable.interval(TIMER_PERIOD_SECOND, TimeUnit.SECONDS)
                .take(TIMER_TIME_SECOND, TimeUnit.SECONDS)
    }
}
