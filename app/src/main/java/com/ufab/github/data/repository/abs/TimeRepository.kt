package com.ufab.github.data.repository.abs

import io.reactivex.Observable

interface TimeRepository {

    fun timer(): Observable<Long>
}
