package com.example.mvpfirstproject.model.local.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.mvpfirstproject.model.remote.data.ProductsData

class ProductDao(context: Context) {
    private val db = DBHelper(context).writableDatabase

    fun addProduct(product: ProductsData): Long {
        val values = ContentValues()
        values.put("product_name", product.productName)
        values.put("price", product.price)
        values.put("qty", product.quantity)
        values.put("image", product.image)

        return db.insert("product", null, values)
    }

    @SuppressLint("Range")
    fun getProducts(): ArrayList<ProductsData> {
        val products = ArrayList<ProductsData>()
        val cursor = db.query("product", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("product_id"))
            val name = cursor.getString(cursor.getColumnIndex("product_name"))
            val price = cursor.getDouble(cursor.getColumnIndex("price"))
            val qty = cursor.getInt(cursor.getColumnIndex("qty"))
            val image = cursor.getString(cursor.getColumnIndex("image"))

            val product = ProductsData(
                _id = id.toString(),
                price = price.toString().toDouble(),
                productName = name,
                quantity = qty,
                image = image
            )

            products.add(product)
        }
        return products
    }

    fun deleteTable() {
        db.delete("product", null, null)
    }

    fun deleteProduct(id: String) {
        val whereClause = "product_id =?"
        val whereArgs = arrayOf(id)
        db.delete("product", whereClause, whereArgs)
    }

    fun updateProduct(product: ProductsData, qty: Int) {
        val values = ContentValues()
        values.put("product_name", product.productName)
        values.put("price", product.price)
        values.put("qty", qty)
        values.put("image", product.image)

        val whereClause = "product_id =?"
        val whereArgs = arrayOf(product._id)
        db.update("product", values, whereClause, whereArgs) > 0
    }
}