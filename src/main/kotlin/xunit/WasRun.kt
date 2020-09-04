package xunit

class WasRun(name: String): TestCase(name)
{
    var wasRun = false

    fun testMethod() {
        wasRun = true
    }
}