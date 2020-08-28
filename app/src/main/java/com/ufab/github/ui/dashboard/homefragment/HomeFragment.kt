package com.ufab.github.ui.dashboard.homefragment
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.ufab.github.BR
import com.ufab.github.R
import com.ufab.github.base.BaseFragment
import com.ufab.github.databinding.FragmentHomeBinding
import com.ufab.github.global.helper.Navigation
import com.ufab.github.global.helper.ViewModelFactory
import com.ufab.github.global.utils.ExtraKeys
import com.ufab.github.ui.contributorlist.ContributorFragment
import javax.inject.Inject
import javax.inject.Named


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeFragmentViewModel>(),HomeFragmentInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var homeAdapter:HomeAdapter2



    private lateinit var navController: NavController
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

    override fun navigate(navigationTo: Navigation) {
        when (navigationTo.navigateTo) {
            ContributorFragment::class -> {
                var bundle = bundleOf("description" to navigationTo.extra[0])
                val actionTask1ToTask11 = HomeFragmentDirections.actionHomeFragmentToContributorFragment(navigationTo.extra[0] as String,navigationTo.extra[1] as String,navigationTo.extra[2] as String)
                findNavController()?.navigate(actionTask1ToTask11)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeFragmentViewModel?.setNavigator(this)
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

    override fun onError(key: String) {


}


}