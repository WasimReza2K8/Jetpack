package com.codeartist.androidpractice.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeartist.androidpractice.databinding.ItemPropertyBinding
import com.codeartist.androidpractice.model.Property

class PropertyAdapter(private var properties: List<Property>) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(properties[position])
    }

   /* fun addProperties(property: Property) {
        properties.add(property)
        notifyItemInserted(properties.size - 1)
    }*/

    fun addProperties(properties: List<Property>) {
        this.properties = properties
        notifyDataSetChanged()
        //this.notifyItemInserted(employees.size() - 1);
    }

    override fun getItemCount(): Int {
        return properties.size
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: ItemPropertyBinding? = null

        constructor(binding: ItemPropertyBinding) : this(binding.root) {
            this.binding = binding
        }

        fun bind(item: Property) {
            binding!!.property = item
            binding!!.executePendingBindings()
        }
    }
}