package com.igdev.exampleapp.ui.checkin

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.igdev.exampleapp.R
import com.igdev.exampleapp.databinding.FragmentDialogCheckinBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CheckinDialogFragment @Inject constructor(
    private val eventId: String
) : DialogFragment() {
    //region Fields

    private val viewModel: CheckinDialogViewModel by viewModels()

    private lateinit var binding: FragmentDialogCheckinBinding

    //endregion

    //region Overrides

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle? ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_dialog_checkin, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventId = eventId

        setClickListeners()

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    //endregion

    //region Private Methods

    private fun setClickListeners() {
        binding.btCancel.setOnClickListener { dismiss() }
        binding.btSendOrder.setOnClickListener {
            viewModel.sendCheckin { dismiss() }
        }
    }

    //endregion
}