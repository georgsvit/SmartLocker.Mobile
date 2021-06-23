package com.example.smartlockermobile.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.smartlockermobile.R
import com.example.smartlockermobile.databinding.FragmentNotificationsBinding
import com.example.smartlockermobile.ui.tools.ToolsGridAdapter

class NotificationsFragment : Fragment() {
    private lateinit var viewModel: NotificationsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentNotificationsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)

        viewModel = NotificationsViewModel(requireActivity().application)
        binding.lifecycleOwner = this
        binding.notificationsViewModel = viewModel
        binding.notificationsGrid.adapter = NotificationsGridAdapter(NotificationsGridAdapter.OnClickListener {
//            viewModel.saveToolIdToSP(it.Id)
//            startActivity(Intent(context, DetailsActivity::class.java))
        })

        return binding.root
    }

    override fun onResume() {
//        viewModel.globalGetTools()
        super.onResume()
    }
}