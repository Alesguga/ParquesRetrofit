package net.azarquiel.parquesretrofit.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.parquesretrofit.R
import net.azarquiel.parquesretrofit.adapters.AdapterComunidad
import net.azarquiel.parquesretrofit.adapters.AdapterParque
import net.azarquiel.parquesretrofit.databinding.ActivityParquesBinding
import net.azarquiel.parquesretrofit.model.Comunidad
import net.azarquiel.parquesretrofit.model.Parque
import net.azarquiel.parquesretrofit.viewmodel.MainViewModel

class ParquesActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var comunidad: Comunidad
    private lateinit var adapter: AdapterParque
    private lateinit var binding: ActivityParquesBinding
    private lateinit var launcherDetail : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParquesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        comunidad = intent.getSerializableExtra("comunidad") as Comunidad
        title = "Todos los Parques de España"
        if (comunidad.id != 0L)
            title = "Parques de ${comunidad.nombre}"
        initRV()
        getParques()
        launcherDetail = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    getParques()
                }
            }
        }
        /*
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
         */
    }

    private fun initRV() {
        adapter = AdapterParque(this,R.layout.rowparque)
        binding.cp.rvparques.adapter = adapter
        binding.cp.rvparques.layoutManager = LinearLayoutManager(this)
    }

    private fun getParques() {
        if (comunidad.id==0L) {
            viewModel.getParques().observe(this, Observer { it ->
                it?.let{
                    adapter.setParques(it)
                }
            })
        }
        else {
            viewModel.getParquesByComunidad(comunidad.id).observe(this, Observer { it ->
                it?.let{
                    adapter.setParques(it)
                }
            })
        }
    }

    fun onClickParque(v: View){
        val parque = v.tag as Parque

        val intent = Intent(this, ParqueDetailActivity::class.java)
        intent.putExtra("parque", parque)
        launcherDetail.launch(intent)
    }

}