package by.agd.rsandroidtask4.view.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import by.agd.rsandroidtask4.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}