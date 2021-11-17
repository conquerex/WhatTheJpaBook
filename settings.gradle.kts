pluginManagement {
    val kotlinPluginVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinPluginVersion
//        kotlin("plugin.allopen") version kotlinPluginVersion
//        kotlin("plugin.noarg") version kotlinPluginVersion
//        kotlin("kapt") version kotlinPluginVersion
        kotlin("plugin.jpa") version kotlinPluginVersion
//        kotlin("plugin.spring") version kotlinPluginVersion
    }
}

rootProject.name = "WhatTheJpaBook"
include("Ch02")
include("Ch10")
include("Ch12")