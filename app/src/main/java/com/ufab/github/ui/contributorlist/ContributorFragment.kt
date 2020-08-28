package com.ufab.github.ui.contributorlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ufab.github.BR
import com.ufab.github.R
import com.ufab.github.base.BaseFragment
import com.ufab.github.databinding.FragmentContributorBinding
import com.ufab.github.databinding.FragmentHomeBinding
import com.ufab.github.global.helper.ViewModelFactory
import com.ufab.github.ui.contributorlist.adapter.ContributorAdapter
import com.ufab.github.ui.dashboard.homefragment.HomeFragment
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_contributor, container, false)
        // Register the UI for XMLBinding
        val bind = FragmentContributorBinding.bind(view)
        bind.viewModel = viewModel
        bind.lifecycleOwner = viewLifecycleOwner

        registerRecycler()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerBaseObserver(viewModel)

    }

    private fun registerRecycler() {
        contributeAdapter.contributorViewModel=viewModel
        git.adapter=contributeAdapter

    }
}