package com.example.rumbaocheckin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CheckInFragment extends Fragment {
    Button checkInButton, checkedInButton, fullListButton;
    ArrayList<String> fragGuestList;
    ListView listView;
    ArrayAdapter adapter;

    public CheckInFragment(ArrayList<String> guestList) {
        this.fragGuestList = guestList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Check In");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_in, container, false);

        checkInButton = view.findViewById(R.id.CheckIn1);
        checkedInButton = view.findViewById(R.id.CheckIn2);
        fullListButton = view.findViewById(R.id.CheckIn3);
        listView = view.findViewById(R.id.listViewCheckIn);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, fragGuestList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setTitle("Would you like to check in, \n" +fragGuestList.get(i) + "?");
                ad.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast toast = Toast.makeText(getContext(), fragGuestList.get((int)l) + ", you have been checked in!", Toast.LENGTH_LONG);
                                View view = toast.getView();
                                TextView text = view.findViewById(android.R.id.message);
                                view.getBackground().setColorFilter(Color.parseColor("#fa7c73"), PorterDuff.Mode.SRC_IN);
                                text.setTextColor(Color.BLACK);
                                text.setTextSize(30);
                                toast.show();
                                iCheckIn.launchCheckIn((int)l);
                            }
                        });

                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ad.create().show();
            }
        });
        checkInButton.setEnabled(false);

        checkedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCheckIn.launchCheckedIn();
            }
        });

        fullListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCheckIn.launchFullList();
            }
        });
        return view;
    }

    ICheckIn iCheckIn;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ICheckIn) {
            iCheckIn = (ICheckIn) context;
        } else {
            throw new RuntimeException("You forgot something important");
        }
    }
}

interface ICheckIn{
    void launchCheckIn(int i);
    void launchCheckedIn();
    void launchFullList();
}