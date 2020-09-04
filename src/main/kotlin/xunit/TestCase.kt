package xunit

open class TestCase(val name: String) {
    fun run(): TestResult {
        val result = TestResult()
        result.testStarted()

        setUp()

        try {
            javaClass.getMethod(name).invoke(this)
        } catch (e: Exception) {
            result.testFailed()

            val cause = e.cause
            if (cause is AssertionError) {
                throw cause
            }
        }

        tearDown()

        return result
    }

    open fun setUp() {}
    open fun tearDown() {}
}