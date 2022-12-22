package com.example.mvpfirstproject.model.remote

object Constants {
    const val BASE_URL = "https://starfish-app-v2z73.ondigitalocean.app/"
    const val REGISTRATION_END_POINT = "api/auth/register"
    const val LOGIN_END_POINT = "api/auth/login"
    const val CATEGORY_END_POINT = "api/category"
    const val SUBCATEGORY_END_POINT = "api/subcategory?category_id="
    const val SEARCH_END_POINT = "api/products/search/"
    const val PRODUCTS_END_POINT = "api/products/sub/"
    const val PRODUCT_DETAILS_EP = "api/products/"
    const val POST_ADDRESS_END_POINT = "api/address"
    const val GET_ADDRESS_END_POINT = "api/address/"
    const val ORDER_PLACE_END_POINT = "api/orders"
    const val ORDER_LIST_END_POINT = "api/orders/"
    const val ORDER_DETAIL_END_POINT = "api/orders/"
    const val IMAGE_URL = "https://rjtmobile.com/grocery/images/"

    const val LOGIN_DETAILS = "login_details"
    const val FIRST_NAME = "firstName"
    const val EMAIL = "email"
    const val MOBILE = "mobile"
    const val PASSWORD = "password"
    const val USER_ID = "_id"


    const val DB_NAME = "ShopDB"
    const val DB_VERSION = 1
    const val CREATE_PRODUCT_TABLE = """
        CREATE TABLE product(
            product_id INTEGER PRIMARY KEY,
            product_name TEXT,
            price FLOAT,
            qty INTEGER,
            image TEXT
        )
    """
}