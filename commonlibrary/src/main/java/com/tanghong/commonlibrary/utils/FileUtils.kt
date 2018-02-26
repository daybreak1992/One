package com.tanghong.commonlibrary.utils

import java.io.File

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class FileUtils private constructor() {

    init {
        throw Throwable("Not allowed to initialize.")
    }

    companion object {

        /**
         * KB与Byte的倍数
         */
        val KB = 1024
        /**
         * MB与Byte的倍数
         */
        val MB = 1048576
        /**
         * GB与Byte的倍数
         */
        val GB = 1073741824

        fun getDirSize(dir: File?): String {
            val len = getDirLength(dir)
            return if (len == -1L) "" else byte2FitMemorySize(len)
        }

        fun byte2FitMemorySize(byteNum: Long): String {
            return if (byteNum < 0) {
                "0"
            } else if (byteNum < KB) {
                String.format("%.2fB", byteNum.toDouble() + 0.0005)
            } else if (byteNum < MB) {
                String.format("%.2fKB", byteNum.toDouble() / KB + 0.0005)
            } else if (byteNum < GB) {
                String.format("%.2fMB", byteNum.toDouble() / MB + 0.0005)
            } else {
                String.format("%.2fGB", byteNum.toDouble() / GB + 0.0005)
            }
        }

        fun getDirLength(dir: File?): Long {
            if (!isDir(dir)) return -1L
            var len: Long = 0
            val files = dir?.listFiles()
            if (files != null && files.size != 0) {
                for (file in files) {
                    if (file.isDirectory) {
                        len += getDirLength(file)
                    } else {
                        len += file.length()
                    }
                }
            }
            return len
        }

        fun isDir(file: File?): Boolean {
            return file != null && file.exists() && file.isDirectory
        }

        fun isFileExists(file: File?): Boolean {
            return file != null && file.exists()
        }

        fun deleteFilesInDir(dir: File?): Boolean {
            if (dir == null) return false
            // 目录不存在返回true
            if (!dir.exists()) return true
            // 不是目录返回false
            if (!dir.isDirectory) return false
            // 现在文件存在且是文件夹
            val files = dir.listFiles()
            if (files != null && files.size != 0) {
                for (file in files) {
                    if (file.isFile) {
                        if (!deleteFile(file)) return false
                    } else if (file.isDirectory) {
                        if (!deleteDir(file)) return false
                    }
                }
            }
            return true
        }

        fun deleteFile(file: File?): Boolean {
            return file != null && (!file.exists() || file.isFile && file.delete())
        }

        fun deleteDir(dir: File?): Boolean {
            if (dir == null) return false
            // 目录不存在返回true
            if (!dir.exists()) return true
            // 不是目录返回false
            if (!dir.isDirectory) return false
            // 现在文件存在且是文件夹
            val files = dir.listFiles()
            if (files != null && files.size != 0) {
                for (file in files) {
                    if (file.isFile) {
                        if (!deleteFile(file)) return false
                    } else if (file.isDirectory) {
                        if (!deleteDir(file)) return false
                    }
                }
            }
            return dir.delete()
        }
    }
}