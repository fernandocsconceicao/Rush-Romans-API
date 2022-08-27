package com.ti89.entity.metrics

import com.ti89.entity.metrics.ardor.ArdorMetrics
import java.time.LocalDateTime

data class PlayerMetrics(
        val timesPlayed:Long,
        val averageTimeLogged: LocalDateTime,
        val ardorMetrics: ArdorMetrics


)
