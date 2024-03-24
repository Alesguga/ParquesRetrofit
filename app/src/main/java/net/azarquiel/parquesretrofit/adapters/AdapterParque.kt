package net.azarquiel.parquesretrofit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.parquesretrofit.R
import net.azarquiel.parquesretrofit.model.Comunidad
import net.azarquiel.parquesretrofit.model.Parque

/**
 * Created by pacopulido
 */
class AdapterParque(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<AdapterParque.ViewHolder>() {

    private var dataList: List<Parque> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setParques(parques: List<Parque>) {
        this.dataList = parques
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Parque){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvrowparque = itemView.findViewById<TextView>(R.id.tvrowparque) as TextView
            val ivrowparque = itemView.findViewById<ImageView>(R.id.ivrowparque) as ImageView

            tvrowparque.text = dataItem.nombre
            // foto de internet a traves de Picasso
            Picasso.get().load(dataItem.imagen).into(ivrowparque)

            itemView.tag = dataItem

        }

    }
}