package com.ufab.github.global.listener

import android.icu.text.CaseMap
import com.ufab.github.data.model.home.HomeModel2

interface OnItemClickedListener {
    fun onItemClicked(data: String,fullname:String,title: String)
}
