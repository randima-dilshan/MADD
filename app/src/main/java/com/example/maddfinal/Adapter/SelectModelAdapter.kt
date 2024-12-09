package com.example.maddfinal.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maddfinal.R
import com.example.maddfinal.databinding.ViewholderModelBinding

class SelectModelAdapter(val items:MutableList<String>):
    RecyclerView.Adapter<SelectModelAdapter.Viewholder>() {

    private var selectionPosition = -1
    private var lastSelectionPoition = -1
    private lateinit var context: Context

    inner class Viewholder (val binding: ViewholderModelBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectModelAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderModelBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SelectModelAdapter.Viewholder, position: Int) {
        holder.binding.modelTxt.text = items[position]

        holder.binding.root.setOnClickListener{
            lastSelectionPoition = selectionPosition
            selectionPosition = position
            notifyItemChanged(lastSelectionPoition)
            notifyItemChanged(selectionPosition)
        }

        if(selectionPosition==position){
            holder.binding.modelLayout.setBackgroundResource(R.drawable.green_bg_selected)
            holder.binding.modelTxt.setTextColor(context.resources.getColor(R.color.green))
        }else{
            holder.binding.modelLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.modelTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int = items.size
}