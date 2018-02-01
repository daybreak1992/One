package com.tanghong.commonlibrary.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class JsonUtils private constructor() {

    init {
        throw Throwable("Not allowed to initialize.")
    }

    companion object {

        private val gson = Gson()

        fun <T> deserializeFromJson(json: String, clazz: Class<T>): T {
            try {
                return gson.fromJson(json, clazz)
            } catch (e: JsonSyntaxException) {
                throw e
            }
        }

        fun <T> deserializeFromJson(json: String, type: Type): T? {
            try {
                return gson.fromJson(json, type)
            } catch (e: JsonSyntaxException) {
                throw e
            }
        }

        fun serializeToJson(obj: Any): String = gson.toJson(obj)

        fun formatJson(jsonStr: String): String {
            var level = 0
            val json = StringBuilder()
            for (i in 0 until jsonStr.length) {
                val c = jsonStr[i]
                if (level > 0 && '\n' == json[json.length - 1]) {
                    json.append(getLevelStr(level))
                }
                when (c) {
                    '{', '[' -> {
                        json.append(c + "\n")
                        level++
                    }
                    ',' -> json.append(c + "\n")
                    '}', ']' -> {
                        json.append("\n")
                        level--
                        json.append(getLevelStr(level))
                        json.append(c)
                    }
                    else -> json.append(c)
                }
            }
            return json.toString()
        }

        private fun getLevelStr(level: Int): String {
            val levelStr = StringBuilder()
            for (levelI in 0 until level) {
                levelStr.append("\t")
            }
            return levelStr.toString()
        }
    }
}