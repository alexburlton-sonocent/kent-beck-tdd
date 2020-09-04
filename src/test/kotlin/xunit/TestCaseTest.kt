package xunit

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.shouldBe
import org.junit.Test

class TestCaseTest(name: String): TestCase(name) {
    lateinit var result: TestResult

    override fun setUp() {
        result = TestResult()
    }

    fun testTemplateMethod() {
        val test = WasRun("testMethod")
        test.run(result)

        test.log shouldBe "setUp testMethod tearDown "
    }

    fun testResult() {
        val test = WasRun("testMethod")
        test.run(result)

        result.summary() shouldBe "1 run, 0 failed"
    }

    fun testBrokenMethod() {
        val test = WasRun("testBrokenMethod")
        test.run(result)

        result.summary() shouldBe "1 run, 1 failed"
    }

    fun testFailedResultFormatting() {
        result.testStarted()
        result.testFailed()

        result.summary() shouldBe "1 run, 1 failed"
    }

    fun testSuite() {
        val suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        suite.run(result)
        result.summary() shouldBe "2 run, 1 failed"
    }

    fun testTearDownForFailedTests() {
        val test = WasRun("testBrokenMethod")
        test.run(result)
        test.log shouldBe "setUp testMethod tearDown "
    }
}

/**
 * Actually use JUnit to drive the above methods, like how he's running them from main() in python
 */
class Tests {
    @Test
    fun testTemplateMethod() {
        val result = TestResult()
        val suite = TestCaseTest("").toTestSuite()

        suite.run(result)

        println(result.summary())
        result.summary() shouldContain "0 failed"
    }
}