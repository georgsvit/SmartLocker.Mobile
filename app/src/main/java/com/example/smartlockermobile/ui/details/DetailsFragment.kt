package com.example.smartlockermobile.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.smartlockermobile.R
import com.example.smartlockermobile.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        viewModel = DetailsViewModel(requireActivity().application)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.selectedTool.observe(viewLifecycleOwner, Observer {
            binding.toolId.text = getString(R.string.toolId) + ": " + it.Id
            binding.name.text = getString(R.string.tool_name) + ": " + it.Name
            binding.description.text = getString(R.string.description) + ": " + it.Description
            binding.accessLvl.text = getString(R.string.accessLevel) + ": " + it.AccessLevel
            binding.state.text = getString(R.string.tool_state) + ": " + it.ServiceState

            when (it.ServiceState) {
                "SERVICE_REQUIRED" -> {
                    binding.state.text = getString(R.string.tool_state) + ": " + getString(R.string.service_required)
                }
                "IN_SERVICE" -> {
                    binding.state.text = getString(R.string.tool_state) + ": " + getString(R.string.in_service)
                }
                "SERVICE_REQUESTED" -> {
                    binding.state.text = getString(R.string.tool_state) + ": " + getString(R.string.service_requested)
                }
                "SERVED" -> {
                    binding.state.text = getString(R.string.tool_state) + ": " + getString(R.string.served)
                }
            }

            val imgUri = it.ImgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(binding.avatar.context)
                .load(imgUri)
                .apply(RequestOptions().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image))
                .into(binding.avatar)
        })

        binding.queueBtn.setOnClickListener {
            viewModel.enterQueue()
        }

        viewModel.queueResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, getString(R.string.queue_success), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, getString(R.string.queue_fail), Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}