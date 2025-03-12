package uz.devmi.rortyexperience.core

import kotlinx.serialization.json.Json

val Json.Default.actual: Json
    get() = lazyJson

private val lazyJson: Json by lazy {
    Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
        coerceInputValues = true
        explicitNulls = false
    }
}