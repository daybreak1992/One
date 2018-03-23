package com.tanghong.one.repair

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DBTable(val name: String = "") {

}