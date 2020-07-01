package com.google.seergii_tymofieiev.presentation.base
import com.google.seergii_tymofieiev.R
/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
open class BaseFragmentError: BaseFragment(),
    BaseViewError {
    override val layoutRes: Int = R.layout.layout_container
    override fun injectDaggerDependency() {

    }

    override fun showHttpError(error: String?) {

    }
}