package com.example.testnaviforproject

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private var selectedBottomNavItem = R.id.miHome

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // val for Fragment navi by bottomNaviView
        val fragmentDerivative = FragmentDerivative()
        val fragment2 = Fragment2()
        val fragment3 = Fragment3()
        val fragmentInt = FragmentIntegral()
        val fragmentHome = arrayOf(FragmentDerivative(), FragmentIntegral())
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Get reference to the spinner
        val spinner: Spinner = findViewById(R.id.spinnerForTopic)

        // Set the onItemSelectedListener to this activity
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // You can use the position parameter to determine the selected item

                // Example: Change the fragment based on the selected item
                val fragment: Fragment? = when (position) {
                    0 -> FragmentDerivative()
                    1 -> FragmentIntegral()
                    // Add more cases for other items if needed
                    else -> null
                }
                /*
                if (position != 0) {
                    val selectedFragment = fragmentHome[position]
                    setCurrentFragment(selectedFragment)
                } else {
                    // If the selected item is the first item and the current bottom navigation item is miMsg,
                    // set the fragment to Fragment2 without changing the spinner
                    if (selectedBottomNavItem == R.id.miMsg) {
                        setCurrentFragment(fragment2)
                    }
                } */
                if (selectedBottomNavItem == R.id.miRevision) {
                    setCurrentFragment(fragment2)
                } else {
                    val selectedFragment = fragmentHome[position]
                    setCurrentFragment(selectedFragment)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        setCurrentFragment(fragmentDerivative)

        bottomNavigationView.setOnItemSelectedListener {
            selectedBottomNavItem = it.itemId
            when(it.itemId) {
                R.id.miHome -> {
                    spinner.setSelection(0)
                    setCurrentFragment(fragmentDerivative)
                }
                R.id.miRevision -> {
                    spinner.setSelection(0)
                    setCurrentFragment(fragment2)
                }
                R.id.miProfile -> {
                    spinner.setSelection(0)
                    setCurrentFragment(fragment3)
                }
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.flFragment)
    }

}