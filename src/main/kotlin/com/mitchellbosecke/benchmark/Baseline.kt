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
            append(part1)
            items.forEachIndexed { index, stock ->
                val classes = if (index % 2 == 0) {
                    "			<tr class=\"odd\">\n\t\t\t\t<td>"
                } else {
                    "			<tr class=\"even\">\n\t\t\t\t<td>"
                }
                append(classes)
                append(index + 1)
                append("</td>\n				<td><a href=\"/stocks/")
                append(stock.symbol)
                append("\">")
                append(stock.symbol)
                append("</a></td>\n				<td><a href=\"")
                append(stock.url)
                append("\">")
                append(stock.name)
                append("</a></td>\n				<td><strong>")
                append(stock.price)
                append("</strong></td>\n\t\t\t\t")
                if (stock.change < 0.0) {
                    append("<td class=\"minus\">")
                    append(stock.change)
                    append("</td><td class=\"minus\">")
                    append(stock.ratio)
                } else {
                    append("<td>")
                    append(stock.change)
                    append("</td><td>")
                    append(stock.ratio)
                }
                append("</td>\n			</tr>\n")
            }
            append(part2)
        }
    }
}

const val part2 = """
                
                		</tbody>
                	</table>

                </body>
                </html>
            """

const val part1 = """
                <!DOCTYPE html>
<html>
<head>
<title>Stock Prices</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<link rel="shortcut icon" href="/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="/css/style.css" media="all" />
<script type="text/javascript" src="/js/util.js"></script>
<style type="text/css">
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
</style>

</head>

<body>

	<h1>Stock Prices</h1>

	<table>
		<thead>
			<tr>
				<th>#</th>
				<th>symbol</th>
				<th>name</th>
				<th>price</th>
				<th>change</th>
				<th>ratio</th>
			</tr>
		</thead>
		<tbody>
			
            """
