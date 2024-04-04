package hoka.fixedgear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListFixieAdapter(private val listFixie: ArrayList<Fixie>, private val listener: (Fixie) -> Unit) : RecyclerView.Adapter<ListFixieAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_fixie, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val fixie = listFixie[position]
        holder.bind(fixie)
    }

    override fun getItemCount(): Int = listFixie.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(fixie: Fixie) {
            imgPhoto.setImageResource(fixie.photo)
            tvName.text = fixie.name
            tvDescription.text = fixie.description

            itemView.setOnClickListener {
                listener(fixie)
            }
        }
    }
}
