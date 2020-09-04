package xunit

class TestResult
{
    var runCount = 0
    var errorCount = 0

    fun testStarted() {
        runCount++
    }

    fun testFailed() {
        errorCount++
    }

    fun summary() = "$runCount run, $errorCount failed"
}