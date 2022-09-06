package com.example.android.travelwriter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.android.travelwriter.databinding.ActivityMainBinding
import com.example.android.travelwriter.main.MainFragmentDirections

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        val sharedPrefs = this.getPreferences(Context.MODE_PRIVATE)
        val username = sharedPrefs.getString(USERNAME_KEY, null)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        drawerLayout = binding.drawerLayout

        //setting the drawer
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        if (username == null){
            navController.navigate(
                MainFragmentDirections.actionMainFragmentToFirstTimeFragment()
            )
        }
        navController.addOnDestinationChangedListener{ _: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == R.id.mainFragment) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
            if (nd.id == R.id.firstTimeFragment){
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
            }
        }

        //setting Action Bar subtitle
        supportActionBar?.let{ bar ->
            username?.let { name ->
                bar.subtitle = "by $name"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    companion object {
        const val USERNAME_KEY = "UserName"
        const val ARTICLES_COUNT_KEY = "ArticlesPostedCount"
    }
}