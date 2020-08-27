package com.ufab.github.global.listener

import com.ufab.github.global.enumeration.State

interface PaginationStateListener {
    fun setState(newState: State)
}
