package com.yunseong.lostark.vo

enum class Materials {

    명예의_파편,
    운명의_파편,
    최상급_오레하_융화제,
    아비도스_융화_재료,
    찬란한_명예의_돌파석,
    운명의_돌파석,
    정제된_수호강석,
    운명의_수호석,
    정제된_파괴강석,
    운명의_파괴석,
    태양의_축복,
    태양의_은총,
    태양의_가호,
    빙하의_숨결,
    용암의_숨결,
    장인의_재봉술1단계,
    장인의_재봉술2단계,
    장인의_야금술1단계,
    장인의_야금술2단계,
    골드,
    Blank;

    companion object {

        fun fromString(value: String): Materials? {
            return runCatching {
                when (value) {
                    "명예의 파편 주머니(소)" -> {
                        명예의_파편
                    }

                    "운명의 파편 주머니(소)" -> {
                        운명의_파편
                    }

                    else -> {
                        Materials.valueOf(value.replace(" ", "_").replace(":", ""))
                    }
                }
            }.getOrNull()
        }
    }
}