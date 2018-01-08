package com.example.jardosh.customlist;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(@NonNull Context context, String[] food) {
        super(context, R.layout.custom_row, food);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater dhavalInflater = LayoutInflater.from(getContext());
        View customView = dhavalInflater.inflate(R.layout.custom_row, parent, false);

        String singleFoodItem = getItem(position);
        TextView dhavalText =  (TextView) customView.findViewById(R.id.dhavalText);
        ImageView dhavalImage = (ImageView) customView.findViewById(R.id.dhavalImage);

        dhavalText.setText(singleFoodItem);
        dhavalImage.setImageResource(R.drawable.djj);
        return customView;

    }
}
