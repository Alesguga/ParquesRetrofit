package net.azarquiel.parquesretrofit.view

import android.app.ActionBar.LayoutParams
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import net.azarquiel.parquesretrofit.databinding.ActivityParqueDetailBinding
import net.azarquiel.parquesretrofit.model.Imagen
import net.azarquiel.parquesretrofit.model.Parque
import net.azarquiel.parquesretrofit.viewmodel.MainViewModel

class ParqueDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var parque: Parque
    private lateinit var binding: ActivityParqueDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParqueDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parque = intent.getSerializableExtra("parque") as Parque
        title = parque.nombre
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getImages()
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { v ->
            onclickLike()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setResult(Activity.RESULT_OK,intent) //volvemos
                finish()
            }
        })
    }


    private fun onclickLike() {
        viewModel.setLike(parque.id).observe(this, Observer { it ->
            it?.let{
                updateLikes()
            }
        })
    }

    private fun updateLikes() {
        Toast.makeText(this, "Anotado un nuevo like...", Toast.LENGTH_SHORT).show()
        parque.likes = parque.likes + 1
        binding.cpd.tvlikes.text = "${parque.likes}"
    }

    private fun getImages() {
        viewModel.getImagenes(parque.id).observe(this, Observer { it ->
            it?.let{
                showParque(it)
            }
        })

    }

    private fun showParque(imagenes: List<Imagen>) {
        binding.cpd.tvnombredetail.text = parque.nombre
        binding.cpd.tvdescripcion.text = Html.fromHtml(parque.descripcion)
        binding.cpd.tvlikes.text = "${parque.likes}"
        imagenes.forEach {
            val iv = ImageView(this)
            val lp = LayoutParams(1000, LinearLayout.LayoutParams.WRAP_CONTENT)
            iv.layoutParams = lp
            iv.setPadding(4)
            iv.adjustViewBounds = true
            iv.scaleType = ImageView.ScaleType.FIT_XY
            Picasso.get().load(it.foto).into(iv)
            binding.cpd.lv.addView(iv)
        }
        Picasso.get().load(parque.fondo).into(binding.cpd.ivfondo)
        Picasso.get().load(parque.mapa).into(binding.cpd.ivmapa)
    }
}