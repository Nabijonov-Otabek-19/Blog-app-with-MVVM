package uz.nabijonov.otabek.blogapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_item.view.*
import uz.nabijonov.otabek.blogapp.R
import uz.nabijonov.otabek.blogapp.model.UserModel

class UserAdapter(private val items: List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.ItemHolder>() {

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.tvName.text = item.firstName
        Glide.with(holder.itemView.context).load(item.picture).into(holder.itemView.imgUser)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}