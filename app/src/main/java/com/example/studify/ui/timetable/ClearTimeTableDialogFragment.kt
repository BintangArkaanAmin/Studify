package com.example.studify.ui.timetable

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.studify.R
import com.example.studify.databinding.FragmentClearTimeTableDialogBinding
import com.example.studify.databinding.FragmentTimeTableBinding


class ClearTimeTableDialogFragment : DialogFragment() {
    lateinit var binding: FragmentClearTimeTableDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClearTimeTableDialogBinding.inflate(inflater,container,false);


        binding.YesTV.setOnClickListener{
            dismiss()

        }

        binding.NoTV.setOnClickListener{
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

}