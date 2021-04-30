package com.example.smartlockermobile.ui.tools

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.smartlockermobile.R
import com.example.smartlockermobile.databinding.FragmentToolsBinding
import com.example.smartlockermobile.ui.details.DetailsActivity

class ToolsFragment : Fragment() {
    private lateinit var viewModel: ToolsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentToolsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tools, container, false)

        viewModel = ToolsViewModel(requireActivity().application)
        binding.lifecycleOwner = this
        binding.toolsViewModel = viewModel
        binding.toolsGrid.adapter = ToolsGridAdapter(ToolsGridAdapter.OnClickListener {
            viewModel.saveToolIdToSP(it.Id)
            startActivity(Intent(context, DetailsActivity::class.java))
        })

        return binding.root
    }

    override fun onResume() {
        viewModel.globalGetTools()
        super.onResume()
    }
}