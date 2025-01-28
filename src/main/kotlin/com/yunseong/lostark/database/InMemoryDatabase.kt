package com.yunseong.lostark.database

import com.yunseong.lostark.vo.Materials

object InMemoryDatabase {

    private val database = mutableMapOf<Materials, Double>()

    init {
        database[Materials.명예의_파편] = 0.3975
        database[Materials.운명의_파편] = 0.0995
        database[Materials.최상급_오레하_융화제] = 50.6
        database[Materials.아비도스_융화_재료] = 73.3
        database[Materials.찬란한_명예의_돌파석] = 31.0
        database[Materials.운명의_돌파석] = 19.0
        database[Materials.정제된_수호강석] = 0.4
        database[Materials.운명의_수호석] = 0.35
        database[Materials.정제된_파괴강석] = 0.82
        database[Materials.운명의_파괴석] = 4.14
        database[Materials.태양의_축복] = 15.2
        database[Materials.태양의_은총] = 33.7
        database[Materials.태양의_가호] = 86.7
        database[Materials.빙하의_숨결] = 171.3
        database[Materials.용암의_숨결] = 573.7
        database[Materials.장인의_재봉술1] = 1000.0
        database[Materials.장인의_재봉술2] = 3800.0
        database[Materials.장인의_야금술1] = 1700.0
        database[Materials.장인의_야금술2] = 9000.0
        database[Materials.골드] = 1.0
        database[Materials.Blank] = 0.0
    }

    operator fun get(key: Materials): Double {
        return database[key]!!
    }

    operator fun set(key: Materials, value: Double) {
        database[key] = value
    }
}