package com.ufab.github.global.listener

import com.ufab.github.global.utils.DebugLog
import com.ufab.github.global.utils.TAG

/**
 * Created on 2/2/18.
 */

interface ToolBarListener {

    fun onStartClicked() {
        DebugLog.d(TAG, "onStartClicked but not handled")
    }

    fun onEndClicked() {
        DebugLog.d(TAG, "onStartClicked but not handled")
    }
}
