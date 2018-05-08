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
        //task = new ArrayList<Task>();
        aa = new DataAdapter(MainActivity.this, R.layout.row, task);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnGetTasks = (Button) findViewById(R.id.btnGetTasks);
        tvResults = (TextView)  findViewById(R.id.tvResults);
        lv = (ListView) this.findViewById(R.id.lv);

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
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    txt += i + ". " + data.get(i).getDescription() + "\n";
                    task.add(new Task(data.get(i).getId(), data.get(i).getDescription(), data.get(i).getDate()));
                }
                lv.setAdapter(aa);
                tvResults.setText(txt);
                Log.d("Database Content", data.get(1).getId() +". "+data.get(1).getDescription()+ ". " + data.get(1).getDate());
            }
        });

    }
}
