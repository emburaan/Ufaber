package com.ufab.github.ui.contributorlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.ufab.github.BR
import com.ufab.github.R
import com.ufab.github.base.BaseFragment
import com.ufab.github.databinding.FragmentContributorBinding
import com.ufab.github.databinding.FragmentHomeBinding
import com.ufab.github.global.helper.Navigation
import com.ufab.github.global.helper.ViewModelFactory
import com.ufab.github.ui.contributorlist.adapter.ContributorAdapter
import com.ufab.github.ui.dashboard.homefragment.HomeFragment
import com.ufab.github.ui.dashboard.homefragment.HomeFragmentDirections
import com.ufab.github.ui.dashboard.homefragment.HomeFragmentInterface
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class ContributorFragment
    : BaseFragment<FragmentContributorBinding, ContributorViewModel>(),
    ContributorFragmentInterface {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var contributeAdapter:ContributorAdapter


    private var mContributorViewModel: ContributorViewModel? = null
    private var mContributorBinding:FragmentContributorBinding?=null
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_contributor
    override val viewModel: ContributorViewModel
        get() {
            mContributorViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(ContributorViewModel::class.java)
            return mContributorViewModel!!

        }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerBaseObserver(viewModel)
        registerHomeObserver()
    }

    private fun registerHomeObserver() {

            viewModel.contributordata.observe(viewLifecycleOwner, Observer {
                contributeAdapter.setData(it)
                mContributorBinding?.imageurl=it.get(0).avatar_url
                getPicasso()
                   // mContributorBinding?.picasso
                mContributorBinding?.tvName?.text = it.get(0).login
                //mContributorBinding.tvFullname.text = it.get(0).
                //mContributorBinding.tvName.text = it.get(0).

            })

        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContributorBinding = viewDataBinding as FragmentContributorBinding
        setContent()
        registerRecycler()

        viewModel.getRepo(arguments?.getString("project").toString())

    }

    private fun setContent() {
        mContributorBinding?.tvDescription?.text=arguments?.getString("description")
        mContributorBinding?.tvProject?.text=arguments?.getString("project")
        Log.d("name123",arguments?.getString("name"))

    }

    private fun registerRecycler() {
        contributeAdapter.contributorViewModel=viewModel
        mContributorBinding!!.contributor.adapter=contributeAdapter

    }


//    override fun navigate(navigationTo: Navigation) {
//        when (navigationTo.navigateTo) {
//            ContributorFragment::class -> {
//           /*     val actionTask1ToTask11 = HomeFragmentDirections.actionHomeFragmentToContributorFragment()
//                findNavController()?.navigate(actionTask1ToTask11)*/
//
//
//            }
//        }
//    }

}