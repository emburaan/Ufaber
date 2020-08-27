package com.ufab.github.ui.dashboard

import android.app.Application
import com.ufab.github.base.BaseAndroidViewModel
import com.ufab.github.global.listener.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DashboardViewModel @Inject
constructor(
        application: Application,
        disposables: CompositeDisposable,
        schedulerProvider: SchedulerProvider
) :
        BaseAndroidViewModel<DashboardNavigator>(application, disposables, schedulerProvider) {


        fun onActionOneClicked() {
                shownSnackBarMessage("Oneclicked")
        }

        fun onActionTwoClicked() {
                shownSnackBarMessage("Twoclicked")

        }

        fun onActionThreeClicked() {
                shownSnackBarMessage("Threeclicked")

        }



}
