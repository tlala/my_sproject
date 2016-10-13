package com.example.user.surveyapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by User on 02/09/2016.
 */
public class GlobalFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment,container,false);

        String[] names = {"tal","bennn","idoo","adi","cohen","ilona","dan","cddd","yoni"};
        ListAdapter listAdapter = new CustomSurveyItem(this.getActivity(),names);
        debugLoger.log("log1");
        ListView listView =(ListView) view.findViewById(R.id.listViewID);
        debugLoger.log("log2");
        listView.setAdapter(listAdapter);
        debugLoger.log("log3s");
        return view ;
    }
}
