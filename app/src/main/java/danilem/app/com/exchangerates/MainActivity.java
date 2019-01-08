package danilem.app.com.exchangerates;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment topFragment = getFragmentManager().findFragmentById(R.id.topFragment);
        Fragment botFragment = getFragmentManager().findFragmentById(R.id.botFragment);

    }
}
