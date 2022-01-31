package com.example.ebazarek_v2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ebazarek_v2.R
import com.example.ebazarek_v2.model.Product
import kotlinx.android.synthetic.main.product_row.view.*

class ProductsAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Picasso.get().load(products[position].photoURL).into(holder.image)
        //holder.title.text = products[position].title
        //holder.price.text = products[position].price.toString() + "$"

        holder.bindProduct(products[position])

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        //val image: ImageView = itemView.findViewById(R.id.image)
        //val title: TextView = itemView.findViewById(R.id.title)
        //val price: TextView = itemView.findViewById(R.id.price)

        fun bindProduct(product: Product) {


            itemView.title.text = product.title
            itemView.description.text = product.description
            itemView.price.text = "$${product.price}"
            //Picasso.get().load(product.photoURL).fit().into(itemView.image)

        }
    }
}
