package com.example.paul.tabtest2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentTab extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        ListView list = (ListView) v.findViewById(R.id.listView);

        List<String> list1Strings = new ArrayList<String>();

        Toast.makeText(getActivity(), " "+ this.getTag(), Toast.LENGTH_SHORT).show();

        if (list != null) {
            if (this.getTag().equals("tab1")) {

                list1Strings.add("Item 1");
                list1Strings.add("Item 2");
                list1Strings.add("Item 3");
                list1Strings.add("Item 4");
            } else if (this.getTag().equals("tab2")) {

                list1Strings.add("Item 5");
                list1Strings.add("Item 6");
                list1Strings.add("Item 7");
                list1Strings.add("Item 8");

            } else if (this.getTag().equals("tab3")) {

                list1Strings.add("Item 9");
                list1Strings.add("Item 10");
                list1Strings.add("Item 11");
                list1Strings.add("Item 12");
            }

            list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list1Strings));
        }
        return v;
    }
}
