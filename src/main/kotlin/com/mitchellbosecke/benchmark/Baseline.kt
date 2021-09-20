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
            append(" <!DOCTYPE html>\n")
            append("<html>\n")
            append("<head>\n")
            append("<title>Stock Prices</title>\n")
            append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n")
            append("<meta http-equiv=\"Content-Style-Type\" content=\"text/css\" />\n")
            append("<meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\" />\n")
            append("<link rel=\"shortcut icon\" href=\"/images/favicon.ico\" />\n")
            append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\" media=\"all\" />\n")
            append("<script type=\"text/javascript\" src=\"/js/util.js\"></script>\n")
            append("<style type=\"text/css\">\n")
            append("/*<![CDATA[*/\n")
            append("body {\n")
            append("	color: #333333;\n")
            append("	line-height: 150%;\n")
            append("}\n")
            append("\n")
            append("thead {\n")
            append("	font-weight: bold;\n")
            append("	background-color: #CCCCCC;\n")
            append("}\n")
            append("\n")
            append(".odd {\n")
            append("	background-color: #FFCCCC;\n")
            append("}\n")
            append("\n")
            append(".even {\n")
            append("	background-color: #CCCCFF;\n")
            append("}\n")
            append("\n")
            append(".minus {\n")
            append("	color: #FF0000;\n")
            append("}\n")
            append("\n")
            append("/*]]>*/\n")
            append("</style>\n")
            append("\n")
            append("</head>\n")
            append("\n")
            append("<body>\n")
            append("\n")
            append("	<h1>Stock Prices</h1>\n")
            append("\n")
            append("	<table>\n")
            append("		<thead>\n")
            append("			<tr>\n")
            append("				<th>#</th>\n")
            append("				<th>symbol</th>\n")
            append("				<th>name</th>\n")
            append("				<th>price</th>\n")
            append("				<th>change</th>\n")
            append("				<th>ratio</th>\n")
            append("			</tr>\n")
            append("		</thead>\n")
            append("		<tbody>\n")
            items.forEachIndexed { index, stock ->
                val classes = if (index % 2 == 0) "odd" else "even"
                append("			<tr class=\"$classes\">\n")
                append("				<td>${index + 1}</td>\n")
                append("				<td><a href=\"/stocks/${stock.symbol}\">${stock.symbol}</a></td>\n")
                append("				<td><a href=\"${stock.url}\">${stock.name}</a></td>\n")
                append("				<td><strong>${stock.price}</strong></td>\n")
                if (stock.change < 0.0) {
                    append("				<td class=\"minus\">${stock.change}</td>\n")
                    append("				<td class=\"minus\">${stock.ratio}</td>\n")
                } else {
                    append("				<td>${stock.change}</td>\n")
                    append("				<td>${stock.ratio}</td>\n")
                }
                append("			</tr>\n")
            }
            append("		</tbody>\n")
            append("	</table>\n")
            append("\n")
            append("</body>\n")
            append("</html>\n")
        }
    }
}
