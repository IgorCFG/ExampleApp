package com.igdev.exampleapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.igdev.exampleapp.R
import com.igdev.exampleapp.databinding.ActivityMainBinding
import com.igdev.exampleapp.extensions.hide
import com.igdev.exampleapp.extensions.show
import com.igdev.exampleapp.managers.interfaces.IViewManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //region Properties

    @Inject
    lateinit var viewManager: IViewManager

    private lateinit var binding: ActivityMainBinding

    private var isBackButtonBlocked: Boolean = false

    //endregion

    //region Overrides

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        setObservers()
    }

    override fun onBackPressed() {
        if (isBackButtonBlocked) return
        super.onBackPressed()
    }

    //endregion

    //region Private Methods

    private fun setObservers() {
        viewManager.getLoading().observe(this, {
            if (it.isNotEmpty()) {
                isBackButtonBlocked = true
                showLoading(it)
            } else {
                hideLoading()
                isBackButtonBlocked = false
            }
        })

        viewManager.getSnackbar().observe(this, this::showSnackbar)
    }

    private fun showLoading(message: String) {
        runOnUiThread {
            binding.loadingView.loadingText.text = message
            binding.loadingView.root.show()
        }
    }

    private fun hideLoading(message: String = "") {
        runOnUiThread {
            binding.loadingView.root.hide()
            binding.loadingView.loadingText.text = ""

            if (message.isNotEmpty())
                showSnackbar(message)
        }
    }

    private fun showSnackbar(message: String, length: Int = Snackbar.LENGTH_LONG) =
        Snackbar.make(binding.mainView, message, length).show()

    //endregion
}