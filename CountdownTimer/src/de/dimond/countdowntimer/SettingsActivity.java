/*
 * Copyright (C) 2010 Jonathan Dimond
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.dimond.countdowntimer;

import android.app.AlertDialog;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.ListView;

public class SettingsActivity extends PreferenceActivity implements OnPreferenceClickListener {

	private static final String REFRESH_NOTICE_KEY = "REFRESH_NOTICE";
	private static final String ABOUT_NOTICE_KEY = "ABOUT_NOTICE";
	private static final String HELP_NOTICE_KEY = "HELP_NOTICE";
	private static final String VOLUME_SOURCE_KEY = "CTW_VOLUME_SOURCE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);

		/* Set up Volume noises */
		ListPreference volume = (ListPreference) findPreference(VOLUME_SOURCE_KEY);
		volume.setEntryValues(new String[] { Integer.toString(AudioManager.STREAM_RING),
				Integer.toString(AudioManager.STREAM_MUSIC), Integer.toString(AudioManager.STREAM_ALARM),
				Integer.toString(AudioManager.STREAM_NOTIFICATION) });

		Preference batteryNotice = findPreference(REFRESH_NOTICE_KEY);
		batteryNotice.setOnPreferenceClickListener(this);
		Preference aboutNotice = findPreference(ABOUT_NOTICE_KEY);
		aboutNotice.setOnPreferenceClickListener(this);
		Preference helpNotice = findPreference(HELP_NOTICE_KEY);
		helpNotice.setOnPreferenceClickListener(this);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if (preference.getKey().equals(REFRESH_NOTICE_KEY)) {
			showMessageBox(R.string.refresh_notice_title, R.string.refresh_notice_text);
		} else if (preference.getKey().equals(ABOUT_NOTICE_KEY)) {
			showMessageBox(R.string.about_title, R.string.about_text);
		} else if (preference.getKey().equals(HELP_NOTICE_KEY)) {
			showMessageBox(R.string.help_title, R.string.help_text);
		}
		return true;
	}

	private void showMessageBox(int title, int message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setPositiveButton(de.dimond.countdowntimer.R.string.ok, null);
		builder.setMessage(message);
		builder.create().show();
	}
}
