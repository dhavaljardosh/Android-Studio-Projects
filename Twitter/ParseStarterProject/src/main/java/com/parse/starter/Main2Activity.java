package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<>();

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("User List");



        ListView listView = (ListView) findViewById(R.id.listView);

        assert listView != null;
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        arrayAdapter  =new ArrayAdapter<>(this,android.R.layout.simple_list_item_checked, users);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = (CheckedTextView) view;

                if(checkedTextView.isChecked()){
                    Log.i("Info","Row is Checked");
                    Log.i("ADD: ",ParseUser.getCurrentUser().getList("isFollowing")+" "+users.get(position));

//                    ParseUser.getCurrentUser().getList("isFollowing").add(users.get(position));
//                    ParseUser.getCurrentUser().saveInBackground();


                } else{
                    Log.i("Info","Row is not Checked");
                    Log.i("REMOVE: ",ParseUser.getCurrentUser().getUsername()+" "+users.get(position));

//
//                    ParseUser.getCurrentUser().getList("isFollowing").remove(users.get(position));
//                    ParseUser.getCurrentUser().saveInBackground();
                }
            }
        });

        users.clear();

        ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(ParseUser user : objects){
                            users.add(user.getUsername());
                        }

                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }
}
