package com.google.seergii_tymofieiev.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
abstract class BaseFragment : MvpAppCompatFragment(), BaseView {
    protected abstract val layoutRes: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDaggerDependency()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutRes, container, false)
    }

    abstract fun injectDaggerDependency()

    open fun onBackPressed() {}
}