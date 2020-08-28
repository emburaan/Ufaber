package com.ufab.github.ui.contributorlist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ufab.github.data.model.contributor.ContributorModel
import com.ufab.github.data.model.contributor.ContributorModelItem
import com.ufab.github.global.listener.DataAdapterListener
import com.ufab.github.global.viewholder.ContributorViewHolder
import com.ufab.github.ui.contributorlist.ContributorViewModel

class ContributorAdapter(private val picasso: Picasso,var contributorViewModel: ContributorViewModel):RecyclerView.Adapter<ContributorViewHolder>(),DataAdapterListener<List<ContributorModelItem>> {
    private val data = ArrayList<ContributorModelItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        return ContributorViewHolder.create(parent,contributorViewModel,picasso)     }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(data)
    }



    override fun setData(contro: List<ContributorModelItem>) {
        data.clear()
        data.addAll(contro)
        notifyDataSetChanged()
    }


}