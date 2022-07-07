package com.example.englanguage.exoviewpager

import android.widget.TextView
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.SimpleExoPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.englanguage.R
import com.google.android.exoplayer2.MediaItem

class ChildFragment : Fragment() {
    private lateinit var textView: TextView
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var playerView: PlayerView
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaItem: MediaItem
    private lateinit var mp4: String
    private lateinit var text: String
    private lateinit var comment: String
    private lateinit var voca: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_child, container, false)
        playerView = view.findViewById(R.id.exoplayerview)
        textView = view.findViewById(R.id.textView)
        tv1 = view.findViewById(R.id.tv1)
        tv2 = view.findViewById(R.id.tv2)
            val bundle = arguments
        if (bundle != null) {
            mp4 = bundle!!.getString("mp4").toString()
            text = bundle.getString("text").toString()
            comment = bundle.getString("comment").toString()
            voca = bundle.getString("voca").toString()

            textView.text = text
            tv1.text = comment
            tv2.text = voca

            simpleExoPlayer = SimpleExoPlayer.Builder(context!!).build()
            playerView.player = simpleExoPlayer
            mediaItem = MediaItem.fromUri(mp4)
            simpleExoPlayer.addMediaItem(mediaItem)
            simpleExoPlayer.prepare()
            simpleExoPlayer.repeatMode = SimpleExoPlayer.REPEAT_MODE_ONE
            simpleExoPlayer.playWhenReady = false
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if (simpleExoPlayer != null) {
            simpleExoPlayer.playWhenReady = false;
            simpleExoPlayer.stop();
            simpleExoPlayer.seekTo(0);
        }
    }

    override fun onStop() {
        super.onStop()
        if (simpleExoPlayer != null) {
            simpleExoPlayer.playWhenReady = false;
            simpleExoPlayer.stop();
            simpleExoPlayer.seekTo(0);
        }
    }
}