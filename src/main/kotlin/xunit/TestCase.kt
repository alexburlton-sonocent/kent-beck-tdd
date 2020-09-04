package xunit

open class TestCase(val name: String) {
    fun run(): TestResult {
        val result = TestResult()
        result.testStarted()

        setUp()
        javaClass.getMethod(name).invoke(this)
        tearDown()

        return result
    }

    open fun setUp() {}
    open fun tearDown() {}
}