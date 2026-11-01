package org.ianus.revmeet.models

import org.ianus.revmeet.models.CarModel
import org.ianus.revmeet.models.CarStore
import timber.log.Timber.i

var lastCarId = 0L

internal fun getCarId(): Long {
    return lastCarId++
}

class CarMemStore : CarStore {
    val cars = ArrayList<CarModel>()

    override fun findAll(): List<CarModel> {
        return cars
    }

    override fun findById(id:Long) : CarModel? {
        val foundCar: CarModel? = cars.find { it.id == id }
        return foundCar
    }

    override fun create(car: CarModel) {
        car.id = getCarId()
        cars.add(car)
        logAll()
    }

    ///added additional update possibilities as the ing has extended properties
    override fun update(car: CarModel) {
        val foundCar: CarModel? = cars.find { p -> p.id == car.id }
        if (foundCar != null) {
            foundCar.make = car.make
            foundCar.color  = car.color
            foundCar.name = car.name
            logAll()
        }
    }

    private fun logAll() {
        cars.forEach { i("$it") }
    }

    override fun delete(car: CarModel) {
        cars.remove(car)
    }
}