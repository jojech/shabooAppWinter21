package edu.byui.cit.partygame.view;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import edu.byui.cit.partygame.R;
import edu.byui.cit.partygame.model.AppDatabase;
import edu.byui.cit.partygame.model.Entry;
import edu.byui.cit.partygame.model.EntryDAO;
import edu.byui.cit.partygame.model.Topic;
import edu.byui.cit.partygame.model.TopicDAO;

public final class MainFrag extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            super.onCreateView(inflater, container, savedInstState);

            // Load frag_main xml file
            view = inflater.inflate(R.layout.frag_main, container, false);
            seedDatabase();
            // Grab frag elements for use
            // New Game Button
            Button newGame = view.findViewById(R.id.newGame);
            newGame.setOnClickListener(new NewGameHandler());
            // Create Topic Button
            Button createTopic = view.findViewById(R.id.createTopic);
            createTopic.setOnClickListener(new CreateTopicHandler());
            // Settings Button
            Button settingButton = view.findViewById(R.id.gotoTopicMain);
            settingButton.setOnClickListener(new GoToTopicsHandler());
        }
        catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    // OnClickListener Classes
    private class NewGameHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                Toast.makeText(getActivity(),"New Game...",Toast.LENGTH_LONG);
                AppCompatActivity act = (AppCompatActivity)getActivity();
                Fragment frag = new RoundFrag();
                FragmentTransaction trans = act.getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragContainer, frag);

                // Add to back arrow
                trans.addToBackStack(null);
                trans.commit();
            }
            catch (Exception ex) {
                Log.e(MainActivity.TAG,ex.toString());
                Toast.makeText(getActivity(),"Failed to start new game...",Toast.LENGTH_LONG);
            }
        }
    }

    private class GoToTopicsHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                Toast.makeText(getActivity(),"Visiting Topics...",Toast.LENGTH_LONG);
                AppCompatActivity act = (AppCompatActivity)getActivity();
                Fragment frag = new TopicsFrag();
                FragmentTransaction trans = act.getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragContainer, frag);

                // Add to back arrow
                trans.addToBackStack(null);
                trans.commit();
            }
            catch (Exception ex) {
                Log.e(MainActivity.TAG,ex.toString());
                Toast.makeText(getActivity(),"Failed to go to Topics...",Toast.LENGTH_LONG);
            }
        }
    }

    private class CreateTopicHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                Toast.makeText(getActivity(),"Initiating Creation Process...",Toast.LENGTH_LONG).show();
                AppCompatActivity act = (AppCompatActivity)getActivity();
                Fragment frag = new CreateTopicFrag();
                FragmentTransaction trans = act.getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragContainer, frag);

                // Add to back arrow
                trans.addToBackStack(null);
                trans.commit();
            }
            catch (Exception ex) {
                Log.e(MainActivity.TAG,ex.toString());
                Toast.makeText(getActivity(),"Failed to initiate creation process...",Toast.LENGTH_LONG);
            }
        }
    }

    // Function to Seed the database
    private void seedDatabase() {
        Context appCtx = getActivity();
        AppDatabase db = AppDatabase.getInstance(appCtx);
        TopicDAO tdao = db.getTopicDAO();
        EntryDAO edao = db.getEntryDAO();

        // Insert Starter Topics
        final List<Topic> topicData;
        topicData = new ArrayList<>();
        topicData.addAll(tdao.getAllTopics());
        // Entries to insert
        String[] swEntries = {
                "Han Solo","Luke Skywalker","Leia Organa","Chewbacca","Dak","Wedge Antilles","Mon Mothma","Admiral Ackbar","Lando Calrissian",
                "Obi Wan Kenobi","C3P0","R2D2","Anakin Skywalker","Padme Amidala","Darth Vader","Darth Sidious","Grand Moff Tarkin",
                "Jar Jar Binks","Yoda","Qui Gon Jinn","Shmi","Jabba the Hutt","Boba Fett","Jango Fett","Bossk","Mace Windu","Darth Maul","Count Dooku"
        };
        String[] lotrEntries = {
                "Frodo","Samwise Gamgee","Merry","Peregrin Took","Boromir","Aragorn","Faramir","Denethor","Legolas","Gimli son of Gloin",
                "Gandalf","Mithrandir","Edoras","Helm's Deep","Fangorn","Isengard","Sauron","Saruman","Rohan","Osgiliath","Minas Tirith",
                "Dol Amroth","Shire","Galadriel","Elrond","Theoden","Theodred","Eowyn","Arwen","Eomer","Gamling","Grima Wormtongue",
                "Gollum","Smeagol","Bilbo Baggins","Radagast","The Witch-King of Angmar","Mordor","Easterlings","Shelob"
        };
        String[] rEntries = {
                "Redd's Grill", "Righteous Slice", "Red Rabbit Grill", "Frontier Pies", "Crossroads", "Rexburg Temple",
                "Runnin' 4 Sweets", "Hibbard", "K'Lani's", "Little Caesar's", "Millhollow Cafe", "Da Pineapple Grill",
                "Salmon", "Rigby", "Stone's", "Abracadabra's", "Yellowstone", "Mesa Falls"
        };
        // Topics to insert
        Topic sw = new Topic("Star Wars");
        Topic lotr = new Topic("Lord of the Rings");
        Topic rex = new Topic("Rexburg");
        if (topicData.isEmpty()) {
            tdao.insert(sw);
            tdao.insert(lotr);
            tdao.insert(rex);
            for (String entry:
                 swEntries) {
                edao.insert(new Entry(sw.getTopicKey(),entry));
            }
            for (String entry:
                    lotrEntries) {
                edao.insert(new Entry(lotr.getTopicKey(),entry));
            }
            for (String entry:
                    rEntries) {
                edao.insert(new Entry(rex.getTopicKey(),entry));
            }
            Toast.makeText(appCtx,"Seeding Database",Toast.LENGTH_LONG).show();
        }
//        else {
//            // Check to see if Data seed matches what's in the database
//            if (edao.countByTopicKey(tdao.getTopicKeyByName("Star Wars")) < swEntries.length) {
//                // TODO: add code to restore data seed
//            }
//        }
        // End if statement
    }//End seedDatabase function


}
