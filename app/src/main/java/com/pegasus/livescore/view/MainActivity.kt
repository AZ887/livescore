package com.pegasus.livescore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.pegasus.livescore.R
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var toolbarSpinner: Spinner
    private var currentSport = 0
    private var currentTab = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        navController = findNavController(R.id.main_nav_host) //Initialising navController

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_football,
            R.id.nav_basketball,
            R.id.nav_live,
            R.id.nav_history,
            R.id.nav_football_history,
            R.id.nav_basketball_history,
            R.id.nav_football_live,
            R.id.nav_basketball_live
        ) //Pass the ids of fragments from nav_graph which you d'ont want to show back button in toolbar
            .setOpenableLayout(main_drawer_layout) //Pass the drawer layout id from activity xml
            .build()

        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        toolbarSpinner = findViewById(R.id.toolbar_spinner)
        toolbarSpinner.adapter = ArrayAdapter(
            this,
            R.layout.spinner_item_textview,
            resources.getStringArray(R.array.Sport)
        )

        setSupportActionBar(toolbar) //Set toolbar

        setupNavControl()
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        ) //Setup toolbar with back button and drawer icon according to appBarConfiguration

        val navHostFragment = main_nav_host as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.mobile_navigation)
        graph.startDestination = R.id.nav_football

        navHostFragment.navController.graph = graph

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_football -> {
                    currentSport = 0
                    navigate()
                }
                R.id.nav_basketball -> {
                    currentSport = 1
                    navigate()
                }
                R.id.nav_live -> {
                    currentTab = 0
                    navigate()
                }
                R.id.nav_history -> {
                    currentTab = 1
                    navigate()
                }
            }
        }

    }

    private fun navigate(){
        if(currentSport == 0 && currentTab == 0){
            navController.navigate(R.id.nav_football_live)
        }else if (currentSport == 0 && currentTab == 1){
            navController.navigate(R.id.nav_football_history)
        }else if (currentSport == 1 && currentTab == 0){
            navController.navigate(R.id.nav_basketball_live)
        }else if (currentSport == 1 && currentTab == 1){
            navController.navigate(R.id.nav_basketball_history)
        }
    }

    private fun setupNavControl() {
        main_navigation_view?.setupWithNavController(navController) //Setup Drawer navigation with navController
        main_bottom_navigation_view?.setupWithNavController(navController) //Setup Bottom navigation with navController

//        main_navigation_view?.setNavigationItemSelectedListener { menuItem ->
//            supportActionBar?.title = menuItem.title
//            menuItem.isChecked = true
//            main_drawer_layout.closeDrawers()
//            true
//        }
//

    }

    fun exitApp() { //To exit the application call this function from fragment
        this.finishAffinity()
    }

    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        when { //If drawer layout is open close that on back pressed
            main_drawer_layout.isDrawerOpen(GravityCompat.START) -> {
                main_drawer_layout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


}