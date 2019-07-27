package com.vinsol.meetingscheduler.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinsol.meetingscheduler.R
import com.vinsol.meetingscheduler.databinding.ItemMeetingBinding
import com.vinsol.meetingscheduler.extensions.formatDate
import com.vinsol.meetingscheduler.extensions.parseDate
import com.vinsol.meetingscheduler.network.Meeting

class MeetingAdapter
    : RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>() {

    var meetings: List<Meeting> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount() = meetings.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        return MeetingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_meeting, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        val meeting = meetings[position]
        val start = meeting.startTime.parseDate("HH:mm")?.formatDate("h:mm a")
        val end = meeting.endTime.parseDate("HH:mm")?.formatDate("h:mm a")

        holder.binding.meeting = meeting
        holder.binding.tvDateTime.text = holder.binding.tvDateTime.resources.getString(R.string.start_end_time, start, end)
    }

    class MeetingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemMeetingBinding = DataBindingUtil.bind(itemView)!!
    }
}