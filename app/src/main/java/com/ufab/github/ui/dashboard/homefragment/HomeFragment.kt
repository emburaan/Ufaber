package com.ufab.github.ui.dashboard.homefragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ufab.github.BR
import com.ufab.github.R
import com.ufab.github.base.BaseFragment
import com.ufab.github.databinding.FragmentHomeBinding
import com.ufab.github.global.helper.ViewModelFactory
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeFragmentViewModel>(),HomeFragmentInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var homeAdapter:HomeAdapter2
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



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerBaseObserver(viewModel)
        registerHomeObserver()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHomeFragmentbinding = viewDataBinding as FragmentHomeBinding

        registerRecycler()

    }


        private fun registerRecycler(){
        homeAdapter.homeFragmentViewModel=viewModel
            mHomeFragmentbinding?.git?.adapter=homeAdapter

    }

    private fun registerHomeObserver() {
        viewModel.homedatas.observe(viewLifecycleOwner, Observer {
            homeAdapter.setData(it)
        })
    }



}