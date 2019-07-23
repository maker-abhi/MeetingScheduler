package com.vinsol.meetingscheduler.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.vinsol.meetingscheduler.R

class MeetingListFragment : Fragment() {
    private lateinit var rvMeetings: RecyclerView
    private lateinit var progressBar: ProgressBar

    companion object {
        fun newInstance(): MeetingListFragment {
            return MeetingListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meeting_list, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
    }

}