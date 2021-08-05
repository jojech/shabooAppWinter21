package edu.byui.cit.partygame.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.byui.cit.partygame.R;
import edu.byui.cit.partygame.model.AppDatabase;
import edu.byui.cit.partygame.model.Topic;
import edu.byui.cit.partygame.model.TopicDAO;

public class CreateTopicFrag extends Fragment {
    private EditText newTopicName;
    private Bundle bundle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            super.onCreateView(inflater,container,savedInstState);
            view = inflater.inflate(R.layout.frag_create_topic, container, false);

            newTopicName = view.findViewById(R.id.editTextCreateTopicName);
            bundle = savedInstState;
            Button createTopicNameButton = view.findViewById(R.id.createTopicNameButton);
            createTopicNameButton.setOnClickListener(new CreateTopicNameHandler());

        }
        catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    private class CreateTopicNameHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                Toast.makeText(getActivity(),"New Topic Added...",Toast.LENGTH_LONG);
                AppCompatActivity act = (AppCompatActivity)getActivity();
                Fragment frag = new AddEntryFrag();
                FragmentTransaction trans = act.getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragContainer, frag);

                // Insert new topic to database
                AppDatabase db = AppDatabase.getInstance(act);
                TopicDAO tdao = db.getTopicDAO();

                String topicName = newTopicName.getText().toString();
                Topic toInsert = new Topic(topicName);
                tdao.insert(toInsert);

                bundle.putString("topicName",toInsert.getTopicName());
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
