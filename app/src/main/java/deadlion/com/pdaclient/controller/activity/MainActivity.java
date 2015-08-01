package deadlion.com.pdaclient.controller.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.VisibleFragment;


public class MainActivity extends AppCompatActivity {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getFragmentManager();
        manager.beginTransaction().add(R.id.container, new VisibleFragment()).commit();


    }
}
