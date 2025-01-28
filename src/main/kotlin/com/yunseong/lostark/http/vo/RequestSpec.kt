package com.yunseong.lostark.http.vo

sealed class RequestSpec(
    open val bearerToken: String
)