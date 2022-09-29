package com.android.dailynote.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.dailynote.R
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.databinding.FragmentNoteListBinding

class NoteListAdapter(private  val context: Context
,init: ArrayList<NoteVO>)
    : RecyclerView.Adapter<NoteListAdapter.ListViewHolder>() {

    private var data = init

    inner class ListViewHolder(val v : View): RecyclerView.ViewHolder(v) {
        private val tv1 : TextView = v.findViewById(R.id.tv_note_id)
        private val tv2 : TextView = v.findViewById(R.id.tv_note_title)
        private val tv3 : TextView = v.findViewById(R.id.tv_note_date)
        private val tv4 : TextView = v.findViewById(R.id.tv_note_writer)
        private val tv5 : TextView = v.findViewById(R.id.tv_note_attachYN)
        private val tv6 : TextView = v.findViewById(R.id.tv_note_commentYN)

        fun bind(item : NoteVO){
            tv1.text = item.noteId.toString()
            tv2.text = item.noteTitle
            tv3.text = item.regTime.toString()
            tv4.text = item.noteWriter
            tv5.text = item.attachYN
            tv6.text = item.commentYN
        }
    }

    // 뷰홀더를 만들고 뷰를 초기화하는 함수이다. 아직 바인딩이 안되었기 때문에 뷰에 내용이 직접적으로 담기는 과정은 아니다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(inflatedView)
    }

    // 총 몇개의 반복되는 뷰 데이터가 있는지, 즉 친구목록에서 총 친구가 몇 명인지와 같다.
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
      val item = data[position]
        holder.bind(item)
    }


}