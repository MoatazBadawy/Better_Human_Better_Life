package com.moataz.better_human.better_life.presentation.base.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moataz.better_human.better_life.BR
import com.moataz.better_human.better_life.core.extensions.setWidthPercent

abstract class BaseDialogFragment<VDB : ViewDataBinding, VM : ViewModel>(private val layoutId: Int) :
    DialogFragment() {

    lateinit var viewModel: VM
    abstract val viewModelClass: Class<VM>

    private lateinit var _binding: VDB
    val binding: VDB get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()

        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding.apply {
            setVariable(BR.viewModel, this@BaseDialogFragment.viewModel)
            lifecycleOwner = this@BaseDialogFragment
            return root
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[viewModelClass]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(80)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.isCancelable = false
    }

}