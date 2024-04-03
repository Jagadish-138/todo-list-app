package com.example.mytodolistapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Edit or Remove Task");

            final EditText input = new EditText(MainActivity.this);
            input.setText(tasks.get(position));
            builder.setView(input);

            builder.setPositiveButton("Edit", (dialog, which) -> {
                tasks.set(position, input.getText().toString());
                adapter.notifyDataSetChanged();
            });

            builder.setNegativeButton("Remove", (dialog, which) -> {
                tasks.remove(position);
                adapter.notifyDataSetChanged();
            });

            builder.setNeutralButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.editText);
            String task = editText.getText().toString();
            if (!task.isEmpty()) {
                tasks.add(task);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
    }
}
