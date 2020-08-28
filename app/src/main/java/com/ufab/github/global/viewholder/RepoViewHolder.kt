package com.ufab.github.global.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ufab.github.R
import com.ufab.github.data.model.repo.RepoModel
import com.ufab.github.data.model.repo.RepoModelItem
import com.ufab.github.databinding.ItemHomepageBinding
import com.ufab.github.databinding.ItemRepoBinding
import com.ufab.github.global.listener.OnItemClickedListener

class RepoViewHolder (private val binding: ItemRepoBinding, private val onItemClickedListener: OnItemClickedListener, private val picasso: Picasso):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: List<RepoModelItem>){

        binding.picasso=picasso
        binding.title=data.get(adapterPosition).name
        binding.project=data.get(adapterPosition).full_name
        binding.onItemClickedListner=onItemClickedListener
        binding.imageurl=data.get(adapterPosition).owner.avatar_url
        binding.placeholder= AppCompatResources.getDrawable(binding.root.context, R.mipmap.ic_launcher)
    }

    companion object{
        fun create(parent: ViewGroup, onItemClickedListener: OnItemClickedListener, picasso: Picasso):RepoViewHolder{
            val inflater= LayoutInflater.from(parent.context)
            val binding= ItemRepoBinding.inflate(inflater,parent,false)
            return RepoViewHolder(binding,onItemClickedListener,picasso)
        }
    }

}