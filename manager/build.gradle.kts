plugins {
    alias(libs.plugins.agp.app) apply false
    alias(libs.plugins.agp.test) apply false
    alias(libs.plugins.androidx.baselineprofile) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.compose.compiler) apply false
}

extra["androidMinSdkVersion"] = 26
extra["androidTargetSdkVersion"] = 37
extra["androidCompileSdkVersion"] = 37
extra["androidBuildToolsVersion"] = "36.1.0"
extra["androidCompileNdkVersion"] = libs.versions.ndk.get()
extra["androidSourceCompatibility"] = JavaVersion.VERSION_21
extra["androidTargetCompatibility"] = JavaVersion.VERSION_21

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

extra["managerVersionCode"] = 30000 + getRemoteCommitCount() + 700
extra["managerVersionName"] = getRemoteTag()
