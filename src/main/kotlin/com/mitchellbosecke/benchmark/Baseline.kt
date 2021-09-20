package com.mitchellbosecke.benchmark

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Setup

open class Baseline : BaseBenchmark() {
    lateinit var result: String

    @Setup
    open fun setup() {
        result = Baseline::class.java.classLoader
            .getResource("expected-output.html")!!
            .readText()
    }

    @Benchmark
    open fun benchmark(): String {
        return result
    }
}
