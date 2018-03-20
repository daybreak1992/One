package com.tanghong.commonlibrary.utils

import android.content.Context
import android.util.Log
import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader
import java.io.File
import java.lang.reflect.Array


/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object FixDexUtils {

    val TAG = "FixDex"
    val DEX_SUFFIX = ".dex"
    val APK_SUFFIX = ".apk"
    val JAR_SUFFIX = ".jar"
    val ZIP_SUFFIX = ".zip"
    val DEX_DIR = "odex"
    val OPTIMIZE_DEX_DIR = "optimize_dex"
    val loadedDex = HashSet<File>()

    init {
        loadedDex.clear()
    }

    /**
     * 加载补丁
     *
     * @param context       上下文
     * @param patchFilesDir 补丁所在目录
     */
    fun loadFixedDex(context: Context, patchFilesDir: File? = null) {
        val fileDir = patchFilesDir ?: File(context.filesDir, DEX_DIR)
        val listFiles = fileDir.listFiles()
        for (file in listFiles) {
            if (file.name.startsWith("classes") &&
                    (file.name.endsWith(DEX_SUFFIX) ||
                            file.name.endsWith(APK_SUFFIX) ||
                            file.name.endsWith(JAR_SUFFIX) ||
                            file.name.endsWith(ZIP_SUFFIX))) {
                Log.i(TAG, "dex path = ${file.absolutePath}")
                loadedDex.add(file)
            }
        }

        doDexInject(context, loadedDex)
    }

    private fun doDexInject(context: Context, loadedDex: HashSet<File>) {
        val optimizeDir = context.filesDir.absolutePath + File.separator + OPTIMIZE_DEX_DIR
        val fopt = File(optimizeDir)
        if (!fopt.exists()) {
            fopt.mkdirs()
        }
        val pathLoader = context.classLoader as PathClassLoader
        for (dex in loadedDex) {
            val dexLoader = DexClassLoader(dex.absolutePath, fopt.absolutePath, null, pathLoader)
            val dexPathList = getPathList(dexLoader)
            val pathPathList = getPathList(pathLoader)
            val leftDexElements = getDexElements(dexPathList)
            Log.i(TAG, "leftDexElements = ${JsonUtils.serializeToJson(leftDexElements)}")
            val rightDexElements = getDexElements(pathPathList)
            Log.i(TAG, "rightDexElements = ${JsonUtils.serializeToJson(rightDexElements)}")
            val dexElements = combineArray(leftDexElements, rightDexElements)
            Log.i(TAG, "dexElements = ${JsonUtils.serializeToJson(dexElements)}")
            val pathList = getPathList(pathLoader)
            setField(pathList, pathList::class.java, "dexElements", dexElements)
        }
    }

    /**
     * 反射给对象中的属性重新赋值
     */
    fun setField(obj: Any, cls: Class<*>, filed: String, value: Any) {
        val declaredField = cls.getDeclaredField(filed)
        declaredField.isAccessible = true
        declaredField.set(obj, value)
    }

    /**
     * 反射得到对象中的属性值
     */
    fun getField(obj: Any, cls: Class<*>, filed: String): Any {
        val localField = cls.getDeclaredField(filed)
        localField.isAccessible = true
        return localField.get(obj)
    }

    /**
     * 反射得到类加载器中的pathList对象
     */
    fun getPathList(baseDexClassLoader: Any): Any {
        return getField(baseDexClassLoader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList")
    }

    /**
     * 反射得到pathList中的dexElements
     */
    fun getDexElements(pathList: Any): Any {
        return getField(pathList, pathList::class.java, "dexElements")
    }

    /**
     * 数组合并
     */
    fun combineArray(arrayLhs: Any, arrayRhs: Any): Any {
        val componentType = arrayLhs::class.java.componentType
        val i = Array.getLength(arrayLhs)
        val j = Array.getLength(arrayRhs)
        val k = i + j
        val result = Array.newInstance(componentType, k)
        System.arraycopy(arrayLhs, 0, result, 0, i)
        System.arraycopy(arrayRhs, 0, result, i, j)
        return result
    }
}