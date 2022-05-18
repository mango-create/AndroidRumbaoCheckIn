package com.example.rumbaocheckin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.ArrayList;

public class FullListFragment extends Fragment {
    Button checkInButton, checkedInButton, fullListButton;
    ArrayList<String> fullList = new ArrayList<>();
    ListView listView;
    ArrayAdapter adapter;
    public FullListFragment(ArrayList<String> fullList) {
        this.fullList = fullList;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Full List");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_full_list, container, false);

        checkInButton = view.findViewById(R.id.fullList1);
        checkedInButton = view.findViewById(R.id.fullList2);
        fullListButton = view.findViewById(R.id.fullList3);
        listView = view.findViewById(R.id.listViewFullList);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, fullList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setTitle("STOP THAT TICKLES");

                ad.setPositiveButton("I'll stop", new DialogInterface.OnClickListener() {
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

        checkedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCheckIn.launchCheckedIn();
            }
        });

        fullListButton.setEnabled(false);
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