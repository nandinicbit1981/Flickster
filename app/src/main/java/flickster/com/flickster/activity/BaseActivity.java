package flickster.com.flickster.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import flickster.com.flickster.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));

    }
}
