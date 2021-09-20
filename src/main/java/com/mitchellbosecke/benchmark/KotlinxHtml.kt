package com.mitchellbosecke.benchmark

import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.meta
import kotlinx.html.stream.createHTML
import kotlinx.html.title
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Setup

class KotlinxHtml : BaseBenchmark() {
    @Setup
    fun setup() {
    }

    @Benchmark
    fun benchmark(): String {
        return createHTML().html {
            head {
                title("StockPrices")
                meta(

                    name = "Content-Type",
                    content = "text/html;charset=UTF-8"
                ) {  }
            }
        }
    }
}
