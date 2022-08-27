package com.ti89.entity.metrics.ardor

data class ArdorMetrics(val gamesPlayed: MutableCollection<ArdorGameStatistic>, val averageBlockedDamage: Double, val averageDamage: Double)