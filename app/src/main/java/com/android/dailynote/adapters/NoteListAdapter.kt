package com.android.dailynote.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.databinding.ListItemBinding
import java.text.SimpleDateFormat

class NoteListAdapter(private val clickListener: NoteListListener)
    :androidx.recyclerview.widget.ListAdapter<NoteVO,NoteListAdapter.ListViewHolder>(NoteListDiffCallback()) {

    class ListViewHolder private constructor(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

       companion object{
           fun from(parent: ViewGroup) : ListViewHolder {
               val layoutInflater = LayoutInflater.from(parent.context)
               val binding = ListItemBinding.inflate(layoutInflater,parent,false)
               return ListViewHolder(binding)
           }
       }
       @SuppressLint("SimpleDateFormat")
       fun bind(item : NoteVO, clickListener: NoteListListener){
           val regTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.regTime) as String
           binding.tvNoteId.text = item.noteId.toString()
           binding.tvNoteTitle.text = item.noteTitle
           binding.tvNoteDate.text = regTime
           binding.tvNoteWriter.text = item.noteWriter
           binding.tvNoteAttachYN.text = item.attachYN
           binding.tvNoteCommentYN.text = item.commentYN
           binding.cbCheck.isChecked = item.isChecked
           binding.noteVO = item
           binding.clickListener = clickListener
           binding.cbCheck.visibility = View.VISIBLE
           (binding.tvNoteTitle.layoutParams as LinearLayout.LayoutParams).weight = 0.6f
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

}

class NoteListDiffCallback : DiffUtil.ItemCallback<NoteVO>(){
    override fun areItemsTheSame(oldItem: NoteVO, newItem: NoteVO): Boolean {
        return oldItem.noteId == newItem.noteId
    }

    override fun areContentsTheSame(oldItem: NoteVO, newItem: NoteVO): Boolean {
        return oldItem == newItem
    }
}

