package com.dsw.timing.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.dsw.timing.R
import com.dsw.timing.ScreenUtils
import com.dsw.timing.bean.Panda

class PandaAdapter(private val items : List<Panda>) : RecyclerView.Adapter<PandaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView = itemView.findViewById(R.id.txt_panda)
        val img : ImageView = itemView.findViewById(R.id.image_panda)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.panda_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.textView.text = currentItem.name
        //holder.img.setImageResource(currentItem.img)
        Glide.with(holder.img.context)
            .load(currentItem.img)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadStarted(placeholder: Drawable?) {
                    super.onLoadStarted(placeholder)
                    holder.img.setImageDrawable(placeholder)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    val imageHeight: Float = resource.intrinsicHeight.toFloat()
                    val imageWidth : Float = resource.intrinsicWidth.toFloat()


                    val layoutParams = holder.itemView.layoutParams
                    layoutParams.width = ScreenUtils.getScreenWidth(holder.img.context) / 2 - 10
                    layoutParams.height = ((imageHeight / imageWidth) * layoutParams.width).toInt()
                    currentItem.height = layoutParams.height
                    Log.d("dsw", "img height: " + layoutParams.height)
                    holder.itemView.layoutParams = layoutParams

                    holder.img.setImageDrawable(resource)
                    holder.img.scaleType = ImageView.ScaleType.FIT_CENTER
                }


            })
    }

    override fun getItemCount(): Int {
        return items.count()
    }



}