package com.example

import android.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomConfig
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomFragment


class LiveroomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(com.example.bolobuddies.R.layout.activity_liveroom)
        val roomIdTextView=findViewById<TextView>(com.example.bolobuddies.R.id.room_id_textview)
        roomIdTextView.text="Room ID :"+intent.getStringExtra("roomId")
        addFragment()
    }

    fun addFragment() {
        val appID: Long = Constants.appId
        val appSign: String = Constants.appsign
        val userID: String? = intent.getStringExtra("userId")

        val userName: String? = intent.getStringExtra("userId")
        val isHost = intent.getBooleanExtra("isHost", false)
        val roomID = intent.getStringExtra("roomId")
        val config: ZegoUIKitPrebuiltLiveAudioRoomConfig
        config = if (isHost) {
            ZegoUIKitPrebuiltLiveAudioRoomConfig.host()
        } else {
            ZegoUIKitPrebuiltLiveAudioRoomConfig.audience()
        }
        val fragment = ZegoUIKitPrebuiltLiveAudioRoomFragment.newInstance(
            appID, appSign, userID, userName, roomID, config
        )
        supportFragmentManager.beginTransaction()
            .replace(com.example.bolobuddies.R.id.fragment_container, fragment)
            .commitNow()
    }

}