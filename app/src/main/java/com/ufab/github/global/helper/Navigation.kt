package com.ufab.github.global.helper

import kotlin.reflect.KClass

class Navigation(val navigateTo: KClass<out Any>, val extra: Array<out Any>) {

    constructor(navigateTo: KClass<out Any>) : this(navigateTo, arrayOf<Any>())

    class Back

}