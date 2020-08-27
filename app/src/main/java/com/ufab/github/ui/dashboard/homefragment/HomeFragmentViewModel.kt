package com.ufab.github.ui.dashboard.homefragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.ufab.github.base.BaseAndroidViewModel
import com.ufab.github.data.model.home.HomeModel
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

    var homedatas:MutableLiveData<HomeModel2>

     var homedata:ArrayList<HomeModel2>

    var refreshState: MutableLiveData<State> = MutableLiveData()


   /* private val homesDataSourceFactory: HomeDataSourceFactory =
        HomeDataSourceFactory(disposable, schedulerProvider, homeRepository, FIRST_PAGE)*/

    init {

        onSignInClicked()
        val config = PagedList.Config.Builder().setPageSize(NEWS_PAGE_SIZE)
            .setInitialLoadSizeHint(NEWS_PAGE_SIZE * 2).setEnablePlaceholders(false).build()
        homedata=ArrayList()

        homedatas= MutableLiveData()

        DebugLog.d("DATA12345",homedata.toString())
        Log.d("homedata", homedatas.value?.get(1)?.assignees_url.toString())




        /*homedata = LivePagedListBuilder<Int, HomeModel>(homesDataSourceFactory, config).build()

        DebugLog.d("state12345",HomeDataSource::state.toString())*/

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

    private fun OnSignInSucess(): (HomeModel2) -> Unit = { homemodel ->
        Log.d("experiment12345","onsigninsucess")
        homedatas.value = homemodel

        Log.d("experiment12345","onsigninsucess")




    }

    private fun OnSignInFail(): (Throwable) -> Unit = { error ->
        Log.d("errrror",error.message)
        Log.d("experiment12345","onsigninsucess")
    }
/*    private fun onSucess(t1: List<HomeModel>?) {
        Log.d("onsucess",t1.toString())


        t1?.let { homedatas.value =it }

    }*/

    private fun onFailure(action: Throwable?) {
        Log.d("onfailure",action.toString())


    }


  /*  fun test(){
    *//*Flowable.just("Hello world")
        .subscribe { s -> println(s) }*//*

        Flowable.just("Hello world")
            .subscribe(
                { emittedData: String? ->
                    println(
                        emittedData
                    )
                }  // onNext
                // onError
            ) { throwable: Throwable -> throwable.printStackTrace() }
    }
*/
    /*  fun getState(): LiveData<State> = Transformations.switchMap<HomeDataSource, State>(
          homesDataSourceFactory.homeDataSourceLiveData,
          HomeDataSource::state

      )*/

/*
    fun onRefresh() {
        updateState(State.REFRESHING)
        compositeDisposable.add(
            homeRepository.gethome()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        //setupHome(response)
                       // getNavigator()!!.setupHome(response)
                        homesDataSourceFactory.homeDataSourceLiveData.value?.refreshData(response)
                    },
                    {
                        updateState(State.DONE)
                        shownSnackBarMessage(R.string.global_error_refresh_fail)
                    }
                )
        )
    }
*/


    /*fun isRefreshing(): LiveData<Boolean> = Transformations.map(refreshState) {
        !listIsEmpty() && it == State.REFRESHING
    }*/

  /*  fun isEmptyError(): LiveData<Boolean> = Transformations.map(getState()) {
        listIsEmpty() && it == State.ERROR
    }*/


   /* fun setupHome(data:List<HomeModel>){
        var hashmap = HashMap<String, ArrayList<HomeModel>>()
        for (homeData in data!!) {
            if (hashmap.containsKey(homeData.title)) {
                val list = hashmap[homeData.title]
                list!!.add(homeData)
                hashmap.put(homeData.title, list!!)
            } else {
                val list  = ArrayList<HomeModel>()
                list.add(homeData)
                hashmap.put(homeData.title,list!!)
            }
        }

        for((key, value) in hashmap){

            Log.d("values",value.toString())
            homedata.add(value)


        }
        Log.d("homedata",homedata.toString())

    }*/


   /* private fun listIsEmpty(): Boolean {
        return homedata.value?.isEmpty() ?: true
    }*/

   /* fun isEmptyLoading(): LiveData<Boolean> = Transformations.map(getState()) {
        listIsEmpty() && it == State.LOADING
    }*/


    private fun updateState(state: State) {
        refreshState.value = state
    }



    fun OnImageClicked(){
        showBlockProgressBar()
    }

    override fun onItemClicked(value: String) {

    }

    override fun onRetry() {

    }

}