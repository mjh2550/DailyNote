package com.android.dailynote.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.databinding.ListItem2Binding
import java.text.SimpleDateFormat

class HomeCalendarListAdapter(private val clickListener: HomeCalendarListListener)
    : androidx.recyclerview.widget.ListAdapter<NoteVO,HomeCalendarListAdapter.ViewHolder>(NoteListDiffCallback()) {

    class ViewHolder private constructor(private val binding: ListItem2Binding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): HomeCalendarListAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItem2Binding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun bind(item: NoteVO, clickListener: HomeCalendarListListener) {
            val regTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.regTime) as String
            binding.tvCalNoteId.text = item.noteId.toString()
            binding.tvCalNoteTitle.text = item.noteTitle
            binding.tvCalNoteDate.text = regTime
            binding.noteVO = item
            binding.clickListener = clickListener
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

}

