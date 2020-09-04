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

    fun toTestSuite(): TestSuite {
        val suite = TestSuite()

        val testMethods = javaClass.methods.filter {
            it.name.startsWith("test")
        }

        val constructor = javaClass.getConstructor(String::class.java)

        testMethods.forEach {
            suite.add(constructor.newInstance(it.name))
        }

        return suite
    }
}