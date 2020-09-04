package xunit

class TestSuite {
    private val tests = mutableListOf<TestCase>()

    fun add(case: TestCase) {
        tests.add(case)
    }

    fun run(result: TestResult) {
        tests.forEach {
            it.run(result)
        }
    }
}