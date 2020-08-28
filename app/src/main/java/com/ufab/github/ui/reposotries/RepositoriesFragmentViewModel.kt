package com.ufab.github.ui.reposotries

import android.app.Application
import com.ufab.github.base.BaseAndroidViewModel
import com.ufab.github.data.repository.abs.HomeRepository
import com.ufab.github.global.listener.OnItemClickedListener
import com.ufab.github.global.listener.RetryListener
import com.ufab.github.global.listener.SchedulerProvider
import com.ufab.github.ui.dashboard.homefragment.HomeFragmentInterface
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RepositoriesFragmentViewModel
@Inject
constructor(
    application: Application,
    disposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    private  val homeRepository: HomeRepository
)
    : BaseAndroidViewModel<RepositoriesFragmentInterface>(application,disposable,schedulerProvider),
    OnItemClickedListener, RetryListener {


    override fun onRetry() {
    }

    override fun onItemClicked(data: String, fullname: String,name: String) {

    }
}