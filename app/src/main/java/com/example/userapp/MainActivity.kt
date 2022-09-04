package com.example.userapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.userapp.Constants.DEFAULT_TOKEN
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBar: MaterialToolbar
    private lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private lateinit var nav_view: NavigationView

    private lateinit var tokenManager: LogInStateManager
    private lateinit var sharedViewModel: SharedViewModel

    private val fragment1 = Fragment1()
    private val fragment2: Fragment = Fragment2()
    private val getStarted: Fragment = GetStarted()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBar = findViewById(R.id.appBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        nav_view = findViewById(R.id.nav_view)


        // setting up navigation drawer
        setUpViews()

        val dao = PersonDB.getDB(this.applicationContext).personDao()
        val repository = Repository(dao)
        sharedViewModel = ViewModelProvider(
            this,
            SharedViewModelFactory(repository)
        ).get(SharedViewModel::class.java)

        sharedViewModel =
            ViewModelProvider(this, SharedViewModelFactory(repository)).get(
                SharedViewModel::class.java
            )

        tokenManager = LogInStateManager(applicationContext)

//         setting up the default fragment on opening of the app
        if (tokenManager.getToken() != DEFAULT_TOKEN) {

            sharedViewModel._id.value = tokenManager.getToken()
            manageFragments(fragment1, "Home")
        } else
            manageFragments(getStarted, "Get Started")

        // listening to clicks on navigation items
        nav_view.setNavigationItemSelectedListener(this)

    }


    private fun setUpViews() {
        setUpDrawerLayout()
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(appBar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    //-- Make Hamburger icon clickable to open navigation drawer --//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //    --- Managing the navigation items click ---//
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.btnFrag1 -> manageFragments(fragment1, "Home")
            R.id.btnFrag2 -> manageFragments(fragment2, "Contacts")
            R.id.btnAct2 -> switchActivity()

        }
        return true
    }

//     TODO: empty the backstack when reached on starting fragment.

    // moving to the particular fragment as instructed from navigation items clicks
    private fun manageFragments(frag: Fragment, toolBarTitle: String) {

        appBar.title = toolBarTitle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_cv, frag)
            addToBackStack(null)
            commit()
        }

    }


    private fun switchActivity() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}