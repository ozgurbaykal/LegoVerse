package com.ozgurbaykal.hostmobile.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.social.legoverse.R
import com.social.legoverse.databinding.FragmentTutorialPage1Binding
import com.social.legoverse.databinding.LoginSignInBinding
import com.social.legoverse.databinding.LoginSignUpBinding
import com.social.legoverse.databinding.LoginStarterFragmentBinding

class LoginSignUp : Fragment(R.layout.login_sign_up) {

    private val TAG = "_LoginSignUp"

    private var _binding: LoginSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView()")

        _binding = LoginSignUpBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)

    }

}

