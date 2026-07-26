plugins {
    alias(libs.plugins.agp.app) apply false
    alias(libs.plugins.agp.test) apply false
    alias(libs.plugins.androidx.baselineprofile) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.compose.compiler) apply false
}

val androidMinSdkVersion by extra(26)
val androidTargetSdkVersion by extra(37)
val androidCompileSdkVersion by extra(37)
val androidBuildToolsVersion by extra("36.1.0")
val androidCompileNdkVersion by extra(libs.versions.ndk.get())
val androidSourceCompatibility by extra(JavaVersion.VERSION_21)
val androidTargetCompatibility by extra(JavaVersion.VERSION_21)

// 远程获取 commit 数量，与原版 ReSukiSU 保持一致
fun getRemoteCommitCount(): Int {
    return providers.exec {
        commandLine(
            "sh", "-c",
            "git fetch --quiet https://github.com/ReSukiSU/ReSukiSU.git main && git rev-list --count FETCH_HEAD"
        )
    }.standardOutput.asText.get().trim().toInt()
}

// 远程获取最新 tag
fun getRemoteTag(): String {
    return providers.exec {
        commandLine(
            "sh", "-c",
            "git ls-remote --tags --sort=-v:refname https://github.com/ReSukiSU/ReSukiSU.git | head -n1 | awk -F/ '{print \$3}' | sed 's/\\^{}//'"
        )
    }.standardOutput.asText.get().trim()
}

val managerVersionCode by extra(30000 + getRemoteCommitCount() + 700)
val managerVersionName by extra(getRemoteTag())
