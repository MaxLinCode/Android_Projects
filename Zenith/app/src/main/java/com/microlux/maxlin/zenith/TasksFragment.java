package com.microlux.maxlin.zenith;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by maxlin on 3/1/16.
 */
// Instances of this class are fragments representing a single
// object in our collection.
public class TasksFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
            /*
            View rootView = inflater.inflate(
                    R.layout.fragment_collection_object, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
            */
        String message = getArguments().getString("EXTRA_MESSAGE");
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);
        TextView messageTextView = (TextView) v.findViewById(R.id.tasksTextView);
        messageTextView.setText("Wow amazing " + getArguments().getString("EXTRA_MESSAGE"));
        return v;
    }


    public static final TasksFragment newInstance(String message) {
        TasksFragment frag = new TasksFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString("EXTRA_MESSAGE", message);
        frag.setArguments(bdl);

        return frag;

    }

}