package com.example.ml_poc

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import com.google.gson.GsonBuilder
import java.text.DateFormat
import java.time.LocalDate


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    data class DateWrapper(val date: Date?)

    data class PrintEvent(
        var url: String,
        var datetime: String = Date().toString()
    )

    @Test
    fun date_serialization() {
        val gson = GsonBuilder().registerTypeAdapter(Date::class.java, DateAdapter()).create()
        val gson2 = Gson()

        val dia3 = "{\"datetime\":\"Jan 24, 2022 AM\"}"
        //val json = gson2.toJson(dia3)
        val df = DateFormat.getDateInstance(DateFormat.MILLISECOND_FIELD, Locale.getDefault())
        val converter2:PrintEvent = gson.fromJson(dia3, PrintEvent::class.java)
        print(df.parse(converter2.datetime)?.time?.minus(Date().time))
    }

    class DateAdapter : JsonDeserializer<Date>, JsonSerializer<Date> {

        private val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())

        override fun serialize(
            src: Date,
            typeOfSrc: Type,
            context: JsonSerializationContext
        ): JsonElement {
            return JsonPrimitive(df.format(src))
        }

        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Date {
            return df.parse(json.asString)!!
        }
    }
}