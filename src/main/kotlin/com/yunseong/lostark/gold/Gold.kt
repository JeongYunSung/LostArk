package com.yunseong.lostark.gold

class Gold(var ratio: Double, var price: Double) {

    fun addValue(ratio: Double, price: Double) {
        this.ratio = ((this.ratio * this.price) + (ratio * price)) / (this.price + price)
        this.price += price
    }
}