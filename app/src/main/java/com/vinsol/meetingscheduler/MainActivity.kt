package com.vinsol.meetingscheduler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vinsol.meetingscheduler.fragment.MeetingListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            launchMeetingsScreen()
        }
    }

    private fun launchMeetingsScreen() {
        val fragment = MeetingListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, null)
            .commit()
    }


}
