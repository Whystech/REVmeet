package org.ianus.revmeet.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.ianus.revmeet.helper.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

private const val CAR_JSON_FILE = "cars.json"
private val carGsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
private val carListType: Type =
    object : TypeToken<ArrayList<CarModel>>() {}.type

private fun generateCarRandomId(): Long {
    return Random().nextLong()
}

class CarJSONStore(private val context: Context) : CarStore {

    var cars = mutableListOf<CarModel>()

    init {
        if (exists(context, CAR_JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CarModel> {
        logAll()
        return cars
    }

    override fun findById(id: Long): CarModel? {
        return cars.find { it.id == id }
    }

    override fun create(car: CarModel) {
        car.id = generateCarRandomId()
        cars.add(car)
        serialize()
    }

    override fun update(car: CarModel) {
        val foundCar = cars.find { it.id == car.id }

        if (foundCar != null) {
            foundCar.make = car.make
            foundCar.color = car.color
            foundCar.name = car.name
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = carGsonBuilder.toJson(cars, carListType)
        write(context, CAR_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, CAR_JSON_FILE)
        cars = carGsonBuilder.fromJson(jsonString, carListType)
    }

    override fun delete(car: CarModel) {
        cars.remove(car)
        serialize()
    }

    private fun logAll() {
        cars.forEach { Timber.i("$it") }
    }
}