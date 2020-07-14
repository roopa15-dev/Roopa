package com.roopa.video.ui

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.roopa.video.R
import com.roopa.video.supportclass.Constants
import com.roopa.video.supportclass.Constants.Companion.current_position
import com.roopa.video.supportclass.Constants.Companion.video_url
import com.roopa.video.supportclass.FullScreenMediaController

class FullScreenVideoActivity : AppCompatActivity() {

    private var mediaController: MediaController? = null
    lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_video)

        videoView = findViewById(R.id.videoView)

        val fullScreen = intent.getStringExtra("fullScreenInd")
        if ("y" == fullScreen) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        else
        {
            finish()
        }

        mediaController = FullScreenMediaController(this,false)

        playVideo(current_position)

        (mediaController as FullScreenMediaController).setPrevNextListeners({ //next button clicked
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

        mediaController!!.setAnchorView(videoView)

        videoView.setMediaController(mediaController)
        videoView.start()

    }
}