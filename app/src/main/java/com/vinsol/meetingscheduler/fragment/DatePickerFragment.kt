package com.vinsol.meetingscheduler.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import android.widget.TimePicker
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        fun newInstance(
            defaultDate: Calendar = Calendar.getInstance(),
            minDate: Calendar? = null,
            maxDate: Calendar? = null
        ): DatePickerFragment {
            val args = Bundle()
            args.putSerializable("defaultDate", defaultDate)
            args.putSerializable("minDate", minDate)
            args.putSerializable("maxDate", maxDate)
            val fragment = DatePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var dateSetListener: DatePickerDialog.OnDateSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the default date in the picker
        val c = arguments!!.getSerializable("defaultDate") as Calendar
        val minDate = arguments!!.getSerializable("minDate") as Calendar?
        val maxDate = arguments!!.getSerializable("maxDate") as Calendar?

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(activity, this, year, month, day)
        minDate?.let {
            dialog.datePicker.minDate = it.time.time
        }
        maxDate?.let {
            dialog.datePicker.maxDate = it.time.time
        }
        return dialog
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        dateSetListener?.onDateSet(view, year, month, day)
    }
}
