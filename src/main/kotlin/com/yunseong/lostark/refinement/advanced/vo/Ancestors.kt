package com.yunseong.lostark.refinement.advanced.vo

enum class Ancestors {

    테메르의정,
    쿠훔바르의모루,
    겔라르의칼,
    갈라투르의망치,
    나베르의송곳,
    에베르의끌;

    operator fun get(base: Double): Double {
        return when (this) {
            테메르의정 -> base + 10
            쿠훔바르의모루 -> base + 30
            겔라르의칼 -> base * 3
            갈라투르의망치 -> base * 5
            나베르의송곳 -> (30 + 80 + 100 + 50 + 100 + ((100 - base) / 2) + 100 + 70 + 140 + 280) / 9.0
            에베르의끌 -> (100 - base) / 2
        }
    }
}