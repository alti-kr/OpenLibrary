package com.google.seergii_tymofieiev.presentation.ui.app_activity

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.seergii_tymofieiev.App
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.presentation.base.BaseActivity
import com.google.seergii_tymofieiev.presentation.base.BaseFragment
import com.google.seergii_tymofieiev.presentation.ui.app_activity.di.ActivityComponent.Companion.activityComponent
import com.google.seergii_tymofieiev.presentation.ui.app_activity.di.ActivityModule
import com.google.seergii_tymofieiev.presentation.ui.app_activity.di.PermissionsRepositoryModule
import com.google.seergii_tymofieiev.utils.Utils
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class AppActivity : BaseActivity(), MainView {

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.root) as? BaseFragment

    override val layoutRes = R.layout.activity_app
    @Inject
    @InjectPresenter
    lateinit var mPresenter: AppPresenter

    override fun injectDependency() {
        App.appComponent
            .apply {
                activityComponent = plusActivityComponent(
                    PermissionsRepositoryModule(this@AppActivity),
                    ActivityModule(this@AppActivity)
                )
                activityComponent?.inject(this@AppActivity)
            }
    }



    @ProvidePresenter
    fun providePresenter() = mPresenter



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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        mPresenter.onCreate()
    }
    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

}