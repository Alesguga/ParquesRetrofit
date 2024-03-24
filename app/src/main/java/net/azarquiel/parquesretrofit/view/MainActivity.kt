package net.azarquiel.parquesretrofit.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.parquesretrofit.R
import net.azarquiel.parquesretrofit.adapters.AdapterComunidad
import net.azarquiel.parquesretrofit.databinding.ActivityMainBinding
import net.azarquiel.parquesretrofit.model.Comunidad
import net.azarquiel.parquesretrofit.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var comunidades: List<Comunidad>
    private lateinit var adapter: AdapterComunidad
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initRV()
        getComunidades()
        /*
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }
    private fun initRV() {
        adapter = AdapterComunidad(this,R.layout.rowcomunidad)
        binding.cm.rvcomunidades.adapter = adapter
        binding.cm.rvcomunidades.layoutManager = LinearLayoutManager(this)
    }

    private fun getComunidades() {
        viewModel.getComunidades().observe(this, Observer { it ->
            it?.let{
                it.forEach {
                    Log.d("paco", it.toString())
                }
                val comunidades = ArrayList(it)
                comunidades.add(0, Comunidad(0, "Todos"))
                this.comunidades = comunidades
                adapter.setComunidades(comunidades)
            }
        })
    }
    fun onClickComunidad(v: View){
        val comunidad = v.tag as Comunidad
        val intent = Intent(this, ParquesActivity::class.java)
        intent.putExtra("comunidad", comunidad)
        startActivity(intent)
    }
/*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
*/
}