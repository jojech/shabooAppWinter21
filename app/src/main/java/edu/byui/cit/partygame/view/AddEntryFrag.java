package edu.byui.cit.partygame.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Array;

import edu.byui.cit.partygame.R;
import edu.byui.cit.partygame.model.AppDatabase;
import edu.byui.cit.partygame.model.Entry;
import edu.byui.cit.partygame.model.EntryDAO;
import edu.byui.cit.partygame.model.Topic;
import edu.byui.cit.partygame.model.TopicDAO;

public class AddEntryFrag extends Fragment {
    private EditText newEntry;
    private Topic t;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            super.onCreateView(inflater,container,savedInstState);
            view = inflater.inflate(R.layout.frag_add_entry, container, false);

            newEntry = view.findViewById(R.id.editTextAddEntry);

            TextView topicName = view.findViewById(R.id.topicName);

            Context appCtx = getContext();
            AppDatabase db = AppDatabase.getInstance((AppCompatActivity)getActivity());
            TopicDAO tdao = db.getTopicDAO();
            Spinner topicSelect = view.findViewById(R.id.topicSpinnerAddEntry);
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                    appCtx, tdao.getAllTopics(),android.R.layout.simple_spinner_item
//            );
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            topicSelect.setAdapter(adapter);

            Button addEntryButton = view.findViewById(R.id.addEntryButton);
            addEntryButton.setOnClickListener(new AddEntryHandler());

        }
        catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    private class AddEntryHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                Toast.makeText(getActivity(),"New Entry Added...",Toast.LENGTH_LONG);
                AppCompatActivity act = (AppCompatActivity)getActivity();
                Fragment frag = new AddEntryFrag();
                FragmentTransaction trans = act.getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragContainer, frag);

                // Insert new entry/entries to database
                AppDatabase db = AppDatabase.getInstance(act);
                EntryDAO edao = db.getEntryDAO();

                String entry = newEntry.getText().toString();

                Entry toInsert = new Entry(t.getTopicKey(),entry);
                edao.insert(toInsert);

                // Add to back arrow
                trans.addToBackStack(null);
                trans.commit();
            }
            catch (Exception ex) {
                Log.e(MainActivity.TAG,ex.toString());
                Toast.makeText(getActivity(),"Failed to add topic...",Toast.LENGTH_LONG);
            }
        }
    }
}
