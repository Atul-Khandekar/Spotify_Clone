package com.example.spotifyclone.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.spotifyclone.databinding.FragmentNowPlayingBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNowPlayingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListeners()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                setUpFullHeight(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setUpFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun setUpClickListeners() {
        binding.apply {
            toggleState(btnPausePlay)
            toggleState(btnRepeat)
            toggleState(btnShuffle)
            toggleState(btnLike)

            toolBar.setNavigationOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }

    private fun toggleState(item: View) {
        item.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}