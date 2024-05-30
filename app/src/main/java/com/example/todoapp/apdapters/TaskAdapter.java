package com.example.todoapp.apdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private List<Task> mListTask;
    private Context context;
    private ClickListener clickListener;

    String check = "";

    public TaskAdapter(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public void setData(List<Task> mListTask) {
        this.mListTask = mListTask;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.item_task, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = mListTask.get(position);
        if(task==null){
            return;
        }

        holder.txtName.setText(task.getNameTask());
        holder.txtDescription.setText(task.getDescriptionTask());
        Boolean checkMar = convertStringToBoolean(task.getIsMarkTask());
        Boolean isCheckMar = convertStringToBoolean(check);

        if(checkMar){
            holder.ckbMark.setChecked(true);

        }
        else {
            holder.ckbMark.setChecked(false);
        }
        holder.ckbMark.setOnCheckedChangeListener(listener);


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.deleteClicked(task);
            }
        });


        holder.ckbMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheckMar){
                    task.setIsMarkTask("1");
                    clickListener.updateClicked(task);
                }
                else{
                    task.setIsMarkTask("0");
                    clickListener.updateClicked(task);
                }
            }
        });
    }

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                check = "1";
            }
            else {
                check = "";
            }
        }
    };

    @Override
    public int getItemCount() {
        if(mListTask != null){
            return mListTask.size();
        }
        return 0;
    }

    public Boolean convertStringToBoolean(String value){
        return "1".equals(value);
    }

    public interface ClickListener{
        void deleteClicked(Task task);
        void updateClicked(Task task);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtDescription;
        private ImageButton btnDelete;

        private CheckBox ckbMark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName =  itemView.findViewById(R.id.txtName);
            txtDescription =  itemView.findViewById(R.id.txtDescription);
            btnDelete =  itemView.findViewById(R.id.btnDelete);
            ckbMark =  itemView.findViewById(R.id.ckbMark);
        }
    }
}
