package com.resukisu.resukisu.ui.util

import com.resukisu.resukisu.data.shell.KsuCliRepository
import com.topjohnwu.superuser.Shell
import org.koin.java.KoinJavaComponent

/**
 * KPM 顶层工具函数，委托给 [KsuCliRepository]
 */
private val ksuCliRepository: KsuCliRepository by lazy {
    KoinJavaComponent.get(KsuCliRepository::class.java)
}

fun getKpmVersion(): String = ksuCliRepository.getKpmVersion()
fun getKpmModuleCount(): Int = ksuCliRepository.getKpmModuleCount()
fun getKpmModuleInfo(name: String): String = ksuCliRepository.getKpmModuleInfo(name)
fun listKpmModules(): String = ksuCliRepository.listKpmModules()
fun controlKpmModule(name: String, args: String? = null): Int =
    ksuCliRepository.controlKpmModule(name, args)
fun loadKpmModule(path: String, args: String? = null): String =
    ksuCliRepository.loadKpmModule(path, args)
fun unloadKpmModule(name: String): String =
    ksuCliRepository.unloadKpmModule(name)

/**
 * 获取 root shell
 * @param globalMnt 是否使用全局挂载模式
 */
fun getRootShell(globalMnt: Boolean = false): Shell =
    ksuCliRepository.getRootShell(globalMnt)
