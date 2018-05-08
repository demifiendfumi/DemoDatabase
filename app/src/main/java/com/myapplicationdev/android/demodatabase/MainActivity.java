package com.myapplicationdev.android.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Task> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnGetTasks = (Button) findViewById(R.id.btnGetTasks);
        tvResults = (TextView)  findViewById(R.id.tvResults);
        lv = (ListView) this.findViewById(R.id.lv);

        task = new ArrayList<Task>();

        aa = new DataAdapter(MainActivity.this, R.layout.row, task);
        lv.setAdapter(aa);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                db.getWritableDatabase();

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<Task> data = db.getTasks();
                task.clear();
                task.addAll(data);
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    txt += i + ". " + data.get(i).getDescription() + "\n";
                    Log.d("Database Content", data.get(i).getId() +". "+data.get(i).getDate()+ ". " + data.get(i).getDescription() + "\n");
                }
                aa.notifyDataSetChanged();
                tvResults.setText(txt);

            }
        });

    }
}
