package com.ufab.github.global.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.ufab.github.R
import com.ufab.github.global.listener.OnItemClickedListener
import com.squareup.picasso.Picasso
import com.ufab.github.data.model.home.HomeModel2
import com.ufab.github.databinding.ItemHomepageBinding

class HomeViewHolder(private val binding:ItemHomepageBinding,private val onItemClickedListener: OnItemClickedListener,private val picasso: Picasso):RecyclerView.ViewHolder(binding.root) {
    fun bind(home: List<HomeModel2>){

        Log.d("daaatahome_new",home.toString())
    binding.picasso=picasso
        binding.title=home.get(adapterPosition).name
        binding.project=home.get(adapterPosition).fullName
        binding.onItemClickedListner=onItemClickedListener
        binding.imageurl=home.get(adapterPosition).owner.avatarUrl
        binding.desctiption=home.get(adapterPosition).description
        binding.placeholder=AppCompatResources.getDrawable(binding.root.context, R.mipmap.ic_launcher)
    }

    companion object{
        fun create(parent:ViewGroup,onItemClickedListener: OnItemClickedListener,picasso: Picasso):HomeViewHolder{
            val inflater=LayoutInflater.from(parent.context)
            val binding= ItemHomepageBinding.inflate(inflater,parent,false)
            return HomeViewHolder(binding,onItemClickedListener,picasso)
        }
    }
}