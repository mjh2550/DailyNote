package com.android.dailynote.adapters

import android.view.View
import android.widget.CompoundButton
import com.android.dailynote.R
import com.android.dailynote.data.model.entity.NoteVO

class NoteListListener(val clickListener : (v: View?, isChecked : Boolean?,noteVO:NoteVO?, EventType) -> Unit)  {
    fun onClick(v0 : View?, noteVO: NoteVO) = clickListener(v0,null,null,EventType.ON_BUTTON_CLICK)


    fun onClickCheckBoxChanged(v0: CompoundButton?, isChecked: Boolean, noteVO: NoteVO) : Boolean {
        when (v0?.id){
            R.id.cb_check -> {
                clickListener(v0,isChecked, noteVO, EventType.ON_CHECKBOX_CHANGED)
            }
        }
        return isChecked
    }
}