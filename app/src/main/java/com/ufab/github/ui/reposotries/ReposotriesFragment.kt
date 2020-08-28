package com.ufab.github.ui.reposotries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ufab.github.BR
import com.ufab.github.R
import com.ufab.github.base.BaseFragment
import com.ufab.github.databinding.FragmentHomeBinding
import com.ufab.github.databinding.FragmentReposotriesBinding
import com.ufab.github.global.helper.ViewModelFactory
import javax.inject.Inject


class ReposotriesFragment : BaseFragment<FragmentReposotriesBinding,RepositoriesFragmentViewModel>(),RepositoriesFragmentInterface {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var mRepositoriesFragmentViewModel:RepositoriesFragmentViewModel? =null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_reposotries
    override val viewModel: RepositoriesFragmentViewModel
        get() {
            mRepositoriesFragmentViewModel =  ViewModelProviders.of(this, viewModelFactory).get(RepositoriesFragmentViewModel::class.java)
            return mRepositoriesFragmentViewModel!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reposotries, container, false)
        // Register the UI for XMLBinding
        val bind = FragmentReposotriesBinding.bind(view)
        bind.viewModel=viewModel
        bind.lifecycleOwner = viewLifecycleOwner

        registerRecycler()
        return view
    }

    private fun registerRecycler() {

    }
}