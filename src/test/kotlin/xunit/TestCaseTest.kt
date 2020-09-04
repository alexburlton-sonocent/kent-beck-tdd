package xunit

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.shouldBe
import org.junit.Test

class TestCaseTest(name: String): TestCase(name) {
    override fun setUp() {
    }

    fun testTemplateMethod() {
        val test = WasRun("testMethod")
        test.run(TestResult())

        test.log shouldBe "setUp testMethod tearDown "
    }

    fun testResult() {
        val test = WasRun("testMethod")
        val result = TestResult()
        test.run(result)

        result.summary() shouldBe "1 run, 0 failed"
    }

    fun testBrokenMethod() {
        val test = WasRun("testBrokenMethod")
        val result = TestResult()
        test.run(result)

        result.summary() shouldBe "1 run, 1 failed"
    }

    fun testFailedResultFormatting() {
        val result = TestResult()
        result.testStarted()
        result.testFailed()

        result.summary() shouldBe "1 run, 1 failed"
    }

    fun testSuite() {
        val suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        val result = TestResult()
        suite.run(result)
        result.summary() shouldBe "2 run, 1 failed"
    }
}

/**
 * Actually use JUnit to drive the above methods, like how he's running them from main() in python
 */
class Tests {
    @Test
    fun testTemplateMethod() {
        val result = TestResult()
        val suite = TestSuite()
        suite.add(TestCaseTest("testTemplateMethod"))
        suite.add(TestCaseTest("testResult"))
        suite.add(TestCaseTest("testFailedResultFormatting"))
        suite.add(TestCaseTest("testBrokenMethod"))
        suite.add(TestCaseTest("testSuite"))

        suite.run(result)

        println(result.summary())

        result.summary() shouldContain "0 failed"
    }
}