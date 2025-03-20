package com.example.testnaviforproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentIntegral: Fragment(R.layout.fragment_inte) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inte, container, false)

        val btnToILevel1: Button = view.findViewById(R.id.btn_i_lv1)
        val btnToILevel2: Button = view.findViewById(R.id.btn_i_lv2)
        val btnToILevel3: Button = view.findViewById(R.id.btn_i_lv3)

        btnToILevel1.setOnClickListener {
            // Perform action when Level 1 button is clicked
            // Example: Start Level 1 activity
            val intent = Intent(requireContext(), gameDeriLevel1::class.java)
            startActivity(intent)
        }

        btnToILevel1.setOnClickListener {
            val intent = Intent(requireContext(), gameInteLevel1::class.java)
            startActivity(intent)
        }
        btnToILevel2.setOnClickListener {
            val intent = Intent(requireContext(), gameInteLevel2::class.java)
            startActivity(intent)
        }
        btnToILevel3.setOnClickListener {
            val intent = Intent(requireContext(), gameInteLevel3::class.java)
            startActivity(intent)
        }
        return view
    }
}