package com.example.todoapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.apdapters.TaskAdapter;
import com.example.todoapp.models.Task;
import com.example.todoapp.views.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.ClickListener {

    private RecyclerView rcvTask;
    private FloatingActionButton btnAdd;
    private TaskAdapter taskAdapter;
    private TaskViewModel taskViewModel;

    private int index = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcvTask = findViewById(R.id.rcvTask);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnNewTask);

        taskAdapter = new TaskAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvTask.setLayoutManager(linearLayoutManager);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.init(this.getApplication());

        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if(tasks.size() > 0){
                    taskAdapter.setData(tasks);
                    rcvTask.setAdapter(taskAdapter);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });
    }

    private void addTask() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = getLayoutInflater().inflate(R.layout.item_add_task, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        Button btnAddTask = (Button) view.findViewById(R.id.btnAddTask);
        EditText edtName = view.findViewById(R.id.edtTaskName);
        EditText edtDescription = view.findViewById(R.id.edtTaskDescription);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                if(edtName.getText() != null && edtDescription.getText() != null){
                    task.setNameTask(edtName.getText().toString().trim());
                    task.setDescriptionTask(edtDescription.getText().toString().trim());
                    task.setIsMarkTask("0");
                    taskViewModel.insertTask(task);
                    alertDialog.dismiss();
                }
            }
        });

        alertDialog.show();
    }


    @Override
    public void deleteClicked(Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want remove task ?")
                .setTitle("Delete Task");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User taps OK button.
                taskViewModel.deleteTask(task);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void updateClicked(Task task) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setMessage("Do you want to mark completed? ?")
//                .setTitle("Complete Task");
//        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User taps OK button.
//                taskViewModel.updateTask(task);
//            }
//        });
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
        taskViewModel.updateTask(task);
    }
}