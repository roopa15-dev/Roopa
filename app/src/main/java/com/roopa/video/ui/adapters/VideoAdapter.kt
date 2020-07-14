package com.roopa.video.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.roopa.video.R
import com.roopa.video.supportclass.Constants


class VideoAdapter(videoTitle: Array<String>, videoUrl: Array<String>,onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private var titles: Array<String> = videoTitle
    private var links: Array<String> = videoUrl
    private var onItemClickListeners:OnItemClickListener =onItemClickListener
//
//    fun VideoAdapter(
//        title: Array<String>,
//        link: Array<String>
//    ) {
//        titles = title
//        links = link
//    }

    class ViewHolder(itemView: View,onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView
        var imageView : ImageView
        lateinit var onItemClickListener : OnItemClickListener

        init {
            titleTextView = itemView.findViewById<View>(R.id.txt_title) as TextView
            imageView = itemView.findViewById<View>(R.id.imageTitle) as ImageView
            this.onItemClickListener = onItemClickListener

            itemView.setOnClickListener(View.OnClickListener {
                onItemClickListener.OnItemClick(adapterPosition)
            })
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_list,parent,false)

        return ViewHolder(view,onItemClickListeners)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val title = titles[position]
        holder.titleTextView.setText(title)
        val drawable = TextDrawable.builder()
            .buildRect(title.get(0).toString(), Color.RED)
        holder.imageView.setImageDrawable(drawable)

    }

    public interface OnItemClickListener{
        fun OnItemClick( position : Int)
    }
}