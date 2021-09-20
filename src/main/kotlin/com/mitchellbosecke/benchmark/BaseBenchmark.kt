package com.mitchellbosecke.benchmark

import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Warmup
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.OutputTimeUnit
import java.util.concurrent.TimeUnit
import java.util.HashMap
import com.mitchellbosecke.benchmark.model.Stock
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@Fork(5)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
open class BaseBenchmark {
    protected val context: Map<String, Any>
        get() {
            val context: MutableMap<String, Any> = HashMap()
            context["items"] = Stock.dummyItems()
            return context
        }
}
