package com.teambloodformypeople.listeners

import android.view.View
import com.teambloodformypeople.data.models.Donor

interface DonorListener {
    fun onDonateButtonClicked(view: View, donor: Donor)
}