/*
 * Ad Free
 * Copyright (c) 2017 by abertschi, www.abertschi.ch
 * See the file "LICENSE" for the full license governing this code.
 */

package ch.abertschi.adfree.model

import android.content.Context
import android.content.SharedPreferences
import org.jetbrains.anko.AnkoLogger
import java.util.*

/**
 * Created by abertschi on 15.04.17.
 */

class PreferencesFactory(context: Context) : AnkoLogger {
    private val prefsKey = "PREFS"
    private val prefIsEnabled = "IS_ENABLED"
    private val prefsLastUpdateInServiceDate = "LAST_UPDATE_IN_SERVICE"
    private val prefsFirstRun = "FIRST_RUN"
    private val prefsAudioVolume: String = "AUDIO_KEY"
    private val prefsActivePlugin: String = "ACTIVE_PLUGIN"

    private val prefs: SharedPreferences = context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)

    fun isBlockingEnabled(): Boolean {
        return prefs.getBoolean(prefIsEnabled, true)
    }

    fun setBlockingEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(prefIsEnabled, enabled).commit()
    }

    fun getLastUpdateInServiceDate(): Date {
        return Date(prefs.getLong(prefsLastUpdateInServiceDate, Date().time) * 1000)
    }

    fun setLastUpdateInServiceDate(date: Date) {
        prefs.edit().putLong(prefsLastUpdateInServiceDate, date.time).commit()
    }

    fun setFirstRun() {
        prefs.edit().putBoolean(prefsFirstRun, true).commit()
    }

    fun isFirstRun(): Boolean = prefs.getBoolean(prefsFirstRun, false)

    fun storeAudioVolume(volume: Int)
            = prefs.edit().putInt(prefsAudioVolume, volume).commit()

    fun loadAudioVolume(): Int =
            prefs.getInt(prefsAudioVolume, 100)

    @Deprecated("Dont use shared prefs outside this class anymore")
    fun getPreferences(): SharedPreferences = prefs

    fun getActivePlugin(): String {
        return prefs.getString(prefsActivePlugin, null)
    }

    fun setActivePlugin(plugin: String) {
        prefs.edit().putString(prefsActivePlugin, plugin).commit()
    }
}