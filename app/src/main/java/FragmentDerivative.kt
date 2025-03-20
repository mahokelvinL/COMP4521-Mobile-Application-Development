package com.example.testnaviforproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity


class FragmentDerivative: Fragment(R.layout.fragment_deri) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deri, container, false)

        val btnToDLevel1: Button = view.findViewById(R.id.btn_d_lv1)
        val btnToDLevel2: Button = view.findViewById(R.id.btn_d_lv2)
        val btnToDLevel3: Button = view.findViewById(R.id.btn_d_lv3)

        btnToDLevel1.setOnClickListener {
            val intent = Intent(requireContext(), gameDeriLevel1::class.java)
            startActivity(intent)
        }
        btnToDLevel2.setOnClickListener {
            val intent = Intent(requireContext(), gameDeriLevel2::class.java)
            startActivity(intent)
        }
        btnToDLevel3.setOnClickListener {
            val intent = Intent(requireContext(), gameDeriLevel3::class.java)
            startActivity(intent)
        }
        return view
    }
}