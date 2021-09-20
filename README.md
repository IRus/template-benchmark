template-benchmark
================

```
Benchmark                        Mode  Cnt       Score   Error  Units
Baseline.benchmark              thrpt       269920.492          ops/s
Freemarker.benchmark            thrpt        35144.785          ops/s
Jte.benchmark                   thrpt       207783.558          ops/s
KotlinxHtml.benchmark           thrpt        47515.473          ops/s
KotlinxHtmlRealistic.benchmark  thrpt        54598.292          ops/s
Mustache.benchmark              thrpt        45663.615          ops/s
Pebble.benchmark                thrpt        63874.127          ops/s
Rocker.benchmark                thrpt       127891.133          ops/s
Thymeleaf.benchmark             thrpt        12072.653          ops/s
Trimou.benchmark                thrpt        55144.275          ops/s
Velocity.benchmark              thrpt        40399.933          ops/s
```

JMH benchmark for popular Java template engines:

* [Freemarker](http://freemarker.org/)
* [Mustache](https://github.com/spullara/mustache.java)
* [Pebble](http://www.mitchellbosecke.com/pebble)
* [Rocker](https://github.com/fizzed/rocker)
* [Thymeleaf](http://www.thymeleaf.org/)
* [Trimou](http://trimou.org/)
* [Velocity](http://velocity.apache.org/)

Running the benchmark
======================

1. Download the source code and build it (`mvn clean install`)
2. Run the entire benchmark suite with `java -jar target/benchmarks.jar`
3. (Optional) To run a single benchmark, such as Mustache, use `java -jar target/benchmarks.jar Mustache`

Generating plot
===============
1. Run benchmark while exporting results to csv with `java -jar target/benchmarks.jar -rff results.csv -rf csv`
2. Use gnuplot to generate plot with `gnuplot benchmark.plot`. This will output `results.png`.

Rules of Template Engine Configuration
======================================
It is imperative that each template engine is configured in way to reflect real-world usage as opposed to it's *optimal* configuration. Typically this means an out-of-the-box configuration.

To strive for a common set of features across template engines, the following configurations are expected:
* Disabling of HTML escaping
* Template loaded from classpath prior to actual benchmark

Interpreting the Results
========================
The benchmarks measure throughput, given in "ops/time". The time unit used is seconds.
Generally, the score represents the number of templates rendered per second; the higher the score, the better.

Example Results
===============

![Template Comparison](results.png)
