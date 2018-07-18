package com.jordangellatly.coopervision;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EditFragment extends Fragment {
    private static final String ARG_PARAM1 = "int";
    private static final String ARG_PARAM2 = "title";

    private String title;
    private int page;

    public EditFragment() {

    }

    public static EditFragment newInstance(String title, int page) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page);
        args.putString(ARG_PARAM2, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_PARAM1, 0);
        title = getArguments().getString(ARG_PARAM2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
}
