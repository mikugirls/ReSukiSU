package com.resukisu.resukisu.ui.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AdminPanelSettings
import androidx.compose.material.icons.twotone.Archive
import androidx.compose.material.icons.twotone.Extension
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.resukisu.resukisu.R
import com.resukisu.resukisu.ui.screen.main.HomePage
import com.resukisu.resukisu.ui.screen.main.KpmPage
import com.resukisu.resukisu.ui.screen.main.ModulePage
import com.resukisu.resukisu.ui.screen.main.SettingsPage
import com.resukisu.resukisu.ui.screen.main.SuperUserPage

enum class BottomBarDestination(
    val direction: @Composable (bottomPadding: Dp) -> Unit,
    @param:StringRes val label: Int,
    val iconSelected: ImageVector,
    val iconNotSelected: ImageVector,
    val rootRequired: Boolean,
) {
    Home(
        { bottomPadding -> HomePage(bottomPadding) },
        R.string.home,
        Icons.TwoTone.Home,
        Icons.TwoTone.Home,
        false
    ),
    Kpm(
        { bottomPadding -> KpmPage(bottomPadding) },
        R.string.kpm_title,
        Icons.TwoTone.Archive,
        Icons.TwoTone.Archive,
        true
    ),
    SuperUser(
        { bottomPadding -> SuperUserPage(bottomPadding) },
        R.string.superuser,
        Icons.TwoTone.AdminPanelSettings,
        Icons.TwoTone.AdminPanelSettings,
        true
    ),
    Module(
        { bottomPadding -> ModulePage(bottomPadding) },
        R.string.module,
        Icons.TwoTone.Extension,
        Icons.TwoTone.Extension,
        true
    ),
    Settings(
        { bottomPadding -> SettingsPage(bottomPadding) },
        R.string.settings,
        Icons.TwoTone.Settings,
        Icons.TwoTone.Settings,
        false
    );

    companion object {
        /**
         * 获取底部导航栏页面列表
         * @param isKsuValid 是否在 KSU 环境下（即 root 环境）
         * @param hideKpm 是否隐藏 KPM 页面（来自用户设置）
         * @param kpmVersion KPM 版本号，若为空或 null 则视为 KPM 不可用
         */
        fun getPages(
            isKsuValid: Boolean,
            hideKpm: Boolean,
            kpmVersion: String?
        ): List<BottomBarDestination> {
            return if (isKsuValid) {
                BottomBarDestination.entries.filter {
                    when (it) {
                        Kpm -> !hideKpm && !kpmVersion.isNullOrEmpty()
                        else -> true
                    }
                }
            } else {
                BottomBarDestination.entries.filter { !it.rootRequired }
            }
        }
    }
}
