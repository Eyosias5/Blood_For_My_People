package com.teambloodformypeople.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.teambloodformypeople.R
import com.teambloodformypeople.databinding.ProfileFragmentBinding
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.UserViewModel

class ProfileFragment : Fragment() {

    lateinit var _context: Context
    private lateinit var binding: ProfileFragmentBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var nameTv: TextView
    lateinit var phoneTv: TextView
    lateinit var dbTv: TextView
    private lateinit var viewModel: UserViewModel
    private lateinit var donorViewModel: DonorViewModel
    var currentRecepientId: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.VISIBLE


      //  val view = inflater.inflate(R.layout.profile_fragment, container, false)
       // _context = view.context
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
//
//        sharedPreferences = _context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)
//        currentRecepientId = sharedPreferences.getInt(Constants().currentUser, 0)
//        Toast.makeText(_context, currentRecepientId.toString(), Toast.LENGTH_SHORT).show()
//        nameTv = view.name_tv
//        phoneTv = view.phone_tv
//        dbTv = view.db_tv
//        updateView(1)

        return binding.root

    }

    private fun updateView(id: Int) {
        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        donorViewModel.getDonorByUserId(id)
        donorViewModel.getResponse.observe(this, Observer { response ->
            response.body()?.run {
                updateView(this.fullName, this.phoneNumber, this.dateOfBirth)

            }
        })
    }

    private fun updateView(name: String, phone: String, db: String) {
        nameTv.text = name
        phoneTv.text = phone
        dbTv.text = db
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.signOutButton)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.signOutAction, null)
        )
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return newInstance()
        }
    }
}
