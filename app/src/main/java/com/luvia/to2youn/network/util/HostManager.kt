package com.luvia.to2youn.network.util

import com.luvia.to2youn.common.PreferenceManager

class HostManager {

    companion object {

        //emulator
        private const val HOST_PRD = "https://10.0.2.2:8000"
        private const val HOST_STG = "https://10.0.2.2:8000/stg"
        private const val HOST_DEV = "https://10.0.2.2:8000/dev"

        //git
        private const val HOST_GIT_PRD = "https://api.github.com"
        private const val HOST_GIT_STG = "https://api.github.com"
        private const val HOST_GIT_DEV = "https://api.github.com"

        fun getHost(preferenceManager: PreferenceManager): String {
            var host = ""
            host = when(preferenceManager.getHostMode()){
                "PRD" -> {
                    HOST_PRD
                }
                "STG" -> {
                    HOST_STG
                }
                "DEV" -> {
                    HOST_DEV
                }
                else -> {
                    HOST_PRD
                }
            }
            return host
        }

        fun getGitHubHost(preferenceManager: PreferenceManager): String {
            var host = ""
            host = when(preferenceManager.getHostMode()){
                "PRD" -> {
                    HOST_GIT_PRD
                }
                "STG" -> {
                    HOST_GIT_STG
                }
                "DEV" -> {
                    HOST_GIT_DEV
                }
                else -> {
                    HOST_GIT_PRD
                }
            }
            return host
        }

    }
}