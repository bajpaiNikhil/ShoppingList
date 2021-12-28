package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository (

    private var db : ShoppingDatabase
    ){
    suspend fun upsert(item:ShoppingItem) = db.getShoppingDoa().upset(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDoa().delete(item)

    fun getAllShoppingItem() = db.getShoppingDoa().getAllShoppingItem()
}
