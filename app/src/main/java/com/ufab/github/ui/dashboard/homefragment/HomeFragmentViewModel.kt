package com.ufab.github.ui.dashboard.homefragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.ufab.github.base.BaseAndroidViewModel
import com.ufab.github.data.model.home.HomeModel2
import com.ufab.github.data.repository.abs.HomeRepository
import com.ufab.github.global.enumeration.State
import com.ufab.github.global.listener.OnItemClickedListener
import com.ufab.github.global.listener.RetryListener
import com.ufab.github.global.listener.SchedulerProvider
import com.ufab.github.global.utils.DebugLog
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


private const val NEWS_PAGE_SIZE = 10
private const val FIRST_PAGE = 0;


class HomeFragmentViewModel
    @Inject
    constructor(
        application: Application,
        disposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider,
        private  val homeRepository: HomeRepository
    )
    :BaseAndroidViewModel<HomeFragmentInterface>(application,disposable,schedulerProvider),OnItemClickedListener,RetryListener {

     //var homedata:LiveData<PagedList<HomeModel>>

    var homedatas:MutableLiveData<List<HomeModel2>>

     var homedata:ArrayList<HomeModel2>

    var refreshState: MutableLiveData<State> = MutableLiveData()




    init {

        onSignInClicked()
        val config = PagedList.Config.Builder().setPageSize(NEWS_PAGE_SIZE)
            .setInitialLoadSizeHint(NEWS_PAGE_SIZE * 2).setEnablePlaceholders(false).build()
        homedata=ArrayList()

        homedatas= MutableLiveData()

        DebugLog.d("DATA12345",homedata.toString())






    }



    fun onSignInClicked() {
        Log.d("experiment123","onsigninclickede")
            compositeDisposable.add(
                homeRepository.gethome()
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(OnSignInSucess(),OnSignInFail()))
                    /*.subscribe({ t1: List<HomeModel>? -> onSucess(t1)}){t2: Throwable? ->onFailure(t2)  })*/
            

    }

    private fun OnSignInSucess(): (List<HomeModel2>) -> Unit = { homemodel ->
        homedatas.value = homemodel
Log.d("sucess1234","onsigninsucess")




    }

    private fun OnSignInFail(): (Throwable) -> Unit = { error ->

    }

    override fun onItemClicked(value: String) {

    }

    override fun onRetry() {
    }


}