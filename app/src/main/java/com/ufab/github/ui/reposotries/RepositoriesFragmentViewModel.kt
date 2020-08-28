package com.ufab.github.ui.reposotries

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ufab.github.base.BaseAndroidViewModel
import com.ufab.github.data.model.contributor.ContributorModelItem
import com.ufab.github.data.model.repo.RepoModelItem
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
    var repoItem: MutableLiveData<List<RepoModelItem>>

    init {
        repoItem= MutableLiveData()
    }

    fun getuserRepo(name: String) {
        compositeDisposable.add(
            homeRepository.getUser(name)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(OnSignInSucess(), OnSignInFail())
        )
    }
    private fun OnSignInSucess(): (List<RepoModelItem>) -> Unit = { repoitems ->
        repoItem.value = repoitems




    }

    private fun OnSignInFail(): (Throwable) -> Unit = { error ->

        getNavigator()?.onError(error.message.toString())
    }


    override fun onRetry() {
    }

    override fun onItemClicked(data: String, fullname: String,name: String) {

    }
}