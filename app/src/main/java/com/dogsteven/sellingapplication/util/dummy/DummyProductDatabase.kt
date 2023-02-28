package com.dogsteven.sellingapplication.util.dummy

import com.dogsteven.sellingapplication.domain.model.remote.Product
import javax.inject.Inject

class DummyProductDatabase @Inject constructor() {
    val products: ArrayList<Product> = arrayListOf(
        Product(
            id = 0,
            name = "Cơm tấm",
            imageURL = "com_tam.png",
            prices = listOf(15, 20, 25, 30)
        ),
        Product(
            id = 0,
            name = "Hủ tiếu",
            imageURL = "hu_tieu.png",
            prices = listOf(20, 25, 30, 35)
        ),
        Product(
            id = 0,
            name = "Mì quảng",
            imageURL = "mi_quant.png",
            prices = listOf(20, 30)
        ),
    )
}