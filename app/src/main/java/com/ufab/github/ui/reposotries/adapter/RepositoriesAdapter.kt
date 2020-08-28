package com.ufab.github.ui.reposotries.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ufab.github.data.model.repo.RepoModel
import com.ufab.github.data.model.repo.RepoModelItem
import com.ufab.github.global.listener.DataAdapterListener
import com.ufab.github.global.viewholder.RepoViewHolder
import com.ufab.github.ui.reposotries.RepositoriesFragmentViewModel

class RepositoriesAdapter (private val picasso: Picasso, var repositoriesFragmentViewModel: RepositoriesFragmentViewModel):
    RecyclerView.Adapter<RepoViewHolder>(), DataAdapterListener<List<RepoModelItem>> {
    private val repo= ArrayList<RepoModelItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.create(parent,repositoriesFragmentViewModel,picasso)    }

    override fun getItemCount(): Int {
        return repo.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repo)
    }

    override fun setData(data: List<RepoModelItem>) {
        repo.clear()
        repo.addAll(data)
        notifyDataSetChanged()     }


}