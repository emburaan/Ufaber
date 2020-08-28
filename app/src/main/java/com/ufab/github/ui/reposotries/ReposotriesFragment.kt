package com.ufab.github.ui.reposotries

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ufab.github.BR
import com.ufab.github.R
import com.ufab.github.base.BaseFragment
import com.ufab.github.databinding.FragmentContributorBinding
import com.ufab.github.databinding.FragmentHomeBinding
import com.ufab.github.databinding.FragmentReposotriesBinding
import com.ufab.github.global.helper.Navigation
import com.ufab.github.global.helper.ViewModelFactory
import com.ufab.github.ui.contributorlist.adapter.ContributorAdapter
import com.ufab.github.ui.reposotries.adapter.RepositoriesAdapter
import javax.inject.Inject


class ReposotriesFragment : BaseFragment<FragmentReposotriesBinding,RepositoriesFragmentViewModel>(),RepositoriesFragmentInterface {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var repoAdapter: RepositoriesAdapter
    private var mRepositoriesFragmentViewModel:RepositoriesFragmentViewModel? =null
    private var mRepoFragmentbinding: FragmentReposotriesBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_reposotries
    override val viewModel: RepositoriesFragmentViewModel
        get() {
            mRepositoriesFragmentViewModel =  ViewModelProviders.of(this, viewModelFactory).get(RepositoriesFragmentViewModel::class.java)
            return mRepositoriesFragmentViewModel!!
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRepoFragmentbinding = viewDataBinding as FragmentReposotriesBinding
        mRepositoriesFragmentViewModel?.setNavigator(this)
        registerRecycler()

        viewModel.getuserRepo(arguments?.getString("name").toString())

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerBaseObserver(viewModel)
        setcontent()
        registerHomeObserver()
    }

    private fun setcontent() {
        mRepoFragmentbinding?.profileName?.text = arguments?.getString("name").toString()
    }

    private fun registerHomeObserver() {
        viewModel.repoItem.observe(viewLifecycleOwner, Observer {
            repoAdapter.setData(it)

            mRepoFragmentbinding?.imageurl = it.get(0).owner.avatar_url
            mRepoFragmentbinding?.picasso = getPicasso()
            mRepoFragmentbinding?.placeholder = resources.getDrawable(R.drawable.profile)
        })
    }

    private fun registerRecycler() {

        repoAdapter.repositoriesFragmentViewModel=viewModel
        mRepoFragmentbinding?.rvRepolist?.adapter=repoAdapter

    }

    override fun onError(key: String) {
        Toast.makeText(activity,resources.getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show()
    }
}