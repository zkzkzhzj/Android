/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?, private val layout: Int, private val dogs: List<Dog>
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {
    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val listImage = view?.findViewById<ImageView>(R.id.recycler_image)
        val listName = view?.findViewById<TextView>(R.id.recycler_name)
        val listAge = view?.findViewById<TextView>(R.id.recycler_age)
        val listHobby = view?.findViewById<TextView>(R.id.recycler_hobby)

        val gridImage = view?.findViewById<ImageView>(R.id.grid_recycler_image)
        val gridName = view?.findViewById<TextView>(R.id.grid_recycler_name)
        val gridAge = view?.findViewById<TextView>(R.id.grid_recycler_age)
        val gridHobby = view?.findViewById<TextView>(R.id.grid_recycler_hobby)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        var adapterLayout: View? = null
        when (layout) {
            Layout.HORIZONTAL, Layout.VERTICAL -> {
                adapterLayout = LayoutInflater.from(context).
                    inflate(R.layout.vertical_horizontal_list_item, parent, false)
            }
            Layout.GRID -> {
                adapterLayout = LayoutInflater.from(context).inflate(R.layout.grid_list_item, parent, false)
            }
        }
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = dogs.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val resources = context?.resources
        val item = dogs[position]

        holder.listImage?.setImageResource(item.imageResourceId)
        holder.listName?.text = item.name
        holder.listAge?.text = resources?.getString(R.string.dog_age, item.age)
        holder.listHobby?.text = resources?.getString(R.string.dog_hobbies, item.hobbies)

        holder.gridImage?.setImageResource(item.imageResourceId)
        holder.gridName?.text = item.name
        holder.gridAge?.text = resources?.getString(R.string.dog_age, item.age)
        holder.gridHobby?.text = resources?.getString(R.string.dog_hobbies, item.hobbies)
    }
}
