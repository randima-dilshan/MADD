package com.example.maddfinal.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maddfinal.Model.ItemsModel
import com.example.maddfinal.databinding.ViewholderCartBinding
import com.example.maddfinal.Helper.ChangeNumberItemsListener
import com.example.maddfinal.Helper.ManagmentCart
import java.util.ArrayList

class CartAdapter(
    private val listItemSelected:ArrayList<ItemsModel>,
    context: Context
    ,var changeNumberItemsListener: ChangeNumberItemsListener
): RecyclerView.Adapter<CartAdapter.Viewholder>() {
    class Viewholder(val binding:ViewholderCartBinding):RecyclerView.ViewHolder(binding.root)

    private val managmentCart= ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
        val binding=ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item=listItemSelected[position]

        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachTime.text="$${item.price}"
        holder.binding.totalEachitem.text="$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text=item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.pic)

        holder.binding.plusCartBtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected,position,object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                        changeNumberItemsListener.onChanged()
                }
            })

        }

        holder.binding.minusCartBtn.setOnClickListener {
            managmentCart.minusItem(listItemSelected,position,object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()
                }
            })

        }
    }

    override fun getItemCount(): Int =listItemSelected.size
}