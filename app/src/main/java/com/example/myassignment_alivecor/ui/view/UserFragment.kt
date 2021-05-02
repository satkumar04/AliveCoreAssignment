package com.example.myassignment_alivecor.ui.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myassignment_alivecor.R
import com.example.myassignment_alivecor.databinding.FragmentUserBinding
import com.example.myassignment_alivecor.model.User
import com.example.myassignment_alivecor.ui.viewmodel.AppViewModelFactory
import com.example.myassignment_alivecor.ui.viewmodel.SharedViewModel

class UserFragment : Fragment(), View.OnClickListener {

    private lateinit var mBind: FragmentUserBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun handleOnBackPressed() {
                activity?.let {
                    AlertDialog.Builder(it)
                            .setMessage("Are sure want to close the application?")
                            .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                               it.finishAndRemoveTask()
                            }
                            .setNegativeButton(android.R.string.cancel) { dialog, whichButton ->

                            }
                            .show()
                }
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
        mBind = FragmentUserBinding.inflate(inflater, container, false)
        return mBind.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(
                requireActivity(),
                AppViewModelFactory()
        ).get(SharedViewModel::class.java)

        mBind.datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            sharedViewModel.year = year
            sharedViewModel.dayOfMonth = dayOfMonth
            sharedViewModel.monthOfYear = monthOfYear
        }
        sharedViewModel.year = mBind.datePicker.year
        sharedViewModel.monthOfYear = mBind.datePicker.month
        sharedViewModel.dayOfMonth = mBind.datePicker.firstDayOfWeek

        mBind.containedButton.setOnClickListener(this)



    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.containedButton -> {
                if (::sharedViewModel.isInitialized) {
                    val user = User(mBind.editTextFirstName.text.toString(),mBind.editTextLastName.text.toString(),"")
                    sharedViewModel.user = user
                    (activity as MainActivity).loadFragmentWithBackStack(UserAgeFragment(),MainActivity.USER_AGE_FRAGMENT)
                }
            }

        }
    }

}