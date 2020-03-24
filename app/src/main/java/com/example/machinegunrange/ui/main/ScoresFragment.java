package com.example.machinegunrange.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.machinegunrange.CustomExpandableListAdapter;
import com.example.machinegunrange.MachineGunner;
import com.example.machinegunrange.MailSender;
import com.example.machinegunrange.MainActivity;
import com.example.machinegunrange.R;
import com.example.machinegunrange.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Scores fragment containing a simple view.
 */
public class ScoresFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static ExpandableListView listView;
    private PageViewModel pageViewModel;
    private Button exportButton;
    private View.OnClickListener exportButtonListener;

    public static ScoresFragment newInstance(int index) {
        ScoresFragment fragment = new ScoresFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        exportButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.sendEmailWithDialog(getContext());

            }
        };
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.scores_fragment_layout, container, false);

        listView = (ExpandableListView) root.findViewById(R.id.scores_expandable_listview);
        listView.setAdapter(MainActivity.machineGunnerListAdapter);
        listView.setGroupIndicator(null);

        //listView.addFooterView(inflater.inflate(R.layout.listview_footer, null));
        exportButton = root.findViewById(R.id.export_button);
        exportButton.setOnClickListener(exportButtonListener);

        return root;
    }

}