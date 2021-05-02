package com.example.myassignment_alivecor.ui.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myassignment_alivecor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    companion object {
        const val USER_FRAGMENT = "UserFragment"
        val USER_AGE_FRAGMENT = "UserAgeFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        loadFragment(UserFragment(), USER_FRAGMENT)
    }

     private fun loadFragment(fragment: Fragment, tag: String) {
         supportFragmentManager.beginTransaction()
                 .replace(mBinding.fragmentContainer.id, fragment, tag).commit()

    }
    fun loadFragmentWithBackStack(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .addToBackStack(tag)
                .replace(mBinding.fragmentContainer.id, fragment, tag).commit()

    }

   /* override fun onBackPressed() {
        AlertDialog.Builder(this)
                .setMessage("Close?")
                .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                    super.onBackPressed()
                }
                .setNegativeButton(android.R.string.cancel) { dialog, whichButton ->

                }
                .show()
    }*/
}

