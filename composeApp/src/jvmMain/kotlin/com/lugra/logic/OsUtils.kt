package com.lugra.logic

enum class OS { WINDOWS, MACOS, LINUX, UNKNOWN }

fun getOS(): OS {
    val osName = System.getProperty("os.name").lowercase()

    return when {
        osName.contains("win") -> OS.WINDOWS
        osName.contains("mac") -> OS.MACOS
        osName.contains("nix") || osName.contains("nux") || osName.contains("aix") -> OS.LINUX
        else -> OS.UNKNOWN
    }
}
