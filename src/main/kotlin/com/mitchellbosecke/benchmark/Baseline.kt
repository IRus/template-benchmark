package com.mitchellbosecke.benchmark

import com.mitchellbosecke.benchmark.model.Stock
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Setup

open class Baseline : BaseBenchmark() {

    @Setup
    open fun setup() {
    }

    @Benchmark
    open fun benchmark(): String {
        return render(Stock.dummyItems())
    }

    @Suppress("NOTHING_TO_INLINE")
    inline fun render(items: List<Stock>): String {
        return buildString {
            appendLine(""" <!DOCTYPE html>""")
            appendLine("""<html>""")
            appendLine("""<head>""")
            appendLine("""<title>Stock Prices</title>""")
            appendLine("""<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />""")
            appendLine("""<meta http-equiv="Content-Style-Type" content="text/css" />""")
            appendLine("""<meta http-equiv="Content-Script-Type" content="text/javascript" />""")
            appendLine("""<link rel="shortcut icon" href="/images/favicon.ico" />""")
            appendLine("""<link rel="stylesheet" type="text/css" href="/css/style.css" media="all" />""")
            appendLine("""<script type="text/javascript" src="/js/util.js"></script>""")
            appendLine("""<style type="text/css">""")
            appendLine("""/*<![CDATA[*/""")
            appendLine("""body {""")
            appendLine("""	color: #333333;""")
            appendLine("""	line-height: 150%;""")
            appendLine("""}""")
            appendLine("""""")
            appendLine("""thead {""")
            appendLine("""	font-weight: bold;""")
            appendLine("""	background-color: #CCCCCC;""")
            appendLine("""}""")
            appendLine("""""")
            appendLine(""".odd {""")
            appendLine("""	background-color: #FFCCCC;""")
            appendLine("""}""")
            appendLine("""""")
            appendLine(""".even {""")
            appendLine("""	background-color: #CCCCFF;""")
            appendLine("""}""")
            appendLine("""""")
            appendLine(""".minus {""")
            appendLine("""	color: #FF0000;""")
            appendLine("""}""")
            appendLine("""""")
            appendLine("""/*]]>*/""")
            appendLine("""</style>""")
            appendLine("""""")
            appendLine("""</head>""")
            appendLine("""""")
            appendLine("""<body>""")
            appendLine("""""")
            appendLine("""	<h1>Stock Prices</h1>""")
            appendLine("""""")
            appendLine("""	<table>""")
            appendLine("""		<thead>""")
            appendLine("""			<tr>""")
            appendLine("""				<th>#</th>""")
            appendLine("""				<th>symbol</th>""")
            appendLine("""				<th>name</th>""")
            appendLine("""				<th>price</th>""")
            appendLine("""				<th>change</th>""")
            appendLine("""				<th>ratio</th>""")
            appendLine("""			</tr>""")
            appendLine("""		</thead>""")
            appendLine("""		<tbody>""")
            items.forEachIndexed { index, stock ->
                val classes = if (index % 2 == 0) "odd" else "even"
                appendLine("""			<tr class="$classes">""")
                appendLine("""				<td>${index + 1}</td>""")
                appendLine("""				<td><a href="/stocks/${stock.symbol}">${stock.symbol}</a></td>""")
                appendLine("""				<td><a href="${stock.url}">${stock.name}</a></td>""")
                appendLine("""				<td><strong>${stock.price}</strong></td>""")
                if (stock.change < 0.0) {
                    appendLine("""				<td class="minus">${stock.change}</td>""")
                    appendLine("""				<td class="minus">${stock.ratio}</td>""")
                } else {
                    appendLine("""				<td>${stock.change}</td>""")
                    appendLine("""				<td>${stock.ratio}</td>""")
                }
                appendLine("""			</tr>""")
            }
            appendLine("""		</tbody>""")
            appendLine("""	</table>""")
            appendLine("""""")
            appendLine("""</body>""")
            appendLine("""</html>""")
        }
    }
}
