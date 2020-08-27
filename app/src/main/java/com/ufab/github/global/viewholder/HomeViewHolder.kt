package com.ufab.github.global.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.ufab.github.R
import com.ufab.github.data.model.home.HomeModel
import com.ufab.github.databinding.ItemHomeBinding
import com.ufab.github.global.listener.OnItemClickedListener
import com.squareup.picasso.Picasso

class HomeViewHolder(private val binding:ItemHomeBinding,private val onItemClickedListener: OnItemClickedListener,private val picasso: Picasso):RecyclerView.ViewHolder(binding.root) {
    fun bind(home: ArrayList<HomeModel>){

        Log.d("daaatahome_new",home.toString())
    binding.picasso=picasso
        binding.title=home.get(adapterPosition).title
        binding.time=home.get(adapterPosition).time
        binding.onItemClickedListner=onItemClickedListener
        binding.imageurl=home.get(adapterPosition).image
        binding.placeholder=AppCompatResources.getDrawable(binding.root.context, R.mipmap.ic_launcher)
    }

    companion object{
        fun create(parent:ViewGroup,onItemClickedListener: OnItemClickedListener,picasso: Picasso):HomeViewHolder{
            val inflater=LayoutInflater.from(parent.context)
            val binding= ItemHomeBinding.inflate(inflater,parent,false)
            return HomeViewHolder(binding,onItemClickedListener,picasso)
        }
    }
}