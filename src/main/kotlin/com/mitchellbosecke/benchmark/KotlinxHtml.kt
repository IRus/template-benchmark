package com.mitchellbosecke.benchmark

import com.mitchellbosecke.benchmark.model.Stock
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.stream.appendHTML
import kotlinx.html.strong
import kotlinx.html.style
import kotlinx.html.table
import kotlinx.html.tbody
import kotlinx.html.td
import kotlinx.html.th
import kotlinx.html.thead
import kotlinx.html.title
import kotlinx.html.tr
import kotlinx.html.unsafe
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Setup

open class KotlinxHtml : BaseBenchmark() {
    @Setup
    open fun setup() {
    }

    @Benchmark
    open fun benchmark(): String {
        return render(Stock.dummyItems())
    }

    @Suppress("NOTHING_TO_INLINE")
    inline fun render(items: List<Stock>): String {
        return StringBuilder(100 * 1024)
            .appendHTML(false)
            .html {
                head {
                    title("StockPrices")
                    meta {
                        httpEquiv = "Content-Type"
                        content = "text/html;charset=UTF-8"
                    }
                    meta {
                        httpEquiv = "Content-Style-Type"
                        content = "text/css"
                    }
                    meta {
                        httpEquiv = "Content-Script-Type"
                        content = "text/javascript"
                    }
                    link {
                        rel = "shortcut icon"
                        href = "/images/favicon.ico"
                    }
                    link {
                        rel = "stylesheet"
                        type = "text/css"
                        href = "/css/style.css"
                        media = "all"
                    }
                    script {
                        type = "text/javascript"
                        src = "/js/util.js"
                    }
                    style(type = "text/css") {
                        unsafe {
                            raw("""
                            /*<![CDATA[*/
                            body {
                            	color: #333333;
                            	line-height: 150%;
                            }

                            thead {
                            	font-weight: bold;
                            	background-color: #CCCCCC;
                            }

                            .odd {
                            	background-color: #FFCCCC;
                            }

                            .even {
                            	background-color: #CCCCFF;
                            }

                            .minus {
                            	color: #FF0000;
                            }

                            /*]]>*/
                        """.trimIndent())
                        }
                    }
                }
                body {
                    h1 {
                        +"Stock Prices"
                    }

                    table {
                        thead {
                            tr {
                                th { +"#" }
                                th { +"symbol" }
                                th { +"name" }
                                th { +"price" }
                                th { +"change" }
                                th { +"ratio" }
                            }
                        }
                        tbody {
                            items.forEachIndexed { index, item ->
                                tr(classes = oddOrEvenClass(index)) {
                                    td { +"${index + 1}" }
                                    td {
                                        a(href = "/stocks/${item.symbol}") {
                                            +item.symbol
                                        }
                                    }
                                    td {
                                        a(href = item.url) {
                                            +item.name
                                        }
                                    }
                                    td {
                                        strong {
                                            +"${item.price}"
                                        }
                                    }
                                    if (item.change < 0.0) {
                                        td(classes = "minus") {
                                            +"${item.change}"
                                        }
                                        td(classes = "minus") {
                                            +"${item.ratio}"
                                        }
                                    } else {
                                        td {
                                            +"${item.change}"
                                        }
                                        td {
                                            +"${item.ratio}"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            .toString()
    }

    val evenOdd = arrayOf("even", "odd")

    @Suppress("NOTHING_TO_INLINE")
    inline fun oddOrEvenClass(index: Int): String {
        return evenOdd[(index + 1) % 2]
    }
}
