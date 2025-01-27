package com.yunseong.lostark

import kotlin.math.max

enum class Ancestors {
        
        테메르의정,
        쿠훔바르의모루,
        겔라르의칼,
        갈라투르의망치,
        나베르의송곳,
        에베르의끌;
        
        operator fun get(base: Double): Double {
                return when(this) {
                        테메르의정 -> base + 10
                        쿠훔바르의모루 -> base + 30
                        겔라르의칼 -> base * 3
                        갈라투르의망치 -> base * 5
                        나베르의송곳 -> base + ((30 * 0.25) + (80 * 0.125) + (base * 5 * 0.125) + (base * 7 * 0.125) + (100 - (base * 6)) / 5)
                        에베르의끌 -> base + max(100 - (base * 6), 0.0)
                }
        }
}