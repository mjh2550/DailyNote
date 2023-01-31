package com.android.dailynote.adapters

import android.view.View
import com.android.dailynote.common.EventType
import com.android.dailynote.data.model.entity.NoteVO

class HomeCalendarListListener(val clickListener : (v: View?, noteVO: NoteVO?, EventType) -> Unit) {
    fun onClick(v0 : View?, noteVO: NoteVO) = clickListener(v0,noteVO,EventType.ON_BUTTON_CLICK)
}