package com.ti89.entity

import com.ti89.entity.metrics.PlayerMetrics

data class Player(val nickname: String, val id: String,val password: String, val level: String, val experience: Long, val metrics: PlayerMetrics)
