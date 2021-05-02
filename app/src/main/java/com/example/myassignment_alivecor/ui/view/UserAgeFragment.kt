package com.example.myassignment_alivecor.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myassignment_alivecor.R
import com.example.myassignment_alivecor.databinding.FragmentUserAgeBinding
import com.example.myassignment_alivecor.ui.viewmodel.AppViewModelFactory
import com.example.myassignment_alivecor.ui.viewmodel.SharedViewModel


class UserAgeFragment : Fragment(),View.OnClickListener {
    private lateinit var mBinding: FragmentUserAgeBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.popBackStackImmediate()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentUserAgeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(
                requireActivity(),
                AppViewModelFactory()
        ).get(SharedViewModel::class.java)

        sharedViewModel.dobLiveData.observe(this, Observer {
            mBinding.textViewFirstName.text = "${activity?.resources?.getString(R.string.first_name)} ${sharedViewModel.user?.firstName}"
            mBinding.textViewLastName.text = "${activity?.resources?.getString(R.string.last_name)} ${sharedViewModel.user?.lastName}"
            mBinding.textViewAge .text = "${activity?.resources?.getString(R.string.age)} ${sharedViewModel.user?.dob}"
        })
        mBinding.backButton.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        sharedViewModel.getAge()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backButton -> {
                activity?.supportFragmentManager?.popBackStackImmediate()
            }

        }
    }
}