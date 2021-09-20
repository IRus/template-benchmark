package com.mitchellbosecke.benchmark

import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
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
        return createHTMLDocument().html {
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
            }
        }.serialize(false)
    }

    /*
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
			<#list items as item>
			<tr class="${["even", "odd"][(item_index+1) %2]}">
				<td>${item_index + 1}</td>
				<td><a href="/stocks/${item.symbol}">${item.symbol}</a></td>
				<td><a href="${item.url}">${item.name}</a></td>
				<td><strong>${item.price}</strong></td><#if (item.change < 0.0)>
				<td class="minus">${item.change}</td>
				<td class="minus">${item.ratio}</td><#else>
				<td>${item.change}</td>
				<td>${item.ratio}</td></#if>
			</tr>
			</#list>
		</tbody>
	</table>

</body>
</html>
     */
}
