package com.example.user.surveyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by User on 08/10/2016.
 */
public class CustomSurveyItem extends ArrayAdapter<String> {

    public CustomSurveyItem(Context context, String[] names) {
        super(context,R.layout.custom_survey_item, names);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.custom_survey_item, parent, false);
        String s = getItem(position);
        TextView txt = (TextView)v.findViewById(R.id.nametxt);
        txt.setText(s);
        return v;

    }

}
