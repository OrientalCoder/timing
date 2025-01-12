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

class PandaAdapter(private val items: List<Panda>, val recyclerView: RecyclerView, val imgBig: ImageView) : RecyclerView.Adapter<PandaAdapter.ViewHolder>() {
    val TAG: String = "PandaAdapter"

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
        //currentItem.img?.let { holder.img.setImageResource(it) }
        Glide.with(holder.img.context)
            .load(currentItem.img)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadStarted(placeholder: Drawable?) {
                    Log.d(TAG, "onLoadStarted")
                    super.onLoadStarted(placeholder)
                    //holder.img.setImageDrawable(placeholder)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    val imageHeight: Float = resource.intrinsicHeight.toFloat()
                    val imageWidth : Float = resource.intrinsicWidth.toFloat()

                    val layoutParams = holder.itemView.layoutParams .apply {
                        this.width = ScreenUtils.getScreenWidth(holder.img.context) / 2 - 10
                        this.height = ((imageHeight / imageWidth) * this.width).toInt() + 10
                    }

                    Log.d("dsw", "img height: " + layoutParams.height)
                    holder.img.also {
                        it.setImageDrawable(resource)
                        it.scaleType = ImageView.ScaleType.FIT_CENTER
                        it.layoutParams.height = layoutParams.height
                        it.setOnClickListener{
                            recyclerView.visibility = View.GONE
                            imgBig.apply {
                                this.setImageDrawable(resource)
                                this.scaleType = ImageView.ScaleType.FIT_CENTER
                                this.visibility = View.VISIBLE
                                this.setOnClickListener() {
                                    this.visibility = View.GONE
                                    recyclerView.visibility = View.VISIBLE
                            }

                            }
                        }
                    }


                    layoutParams.height += holder.textView.height
                    holder.textView.text = currentItem.name
                }


            })
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    open class OnImgItemClick(var imgId : ImageView) : View.OnClickListener {
        override fun onClick(v: View?) {
        }

    }



}