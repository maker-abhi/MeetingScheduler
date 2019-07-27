package com.vinsol.meetingscheduler.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.vinsol.meetingscheduler.R
import com.vinsol.meetingscheduler.di.ViewModelFactory
import com.vinsol.meetingscheduler.extensions.formatDate
import com.vinsol.meetingscheduler.model.Status
import com.vinsol.meetingscheduler.viewmodel.ScheduleMeetingViewModel
import kotlinx.android.synthetic.main.fragment_schedule_meeting.*
import java.util.*
import javax.inject.Inject

class ScheduleMeetingFragment : BaseFragment(), DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ScheduleMeetingViewModel


    companion object {
        fun newInstance(): ScheduleMeetingFragment {
            return ScheduleMeetingFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ScheduleMeetingViewModel::class.java)
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
        tv_meeting_date.text = viewModel.calendar.time.formatDate()
        tv_start_time.text = viewModel.startTime.time.formatDate("HH:mm")
        tv_end_time.text = viewModel.startTime.time.formatDate("HH:mm")

        tv_back.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        tv_meeting_date.setOnClickListener {
            val newFragment = DatePickerFragment.newInstance(viewModel.calendar, minDate = Calendar.getInstance())
            newFragment.dateSetListener = this
            newFragment.show(fragmentManager, "datePicker")
        }
        tv_start_time.setOnClickListener {
            val newFragment = TimePickerFragment.newInstance(viewModel.startTime)
            newFragment.timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                tv_start_time.text = String.format(getString(R.string.time_format), hourOfDay, minute)
                viewModel.startTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                viewModel.startTime.set(Calendar.MINUTE, minute)
            }
            newFragment.show(fragmentManager, "startTime")
        }
        tv_end_time.setOnClickListener {
            val newFragment = TimePickerFragment.newInstance(viewModel.endTime)
            newFragment.timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                tv_end_time.text = String.format(getString(R.string.time_format), hourOfDay, minute)
                viewModel.endTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                viewModel.endTime.set(Calendar.MINUTE, minute)
            }
            newFragment.show(fragmentManager, "endTime")
        }

        btn_submit.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            viewModel.checkIfSlotAvailable()
        }

        viewModel.slotAvailabilityLiveData.observe(this, Observer {
            if (it?.status == Status.SUCCESS) {
                progress_bar.visibility = View.GONE
                val message = if (it.data == true) {
                    R.string.slot_available
                } else {
                    R.string.slot_unavailable
                }
                AlertDialog.Builder(context)
                    .setMessage(message)
                    .setPositiveButton("Okay") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        })
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.calendar.set(year, month, dayOfMonth)
        tv_meeting_date.text = viewModel.calendar.time.formatDate()
    }
}