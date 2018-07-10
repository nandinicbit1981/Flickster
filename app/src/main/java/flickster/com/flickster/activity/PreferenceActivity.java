package flickster.com.flickster.activity;

import android.os.Bundle;

import flickster.com.flickster.R;

public class PreferenceActivity extends android.preference.PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }




}
