package org.ianus.revmeet.models

interface CarStore {
    fun findAll(): List<CarModel>
    fun findById(id:Long) : CarModel?
    fun create(car: CarModel)
    fun update(car: CarModel)
    fun delete(car: CarModel)
}