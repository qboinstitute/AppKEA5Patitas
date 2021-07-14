package com.qbo.appkea5patitas.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qbo.appkea5patitas.R
import com.qbo.appkea5patitas.databinding.ActivityHomeBinding
import com.qbo.appkea5patitas.viewmodel.PersonaViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navlistamascota, R.id.navvoluntario
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        mostrarInfoAutenticacion()
    }

    private fun mostrarInfoAutenticacion() {
        val tvnomusuario: TextView = binding.navView.getHeaderView(0)
            .findViewById(R.id.tvnomapeusuario)
        val tvemailusuario: TextView = binding.navView.getHeaderView(0)
            .findViewById(R.id.tvemailusuario)
        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)
        personaViewModel.obtener()
            .observe(this, Observer { persona ->
                persona.let {
                    tvnomusuario.text = "${persona.nombres}  ${persona.apellidos}"
                    tvemailusuario.text = persona.email
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val idItem = item.itemId
        if(idItem == R.id.action_cerrar){
            startActivity(Intent(this,
                LoginActivity::class.java))
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}