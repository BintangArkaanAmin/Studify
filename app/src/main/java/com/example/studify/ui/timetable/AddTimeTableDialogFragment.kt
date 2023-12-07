package com.example.studify.ui.timetable

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.studify.R
import com.example.studify.databinding.FragmentAddTimeTableDialogBinding
import java.util.Calendar


class AddTimeTableDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    lateinit var binding: FragmentAddTimeTableDialogBinding
    var jam = 0
    var menit = 0
    var waktuBol = true;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTimeTableDialogBinding.inflate(inflater, container, false)


        val languages = resources.getStringArray(R.array.days)

        if (binding.spinner != null) {
            val adapter = ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_spinner_item, languages
            )
            binding.spinner.adapter = adapter

            binding.spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?, position: Int, id: Long
                ) {
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        binding.TP1.setOnClickListener {
            waktuBol = true
            fSetWaktu()
        }

        binding.TP2.setOnClickListener {
            waktuBol = false
            fSetWaktu()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnAdd.setOnClickListener {
            dismiss()
        }

        return binding.root
    }


    override fun onStart() {
        super.onStart()
        var dialog: Dialog = getDialog()!!
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        return dialog
    }

    fun fSetWaktu() {
        getWaktuSaatIni()
        TimePickerDialog(requireActivity(), this, jam, menit, true).show()
    }

    private fun getWaktuSaatIni() {
        val kal: Calendar = Calendar.getInstance()
        jam = kal.get(Calendar.HOUR_OF_DAY)
        menit = kal.get(Calendar.MINUTE)
    }

    override fun onTimeSet(view: TimePicker?, HourOfDay: Int, minute: Int) {
        jam = HourOfDay
        menit = minute

        var jamS = jam.toString()
        var menitS = menit.toString()

        if (jamS.length == 1) {
            jamS = "0" + jamS

        }

        if (menitS.length == 1) {
            menitS = "0" + menitS
        }

        if (waktuBol) {
            binding.TP1.text = "${jamS}:${menitS}"
        } else {
            binding.TP2.text = "${jamS}:${menitS}"
        }
    }

}



