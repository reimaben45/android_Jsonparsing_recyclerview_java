package com.example.recyclerjson;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    Context context;
    //private String[] mStrings;
    List<ExampleModel> exampleModelList;

    //public MyListAdapter(String[] values){
    public MyListAdapter(Context context1, List<ExampleModel> exampleModelList){
        this.context = context1;
        this.exampleModelList = exampleModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame_lay, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ExampleModel exampleModel =  exampleModelList.get(position);
        holder.mytext.setText(exampleModel.getName());
        holder.gender.setText(exampleModel.getGender());
       // holder.getView().setText((CharSequence) exampleModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return exampleModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mytext;
        TextView gender;

        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mytext = (TextView) itemView.findViewById(R.id.textView);
            gender = (TextView) itemView.findViewById(R.id.gender1);
            img = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public TextView getView() {
            return mytext;
        }
    }
}