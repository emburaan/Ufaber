package com.ufab.github.global.helper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class NonNullLiveData<T : Any>(initValue: T) : MutableLiveData<T>() {

    init {
        value = initValue
    }

    override fun getValue(): T {
        return super.getValue()!!
    }

    fun observe(owner: LifecycleOwner, body: (T) -> Unit) {
        observe(owner, Observer<T> { t -> body(t!!) })
    }

}