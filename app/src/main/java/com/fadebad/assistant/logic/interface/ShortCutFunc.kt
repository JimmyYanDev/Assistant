package com.fadebad.assistant.logic.`interface`

import android.content.Context

/**
 * @author eli
 * @e-mail eliflichang@gmail.com
 */
interface ShortCutFunc {
    fun <T> get(context: Context) : T
    fun <T> set(context: Context, value: T)
}

/*
show_touch:
    Settings.System.SHOW_TOUCHES(show_touches)

pointer_location:
    Settings.System.POINTER_LOCATION(pointer_location)

layout:
    SystemProperties View.DEBUG_LAYOUT_PROPERTY(debug.layout)
    SystemPropPoker.getInstance().poke();

ANR:
    Settings.Secure.ANR_SHOW_BACKGROUND(anr_show_background)

AES:
    Settings.Secure, FreemeSettings.Secure.FREEME_CAN_SHOW_ERROR_DIALOGS(freeme_can_show_error_dialogs)

val oemLockManager = context?.getSystemService(Context.OEM_LOCK_SERVICE) as OemLockManager
val userManager = context?.getSystemService(Context.USER_SERVICE) as UserManager
val userHandle = UserHandle.of(UserHandle.myUserId()) as UserHandle
val oemUnlock = root.findViewById<Switch>(R.id.oemUnlockController)

val isEnabled = !oemLockManager?.isDeviceOemUnlocked && (oemLockManager?.isOemUnlockAllowedByCarrier &&
        !userManager?.hasBaseUserRestriction(UserManager.DISALLOW_FACTORY_RESET, userHandle))
oemUnlock.isEnabled = isEnabled
oemUnlock.isChecked = oemLockManager?.isOemUnlockAllowed
oemUnlock.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
    oemLockManager.setOemUnlockAllowedByUser(b)
}*/