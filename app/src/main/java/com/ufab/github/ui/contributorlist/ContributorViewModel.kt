package com.ufab.github.ui.contributorlist

import android.app.Application
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ReportFragment
import com.ufab.github.R
import com.ufab.github.base.BaseAndroidViewModel
import com.ufab.github.data.model.contributor.ContributorModel
import com.ufab.github.data.model.contributor.ContributorModelItem
import com.ufab.github.data.model.home.HomeModel2
import com.ufab.github.data.repository.abs.HomeRepository
import com.ufab.github.global.helper.Navigation
import com.ufab.github.global.listener.OnItemClickedListener
import com.ufab.github.global.listener.RetryListener
import com.ufab.github.global.listener.SchedulerProvider
import com.ufab.github.global.utils.ExtraKeys
import com.ufab.github.ui.dashboard.homefragment.HomeFragmentViewModel
import com.ufab.github.ui.reposotries.ReposotriesFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class ContributorViewModel
@Inject
constructor(
    application: Application,
    disposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    private  val homeRepository: HomeRepository
)
    : BaseAndroidViewModel<ContributorFragmentInterface>(application,disposable,schedulerProvider),
    OnItemClickedListener, RetryListener {
    var contributordata: MutableLiveData<List<ContributorModelItem>>
    init {
        contributordata =MutableLiveData()
    }


    override fun onRetry() {
    }

    fun getRepo(repo_name:String) {
        Log.d("experiment123","onsigninclickede")
        compositeDisposable.add(
            homeRepository.getContributor(repo_name)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(OnSignInSucess(),OnSignInFail()))
        /*.subscribe({ t1: List<HomeModel>? -> onSucess(t1)}){t2: Throwable? ->onFailure(t2)  })*/


    }
    private fun OnSignInSucess(): (List<ContributorModelItem>) -> Unit = { contributor ->
        contributordata.value = contributor



    }

    private fun OnSignInFail(): (Throwable) -> Unit = { error ->

        getNavigator()?.onError(error.message.toString())
    }



    override fun onItemClicked(data: String, fullname: String,name:String) {
        navigate(
            Navigation(
                ReposotriesFragment::class,
                arrayOf(name, ContributorViewModel::class.java)
            )
        )      }
}