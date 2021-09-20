package com.mitchellbosecke.benchmark

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Setup

class Baseline : BaseBenchmark() {
    lateinit var result: String

    @Setup
    fun setup() {
        result = Baseline::class.java.classLoader
            .getResource("expected-output.html")!!
            .readText()
    }

    @Benchmark
    fun benchmark(): String {
        return result
    }
}
