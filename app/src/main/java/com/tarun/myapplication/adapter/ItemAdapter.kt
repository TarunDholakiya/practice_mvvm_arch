package com.tarun.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tarun.myapplication.MyApplication
import com.tarun.myapplication.R
import com.tarun.myapplication.model.User
import com.tarun.myapplication.utils.CircleTransform
import kotlinx.android.synthetic.main.item_list.view.*


class ItemAdapter(private val items: List<User>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: User) {
            itemView.apply {
                item.apply {
                    tvName.text = "${name.first} ${name.last}"
                    tvEmail.text = email
                    tvCellNo.text = cell

                    if (status != null) {
                        tvStatus.visibility = View.VISIBLE
                        llActionButton.visibility = View.GONE
                        tvStatus.text =
                            if (status as Boolean) context.getString(R.string.accepted)
                            else context.getString(
                                R.string.declined
                            )
                    } else {
                        tvStatus.visibility = View.GONE
                        llActionButton.visibility = View.VISIBLE
                        btnAccept.setOnClickListener {
                            item.status = true
                            MyApplication.db.userDao().updateUser(item)
                            notifyItemChanged(adapterPosition)
                        }
                        btnDecline.setOnClickListener {
                            item.status = false
                            MyApplication.db.userDao().updateUser(item)
                            notifyItemChanged(adapterPosition)
                        }
                    }
                    Picasso.get()
                        .load(picture.large)
                        .transform(CircleTransform())
                        .into(ivUserImage)
                }
            }
        }
    }
}