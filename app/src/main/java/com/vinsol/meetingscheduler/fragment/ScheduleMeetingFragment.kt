package com.vinsol.meetingscheduler.fragment

import android.app.TimePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinsol.meetingscheduler.viewmodel.MeetingsViewModel
import com.vinsol.meetingscheduler.R
import com.vinsol.meetingscheduler.di.ViewModelFactory
import com.vinsol.meetingscheduler.extensions.formatDate
import kotlinx.android.synthetic.main.fragment_schedule_meeting.*
import java.util.*
import javax.inject.Inject

class ScheduleMeetingFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MeetingsViewModel


    companion object {
        fun newInstance(): ScheduleMeetingFragment {
            return ScheduleMeetingFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(context as AppCompatActivity, viewModelFactory).get(MeetingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_meeting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        tv_back.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        tv_meeting_date.setOnClickListener {
        }
    }
}