package com.example.userapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBar: MaterialToolbar
    private lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private lateinit var nav_view : NavigationView

    private val fragment1 = Fragment1()
    private val fragment2: Fragment = Fragment2()
    private val fragment3: Fragment = Fragment3()
    private val fragment4: Fragment = Fragment4()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBar = findViewById(R.id.appBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        nav_view = findViewById(R.id.nav_view)


        // setting up navigation drawer
        setUpViews()

        // setting up the default fragment on opening of the app
        manageFragments(fragment1, "Fragment1")

        // listening to clicks on navigation items
        nav_view.setNavigationItemSelectedListener(this)




    }

    fun setUpViews(){
        setUpDrawerLayout()
    }

    fun setUpDrawerLayout(){
        setSupportActionBar(appBar)
        actionBarDrawerToggle= ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

//-- Make Hamburger icon clickable to open navigation drawer --//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

//    --- Managing the navigation items click ---//
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId){
            R.id.btnFrag1 -> manageFragments(fragment1, "Fragment1")
            R.id.btnFrag2 -> manageFragments(fragment2, "Fragment2")
            R.id.btnFrag3 -> manageFragments(fragment3, "Fragment3")
            R.id.btnFrag4 -> manageFragments(fragment4, "Fragment4")
            R.id.btnAct2 -> switchActivity()

        }
        return true
    }

    // moving to the particular fragment as instructed from navigation items clicks
    fun manageFragments(frag: Fragment,toolBarTitle:String){

        appBar.title= toolBarTitle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_cv,frag)


            addToBackStack(null)

            commit()
        }

    }


    fun switchActivity(){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}