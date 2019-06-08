package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_fragment.*

class ProfileFragment : Fragment(){


override fun onCreateView(inflater: LayoutInflater,
                          container: ViewGroup?,
                          savedInstanceState: Bundle?
): View? {
    setHasOptionsMenu(true)
    return inflater.inflate(R.layout.profile_fragment,container,false)
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    companion object{
        fun newInstance(): ProfileFragment{
            return newInstance()
        }
    }
}