package com.yigitco.springsecurityclient.util

import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class UtilizationFunctions {


    public fun getApplicationURL(httpServletRequest: HttpServletRequest): String {
        return "http://" + httpServletRequest.serverName + ":" + httpServletRequest.serverPort + httpServletRequest.contextPath;
    }
}