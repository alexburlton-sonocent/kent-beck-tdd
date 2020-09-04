package xunit

class TestResult
{
    var runCount = 0

    fun testStarted() {
        runCount++
    }

    fun summary() = "$runCount run, 0 failed"
}