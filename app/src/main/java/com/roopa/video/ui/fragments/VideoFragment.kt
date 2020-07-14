package com.roopa.video.ui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roopa.video.R
import com.roopa.video.supportclass.Constants
import com.roopa.video.supportclass.Constants.Companion.current_position
import com.roopa.video.supportclass.Constants.Companion.video_title
import com.roopa.video.supportclass.Constants.Companion.video_url
import com.roopa.video.supportclass.FullScreenMediaController
import com.roopa.video.ui.adapters.VideoAdapter

class VideoFragment : Fragment(),VideoAdapter.OnItemClickListener {

    lateinit var recyclerView: RecyclerView;
    lateinit var videoView: VideoView;
    lateinit var mediaController : MediaController

    lateinit var current_video: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_video, container, false)

        videoView = view.findViewById(R.id.videoView) as VideoView
        recyclerView = view.findViewById(R.id.recycleView) as RecyclerView
        mediaController = FullScreenMediaController(view.context,false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playVideo(current_position)

        setAdapter(view.context)

        mediaController.setPrevNextListeners({ //next button clicked
            if(current_position < video_url.size-1)
            {
                current_position++
                playVideo(current_position)
            }

        }) { //previous button clicked

            if(current_position > 0)
            {
                current_position --
                playVideo(current_position)
            }
        }

    }
    private fun playVideo(current_position: Int) {

        val currentVideo = video_url[current_position]

        val videoUri =
            Uri.parse(currentVideo)

        videoView.setVideoURI(videoUri)

        mediaController.setAnchorView(videoView)

        videoView.setMediaController(mediaController)
        videoView.start()

    }

    private fun setAdapter(context: Context) {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.setLayoutManager(linearLayoutManager)
        val videoAdapter = VideoAdapter(video_title, video_url,this)
        recyclerView.setAdapter(videoAdapter)

    }

    override fun OnItemClick(position: Int) {
        current_position = position
        playVideo(position)
    }

}