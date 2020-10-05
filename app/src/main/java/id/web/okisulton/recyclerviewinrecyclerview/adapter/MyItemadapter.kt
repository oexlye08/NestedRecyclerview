package id.web.okisulton.recyclerviewinrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.web.okisulton.recyclerviewinrecyclerview.R
import id.web.okisulton.recyclerviewinrecyclerview.`interface`.IItemClickListener
import id.web.okisulton.recyclerviewinrecyclerview.model.ItemData
import id.web.okisulton.recyclerviewinrecyclerview.model.ItemGroup

class MyItemadapter(private val context: Context,
                    private val itemList: List<ItemData>?): RecyclerView.Adapter<MyItemadapter.MyViewHolder>() {
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener {

        var txt_tittle:TextView
        var img_item: ImageView
        lateinit var iItemClickListener: IItemClickListener

        fun setClick(iItemClickListener: IItemClickListener){
            this.iItemClickListener = iItemClickListener
        }

        init {
            txt_tittle = view.findViewById(R.id.tvTitle) as TextView
            img_item = view.findViewById(R.id.itemImage) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iItemClickListener.onItemClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList?.size!! ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_tittle.setText(itemList!![position].name)
        Picasso.get().load(itemList[position].image)
            .into(holder.img_item)

        holder.setClick(object :IItemClickListener{
            override fun onItemClickListener(view: View, position: Int) {
                Toast.makeText(context, ""+itemList[position].name, Toast.LENGTH_SHORT).show()
            }

        })
    }
}