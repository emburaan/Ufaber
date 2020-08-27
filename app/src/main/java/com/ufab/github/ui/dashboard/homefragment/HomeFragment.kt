package com.ufab.github.ui.dashboard.homefragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufab.github.BR
import com.ufab.github.R
import com.ufab.github.base.BaseFragment
import com.ufab.github.data.model.home.HomeModel
import com.ufab.github.databinding.FragmentHomeBinding
import com.ufab.github.global.helper.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeFragmentViewModel>(),HomeFragmentInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var homeAdapter:HomeAdapter2
    private var dataHome:List<HomeModel>?=null
    private var mHomeFragmentViewModel: com.ufab.github.ui.dashboard.homefragment.HomeFragmentViewModel? = null
    private var mHomeFragmentbinding: FragmentHomeBinding? = null
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: com.ufab.github.ui.dashboard.homefragment.HomeFragmentViewModel
        get() {
            mHomeFragmentViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(com.ufab.github.ui.dashboard.homefragment.HomeFragmentViewModel::class.java)
            return mHomeFragmentViewModel!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        // Register the UI for XMLBinding
        val bind = FragmentHomeBinding.bind(view)
        bind.viewModel=viewModel
        bind.lifecycleOwner = viewLifecycleOwner


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerBaseObserver(viewModel)
        registerHomeObserver()
    }

    private fun registerHomeObserver(){
        viewModel.homedatas.observe(viewLifecycleOwner, Observer {
            setupHome(it)
        })
        //registerRecycler()

    }

  /*  override fun setupHome(key: String) {
        var view = LayoutInflater.from(context).inflate(R.layout.item_home_category,null)
        var textView = view.findViewById<TextView>(R.id.textview_categoryName)
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        textView.setText(key)
        recyclerView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = homeAdapter
        linearLayout_home.addView(view)    }*/

      fun setupHome(data:List<HomeModel>){
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
            // Log.e("Shop Category Name is :- "+key," Number of Shop is "+value.size.toString())
            var view = LayoutInflater.from(context).inflate(R.layout.item_home_category,null)
            var textView = view.findViewById<TextView>(R.id.textview_categoryName)
            var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
            textView.setText(key)
            Log.d("daaatahome",dataHome.toString())
            recyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter = homeAdapter
            dataHome=value
            homeAdapter.setData(value)

//            homeAdapter.setData(value)

            //linearLayout_home.addView(view)
        }




    }
   /* private fun registerRecycler(){
        homeAdapter.homeFragmentViewModel=viewModel
        rv_recyclerview.adapter=homeAdapter

    }*/



}