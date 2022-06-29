package droid.maxander.rainbowkeyboard

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.preference.PreferenceManager

class MyService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        player = MediaPlayer.create(this, R.raw.sound_of_sands)
        player!!.isLooping = true
    }

    override fun onDestroy() {
        player!!.stop()
    }

    override fun onStart(intent: Intent, startid: Int) {
        val sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this)
        if (sharedPreferences.getBoolean("sound",true))
        player!!.start()
    }

    companion object {
        private const val TAG = "MyService"
        var player: MediaPlayer? = null
    }
}