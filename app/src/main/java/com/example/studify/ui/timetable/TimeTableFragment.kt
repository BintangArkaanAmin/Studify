package com.example.studify.ui.timetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.studify.R
import com.example.studify.databinding.FragmentTimeTableBinding
import com.github.tlaabs.timetableview.Schedule
import com.github.tlaabs.timetableview.TimetableView

class TimeTableFragment : Fragment() {

    lateinit var binding: FragmentTimeTableBinding
    private lateinit var addTimeTableDialogFragment: AddTimeTableDialogFragment
    private lateinit var clearTimeTableDialogFragment: ClearTimeTableDialogFragment
    private lateinit var editTimeTableDialogFragment: EditTimeTableDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeTableBinding.inflate(inflater,container,false);

        clearTimeTableDialogFragment = ClearTimeTableDialogFragment()
        addTimeTableDialogFragment = AddTimeTableDialogFragment()
        editTimeTableDialogFragment = EditTimeTableDialogFragment()


        binding.timetable.setOnStickerSelectEventListener { idx, schedules ->
            Toast.makeText(requireActivity(), idx.toString(), Toast.LENGTH_SHORT).show()
            editTimeTableDialogFragment.show(parentFragmentManager,"")
        }

        val schedules = ArrayList<Schedule>()
        val schedule = Schedule()
        schedule.classTitle = "Data Structure" // sets subject

        schedule.classPlace = "IT-601" // sets place

        schedule.professorName = "Won Kim" // sets professor

        schedule.day = 4

        schedule.startTime = com.github.tlaabs.timetableview.Time(10, 0) // sets the beginning of class time (hour,minute)

        schedule.endTime = com.github.tlaabs.timetableview.Time(13, 30) // sets the end of class time (hour,minute)


        schedules.add(schedule)

        binding.timetable.add(schedules)

        binding.addBtn.setOnClickListener {
            addTimeTableDialogFragment.show(parentFragmentManager, "")
        }

        binding.clearBtn.setOnClickListener {
            clearTimeTableDialogFragment.show(parentFragmentManager, "")
        }




        return binding.root
    }

}
