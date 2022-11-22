package com.example.recyclerviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.model.Affirmation

// recycler Adapter
class ItemAdapter(private val context: Context, private val dataset: List<Affirmation>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // 리사이클러는 뷰와 직접적으로 상호작용을 하지 않고 뷰홀더를 이용하여 소통
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        // 뷰홀더에 매칭되어있는 텍스트뷰 연결
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    // 뷰홀더 생성(레이아웃 연결 후 반환), 사용중인 뷰와 관련없는 레이아웃 반환시 nullPointException 발생
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    // 위에서 연결하고 매칭시킨 개별 뷰에 대한 동작들 정의
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    /*override fun getItemCount(): Int {
        return dataset.size
    }*/
    // 실제 데이터의 사이즈 반환
    override fun getItemCount() = dataset.size
}