package com.example.myassignment_alivecor.ui.view

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.core.text.bold
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

            val spannableFirstName = SpannableStringBuilder()
                .append("${activity?.resources?.getString(R.string.first_name)}")
                .bold { append(" ${sharedViewModel.user?.firstName}") }
            val spannableLastName = SpannableStringBuilder()
                .append("${activity?.resources?.getString(R.string.last_name)}")
                .bold { append(" ${sharedViewModel.user?.lastName}") }
            val spannableAge = SpannableStringBuilder()
                .append("${activity?.resources?.getString(R.string.age)}")
                .bold { append(" ${sharedViewModel.user?.dob}") }

            mBinding.textViewFirstName.text = spannableFirstName
            mBinding.textViewLastName.text = spannableLastName
            mBinding.textViewAge .text = spannableAge
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