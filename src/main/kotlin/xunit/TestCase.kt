package xunit

open class TestCase(private val name: String) {
    fun run(result: TestResult) {
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
    }

    open fun setUp() {}
    open fun tearDown() {}
}