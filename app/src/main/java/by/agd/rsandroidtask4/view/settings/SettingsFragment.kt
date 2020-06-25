package by.agd.rsandroidtask4.view.settings

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import by.agd.rsandroidtask4.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val resources = preferenceManager.context.resources

        val sortingFieldKey = resources.getString(R.string.pref_sorting_field_key)
        val sortingField: ListPreference? = findPreference(sortingFieldKey)
        sortingField?.summaryProvider = Preference.SummaryProvider<ListPreference> {
            resources.getString(R.string.summary_sorting_field_format, it.value)
        }

        val sortingOrderKey = resources.getString(R.string.pref_sorting_order_key)
        val sortingOrder = findPreference<ListPreference?>(sortingOrderKey)
        sortingOrder?.summaryProvider = Preference.SummaryProvider<ListPreference> {
            resources.getString(R.string.summary_sorting_order_format, it.value)
        }
    }
}