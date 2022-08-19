package com.example.android.travelwriter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.android.travelwriter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        val sharedPrefs = this.getPreferences(Context.MODE_PRIVATE)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout


        //setting the drawer
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        //setting Action Bar title
        if (supportActionBar!=null) {
            supportActionBar!!.title = "TravelWriter"
            sharedPrefs.getString(USERNAME_KEY, null)?.let {
                supportActionBar!!.subtitle = "by $it"
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    companion object {
        const val USERNAME_KEY = "UserName"
    }
}