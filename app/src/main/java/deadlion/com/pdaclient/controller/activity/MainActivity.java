package deadlion.com.pdaclient.controller.activity;

import android.app.FragmentManager;
import android.os.Bundle;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.ListAllFragment;


public class MainActivity extends ToolbarActivity {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.container, new ListAllFragment()).commit();
    }
}
