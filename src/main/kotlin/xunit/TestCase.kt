package xunit

open class TestCase(private val name: String) {
    fun run(result: TestResult) {
        result.testStarted()

        setUp()

        try {
            javaClass.getMethod(name).invoke(this)
        } catch (e: Exception) {
            println(e.cause?.message)
            result.testFailed()
        }

        tearDown()
    }

    open fun setUp() {}
    open fun tearDown() {}
}