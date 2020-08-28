package com.ufab.github.ui.contributorlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ReportFragment
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
import com.ufab.github.ui.reposotries.ReposotriesFragment
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

                if (it!=null) {
                    contributeAdapter.setData(it)

                        mContributorBinding?.imageurl = it.get(0).avatar_url
                    mContributorBinding?.picasso = getPicasso()
                    mContributorBinding?.placeholder = resources.getDrawable(R.drawable.profile)
                    mContributorBinding?.tvName?.text = it.get(0).login
                    //mContributorBinding.tvFullname.text = it.get(0).
                    //mContributorBinding.tvName.text = it.get(0).

                }else{
                    showSnackBar(resources.getString(R.string.error_something_went_wrong))
                }
            })

        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContributorBinding = viewDataBinding as FragmentContributorBinding
        mContributorViewModel?.setNavigator(this)
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


    override fun navigate(navigationTo: Navigation) {
        when (navigationTo.navigateTo) {
            ReposotriesFragment::class -> {
                var bundle = bundleOf("description" to navigationTo.extra[0])
                val actionTask1ToTask11 = ContributorFragmentDirections.actionContributorFragmentToReposotriesFragment(navigationTo.extra[0] as String)
                findNavController()?.navigate(actionTask1ToTask11)


            }
        }
    }

    override fun onError(key: String) {
        Toast.makeText(activity,resources.getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show()

    }

}