package com.ufab.github.base


import com.ufab.github.data.db.Database
import com.ufab.github.data.retrofit.APIClient
import com.ufab.github.global.helper.SharedPreferences

/**
 * this is the base repository class, all project repositories should extends this class.
 */
abstract class BaseRepository(protected val apiClient: APIClient, protected val sharedPreferences: SharedPreferences, protected val database: Database)
