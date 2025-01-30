package com.yunseong.lostark.gold

import com.yunseong.lostark.database.FileDatabase
import java.io.File
import java.time.DayOfWeek
import java.time.LocalDate

object GoldScraping {

    fun scraping() {
        val dateRegex = Regex("""(\d{4}.\s\d{1,2}.\s\d{1,2})""")
        val ratioRegex = Regex("""\d{3}\s?:\s?(\d{2})""")
        val priceRegex = Regex("""([0-9]+)만""")

        val directory = File("")
        val total = File("")

        val map = mutableMapOf<LocalDate, Gold>()

        var date: String? = null
        var ratio: String? = null
        var price: String? = null

        val files = directory.listFiles()

        println("스크래핑을 시작합니다. 총 파일 개수 : ${files?.size}")

        files?.forEachIndexed { index, file ->
            FileDatabase.read(file.absolutePath).forEach { line ->
                dateRegex.find(line)?.let {
                    date = it.groupValues[1]
                }
                ratioRegex.find(line)?.let {
                    ratio = it.groupValues[1]
                }
                priceRegex.find(line)?.let {
                    price = it.groupValues[1]
                }
                if (date != null && ratio != null && price != null) {
                    val rt = ratio!!.toDouble()
                    val pr = price!!.toDouble()

                    val localDate = date!!.replace("년", ".").replace("월", ".").replace(" ", "").split(".").let {
                        LocalDate.of(it[0].toInt(), it[1].toInt(), it[2].toInt())
                    }

                    map.computeIfAbsent(localDate) { Gold(rt, pr) }.addValue(rt, pr)
                    date = null
                    ratio = null
                    price = null
                }
            }
            println("${file.name} 파일을 처리했습니다. 현황 : (${index + 1}/${files.size})")
        }

        println("결과 파일을 작성합니다.")

        if (total.exists()) {
            total.delete()
        }
        total.createNewFile()

        val sortedMap = map.toSortedMap()

        sortedMap.forEach { (date, gold) ->
            FileDatabase.append(total.absolutePath, "$date,${date.dayOfWeek.toKorean()},${String.format("%.01f", gold.ratio)},${gold.price}\n")
        }

        println("완료.")
    }

    fun DayOfWeek.toKorean(): String {
        return when (this) {
            DayOfWeek.MONDAY -> "월"
            DayOfWeek.TUESDAY -> "화"
            DayOfWeek.WEDNESDAY -> "수"
            DayOfWeek.THURSDAY -> "목"
            DayOfWeek.FRIDAY -> "금"
            DayOfWeek.SATURDAY -> "토"
            DayOfWeek.SUNDAY -> "일"
        }
    }
}