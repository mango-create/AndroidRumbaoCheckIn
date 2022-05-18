package com.example.rumbaocheckin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements ICheckIn, ICheckedIn{

    ArrayList<String> guestList = new ArrayList<>();
    ArrayList<String> alreadyCheckedIn = new ArrayList<>();
    ArrayList<String> fullList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guestList.add("Captain America");
        guestList.add("Captain Marvel");
        guestList.add("Iron Man");
        guestList.add("Thor");
        guestList.add("Hulk");
        guestList.add("Ant-Man");
        guestList.add("Black Widow");
        guestList.add("Spider-Man");
        guestList.add("Black Panther");
        guestList.add("Doctor Strange");
        guestList.add("Shang-Chi");
        guestList.add("Wanda");
        guestList.add("Wong");
        guestList.add("Loki");
        guestList.add("Odin");
        guestList.add("Darcy");
        guestList.add("Vision");
        guestList.add("Thanos");
        guestList.add("Makkari :)))))");
        guestList.add("MJ");
        guestList.add("Ned");
        guestList.add("Aunt May, the hot one");
        guestList.add("Falcon");
        guestList.add("Heimdall");
        Collections.sort(guestList);

        for (String s: guestList){
            fullList.add(s);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CheckInFragment(guestList)).commit();
    }

    @Override
    public void launchCheckIn(int i) {
        if (i >= 0){
            alreadyCheckedIn.add(guestList.get(i));
            guestList.remove(i);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CheckInFragment(guestList)).commit();
    }

    @Override
    public void launchCheckedIn() {
        Collections.sort(alreadyCheckedIn);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CheckedInFragment(alreadyCheckedIn)).commit();
    }

    @Override
    public void launchFullList() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new FullListFragment(fullList)).commit();
    }

    @Override
    public void unCheckIn(int i) {
        if (i >= 0) {
            guestList.add(alreadyCheckedIn.get(i));
            Collections.sort(guestList);
            alreadyCheckedIn.remove(i);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CheckedInFragment(alreadyCheckedIn)).commit();

    }
}