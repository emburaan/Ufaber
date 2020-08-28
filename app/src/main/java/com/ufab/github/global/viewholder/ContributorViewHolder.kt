package com.ufab.github.global.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ufab.github.R
import com.ufab.github.data.model.contributor.ContributorModel
import com.ufab.github.data.model.contributor.ContributorModelItem
import com.ufab.github.databinding.ItemContributorBinding
import com.ufab.github.databinding.ItemHomepageBinding
import com.ufab.github.global.listener.OnItemClickedListener

class ContributorViewHolder(private val binding:ItemContributorBinding,private val onItemClickedListener: OnItemClickedListener, private val picasso: Picasso):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data:List<ContributorModelItem>){
        binding.picasso=picasso
        binding.name=data.get(adapterPosition).login
        binding.empty=""
        binding.onItemClickedListner=onItemClickedListener
        binding.imageurl=data.get(adapterPosition).avatar_url
        binding.placeholder= AppCompatResources.getDrawable(binding.root.context, R.mipmap.ic_launcher)

    }
    companion object{
        fun create(parent: ViewGroup, onItemClickedListener: OnItemClickedListener, picasso: Picasso):ContributorViewHolder{
            val inflater= LayoutInflater.from(parent.context)
            val binding= ItemContributorBinding.inflate(inflater,parent,false)
            return ContributorViewHolder(binding,onItemClickedListener,picasso)
        }
    }
}