package com.tanghong.joker.app

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class Constants private constructor() {

    companion object {

        //debug
        val DEBUG = true

        //http
        val baseUrl = "http://v3.wufazhuce.com:8000/api/"

        //config
        val platform = "android"
        val version = "4.5.1"
        val channel = "baidu"
        val user_id = null
        val sign = "a472875f55625efe726cdf7b73eae5ee"
        val uuid = "00000000-7a2a-fae7-9c35-fcea0033c587"
        val city = "上海"

        //error
        //error code
        val error_code_request = -1
        //error msg
        val error_msg_request = "request error"
        val error_msg_data = "data error"
    }
}