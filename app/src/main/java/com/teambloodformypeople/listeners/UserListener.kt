package com.teambloodformypeople.listeners

import android.view.View
import com.teambloodformypeople.data.models.User

interface UserListener {
    fun onDeleteButtonClicked(view: View, user: User)
    fun onUpdateButtonClicked(view: View, user: User)
}