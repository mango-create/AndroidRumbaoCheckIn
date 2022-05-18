package com.example.rumbaocheckin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

public class CheckedInFragment extends Fragment {
    Button checkInButton, checkedInButton, fullListButton;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> alreadyCheckedIn = new ArrayList<>();

    public CheckedInFragment(ArrayList<String> alreadyCheckedIn) {
        this.alreadyCheckedIn = alreadyCheckedIn;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Already Checked In");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checked_in, container, false);

        checkInButton = view.findViewById(R.id.CheckedIn1);
        checkedInButton = view.findViewById(R.id.CheckedIn2);
        fullListButton = view.findViewById(R.id.CheckedIn3);
        listView = view.findViewById(R.id.listViewCheckedIn);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, alreadyCheckedIn);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setTitle("Remove " + alreadyCheckedIn.get(i)+ " and add them back to Check In List?");

                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(getContext(), alreadyCheckedIn.get((int)l) + " has been added back to Check In List.", Toast.LENGTH_LONG);
                        View view = toast.getView();
                        TextView text = view.findViewById(android.R.id.message);
                        view.getBackground().setColorFilter(Color.parseColor("#fa7c73"), PorterDuff.Mode.SRC_IN);
                        text.setTextColor(Color.BLACK);
                        text.setTextSize(30);
                        toast.show();
                        iCheckedIn.unCheckIn((int)l);
                    }
                });

                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ad.create().show();
            }
        });

        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCheckIn.launchCheckIn(-1);
            }
        });

        checkedInButton.setEnabled(false);

        fullListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCheckIn.launchFullList();
            }
        });
        return view;
    }

    ICheckedIn iCheckedIn;
    ICheckIn iCheckIn;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ICheckedIn) {
            iCheckedIn = (ICheckedIn) context;
        } else {
            throw new RuntimeException("You forgot something important");
        }
        if (context instanceof ICheckIn) {
            iCheckIn = (ICheckIn) context;
        } else {
            throw new RuntimeException("You forgot something important");
        }

    }
}

interface ICheckedIn{
    void unCheckIn(int i);
}