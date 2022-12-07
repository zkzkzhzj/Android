package com.example.android.marsphotos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.data.MarsPhoto
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.databinding.GridViewItemBinding.inflate

/**
 * RecyclerView Adapter
 * ListAdapter 는 서브 클래스로 백그라운드 스레드에서 이미지를 불러오는
 * 작업을 계산하는 기능을 포함하여 데이터를 로드하기 위하여 사용
 * DiffUtil 구현을 진행하는데 장점으로는 일부만 변경되었을 때 전체를 새로고침하는 것이 아니라
 * 변경된 부분만 새로고침 됨
 */
class PhotoGridAdapter : ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    // 뷰 홀더 생성, 레이아웃 연결 후 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(inflate(LayoutInflater.from(parent.context)))
    }

    // 생성된 뷰 홀더 사용(MarsPhotoViewHolder 로 넘겨줌)
    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    // 뷰 홀더를 사용하여 작업
    class MarsPhotoViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(MarsPhoto: MarsPhoto) {
            binding.photo = MarsPhoto
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        // 두 객체가 동일한 아이템을 나타내는지 확인
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        // 두 객체의 데이터가 동일한지 확인
        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }
}
