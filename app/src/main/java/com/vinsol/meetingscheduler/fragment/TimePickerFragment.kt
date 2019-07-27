package com.vinsol.meetingscheduler.fragment

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.TimePicker
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    companion object {
        fun newInstance(defaultTime: Calendar = Calendar.getInstance()): TimePickerFragment {
            val args = Bundle()
            args.putSerializable("defaultTime", defaultTime)
            val fragment = TimePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var timeSetListener: TimePickerDialog.OnTimeSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the default date in the picker
        val c = arguments!!.getSerializable("defaultTime") as Calendar

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        timeSetListener?.onTimeSet(view, hourOfDay, minute)
    }
}