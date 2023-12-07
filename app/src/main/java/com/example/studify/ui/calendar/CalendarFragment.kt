package com.example.studify.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.example.studify.R
import com.example.studify.databinding.FragmentAddTimeTableDialogBinding
import com.example.studify.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment() {
    lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater,container,false)

        binding.idTVDate

        binding.calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener{view,year,month, dayOfMonth ->
                val Date = (dayOfMonth.toString() + "-"
                        + (month + 1) + "-" + year)

                // set this date in TextView for Display
                binding.idTVDate.setText(Date)
            }
        )


        return binding.root
    }
    fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
