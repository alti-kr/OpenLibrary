package com.google.seergii_tymofieiev.utils

import android.os.SystemClock
import android.view.View
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
fun Navigator.setLaunchScreen(screen: SupportAppScreen) {
    applyCommands(
        arrayOf(
            BackTo(null),
            Replace(screen)
        )
    )
}

class SafeClickListener(
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {


    override fun onClick(v: View) {

        onSafeCLick(v)
    }
}

private var lastTimeClicked: Long = 0
private var defaultInterval: Int = 500
fun View.safeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        if (SystemClock.elapsedRealtime() - lastTimeClicked >= defaultInterval) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSafeClick(it)
        }
    }
    setOnClickListener(safeClickListener)
}

