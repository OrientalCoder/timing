package com.dsw.timing.bean

class Student(var id: Long, var name: String) {
    var age: Int = 0
    lateinit var img: String

    constructor(id: Long, name: String, age: Int) : this(id, name) {
        this.age = age
    }

    constructor(id: Long, name: String, age: Int, img: String) : this(id, name) {
        this.age = age
        this.img = img
    }
}