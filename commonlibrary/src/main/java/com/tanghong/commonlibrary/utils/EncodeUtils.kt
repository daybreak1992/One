package com.tanghong.commonlibrary.utils

import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class EncodeUtils private constructor() {

    init {
        throw Throwable("Not allowed to initialize.")
    }

    companion object {

        fun urlEncode(url: String, charset: String = "UTF-8"): String {
            try {
                return URLEncoder.encode(url, charset)
            } catch (e: UnsupportedEncodingException) {
                throw AssertionError(e)
            }
        }

        fun urlDecode(url: String, charset: String = "UTF-8"): String {
            try {
                return URLDecoder.decode(url, charset)
            } catch (e: UnsupportedEncodingException) {
                throw AssertionError(e)
            }

        }
    }
}