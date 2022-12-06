package com.android.dailynote.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.dailynote.R
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.databinding.FragmentNoteListBinding
import com.android.dailynote.databinding.ListItemBinding

class NoteListAdapter(private val clickListener: NoteListListener)
    :androidx.recyclerview.widget.ListAdapter<NoteVO,NoteListAdapter.ListViewHolder>(NoteListDiffCallback()) {

    class ListViewHolder private constructor(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

       companion object{
           fun from(parent: ViewGroup) : ListViewHolder{
               val layoutInflater = LayoutInflater.from(parent.context)
               val binding = ListItemBinding.inflate(layoutInflater,parent,false)
               return ListViewHolder(binding)
           }
       }
       fun bind(item : NoteVO, clickListener: NoteListListener){
           binding.tvNoteId.text = item.noteId.toString()
           binding.tvNoteTitle.text = item.noteTitle
           binding.tvNoteDate.text = item.regTime.toString()
           binding.tvNoteWriter.text = item.noteWriter
           binding.tvNoteAttachYN.text = item.attachYN
           binding.tvNoteCommentYN.text = item.commentYN
           binding.cbCheck.isChecked = item.isChecked
           binding.noteVO = item
           binding.clickListener = clickListener
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

class NoteListListener(val clickListener : (noteVO:NoteVO,EventType) -> Unit){
    fun onClick(noteVO: NoteVO) = clickListener(noteVO,EventType.ON_BUTTON_CLICK)
    fun onClickCheckBoxChanged(noteVO: NoteVO) {
        noteVO.isChecked = !noteVO.isChecked
        clickListener(noteVO, EventType.ON_CHECKBOX_CHANGED)
    }
}