package com.ufab.github.ui.contributorlist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ufab.github.data.model.contributor.ContributorModel
import com.ufab.github.global.listener.DataAdapterListener
import com.ufab.github.global.viewholder.ContributorViewHolder
import com.ufab.github.ui.contributorlist.ContributorViewModel

class ContributorAdapter(private val picasso: Picasso,var contributorViewModel: ContributorViewModel):RecyclerView.Adapter<ContributorViewHolder>(),DataAdapterListener<ContributorModel> {
    private val data = ContributorModel()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        return ContributorViewHolder.create(parent,contributorViewModel,picasso)     }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(data)
    }

    override fun setData(contributor: ContributorModel) {
        data.clear()
        data.addAll(contributor)
        notifyDataSetChanged()     }
}