package com.codeartist.androidpractice.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeartist.androidpractice.databinding.ItemEmployeeBinding;
import com.codeartist.androidpractice.model.Employees;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private ArrayList<Employees> employees;

    public EmployeeAdapter(ArrayList<Employees> employees) {
        this.employees = employees;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEmployeeBinding binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(employees.get(position));

    }

    public void addEmployee(Employees employee){
        employees.add(employee);
        this.notifyItemInserted(employees.size() - 1);
    }


    @Override
    public int getItemCount() {
        return employees.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemEmployeeBinding binding;

        public ViewHolder(ItemEmployeeBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }

        ViewHolder(View view) {
            super(view);
        }

        void bind(@NonNull Employees item) {
            binding.setEmployee(item);
            binding.executePendingBindings();
        }
    }
}
