package edu.byui.cit.partygame.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import edu.byui.cit.partygame.R;

public class MainActivity extends AppCompatActivity {
    // Temp name
    public static final String TAG = "PartyGame";

    @Override
    protected void onCreate(Bundle savedInstState) {
        try {
            super.onCreate(savedInstState);
            setContentView(R.layout.activity_main);

            if (savedInstState == null) {
                // Create the main fragment and place it
                // as the first fragment in this activity.
                Fragment frag = new MainFrag();
                FragmentTransaction trans =
                        getSupportFragmentManager().beginTransaction();
                trans.add(R.id.fragContainer, frag);
                trans.commit();
            }
        }
        catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
    }
}