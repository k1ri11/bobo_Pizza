package com.example.bobopizza.domain.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bobopizza.R
import com.example.bobopizza.databinding.SliderItemBinding

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    var sliderList = listOf(R.drawable.slider, R.drawable.slider)

    inner class SliderViewHolder(val binding: SliderItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = SliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(this.root).load(sliderList[position]).into(sliderImg)
        }
    }

    override fun getItemCount(): Int {
        return sliderList.size
    }
}