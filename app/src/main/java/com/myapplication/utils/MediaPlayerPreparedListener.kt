package com.myapplication.utils

import android.media.MediaPlayer

interface MediaPlayerPreparedListener {

    fun onPrepared(mp: MediaPlayer)
}