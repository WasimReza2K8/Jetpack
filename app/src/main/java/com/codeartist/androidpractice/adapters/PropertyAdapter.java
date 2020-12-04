package com.codeartist.androidpractice.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeartist.androidpractice.databinding.ItemPropertyBinding;
import com.codeartist.androidpractice.model.Property;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    private List<Property> properties;

    public PropertyAdapter(List<Property> employees) {
        this.properties = employees;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPropertyBinding binding = ItemPropertyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(properties.get(position));

    }

    public void addProperties(Property property){
        properties.add(property);
        this.notifyItemInserted(properties.size() - 1);
    }
    public void addProperties(List<Property> properties){
        this.properties = properties;
        this.notifyDataSetChanged();
        //this.notifyItemInserted(employees.size() - 1);
    }


    @Override
    public int getItemCount() {
        return properties.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemPropertyBinding binding;

        public ViewHolder(ItemPropertyBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }

        ViewHolder(View view) {
            super(view);
        }

        void bind(@NonNull Property item) {
            binding.setProperty(item);
            binding.executePendingBindings();
        }
    }
}
