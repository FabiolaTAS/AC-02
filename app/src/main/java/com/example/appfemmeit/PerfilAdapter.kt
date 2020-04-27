package com.example.appfemmeit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PerfilAdapter (
    val perfil: List<Perfil>,
    val onClick: (Perfil) -> Unit): RecyclerView.Adapter<PerfilAdapter.PefilViewHoder>()
    {

            class PefilViewHoder(view: View): RecyclerView.ViewHolder(view){
                val cardNome: TextView
                val card_img: ImageView
                val cardProgress: ProgressBar
                val cardView: CardView

                init {
                    cardNome = view.findViewById(R.id.cardNome)
                    card_img = view.findViewById(R.id.card_img)
                    cardProgress = view.findViewById(R.id.cardProgress)
                    cardView = view.findViewById(R.id.card_perfil)
                }
            }

        override fun getItemCount() = this.perfil.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PefilViewHoder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_perfil, parent,false)
            val holder = PefilViewHoder(view)

            return holder
        }

        override fun onBindViewHolder(holder: PefilViewHoder, position: Int) {
            val context = holder.itemView.context
            val perfil = perfil[position]

            holder.cardNome.text = perfil.nome
            holder.cardProgress.visibility = View.VISIBLE

            Picasso.with(context).load(perfil.img).fit().into(
                holder.card_img,
                object : com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }
                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                }
            )
            holder.itemView.setOnClickListener{ onClick(perfil)}
        }
   }
