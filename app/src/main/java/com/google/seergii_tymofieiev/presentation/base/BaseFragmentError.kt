package com.google.seergii_tymofieiev.presentation.base

import android.widget.Toast
import com.google.seergii_tymofieiev.App
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.presentation.common.LoadingPopupDialog
import com.google.seergii_tymofieiev.utils.Utils


/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
open class BaseFragmentError: BaseFragment(),
    BaseViewError {
    override val layoutRes: Int = R.layout.layout_container
    override fun injectDaggerDependency() {

    }



    override fun showNetError(error: String) {
        showToast(String.format(Utils.getStringById(R.string.error_network), error))
    }

    override fun showError(error: String) {
        showToast(error)
    }

    private fun showToast(value: String){
        val toast = Toast.makeText(
            App.getContext(),value, Toast.LENGTH_SHORT
        )
        toast.show()
    }

    override fun toggleLoadingView(onOff: Boolean) {
        mLoadingPopupDialog?.hide()
        if (onOff) {
            activity?.let {
                mLoadingPopupDialog = LoadingPopupDialog(it.layoutInflater)
                mLoadingPopupDialog?.show()
            }
        } else {
            mLoadingPopupDialog?.hide()
        }
    }
}