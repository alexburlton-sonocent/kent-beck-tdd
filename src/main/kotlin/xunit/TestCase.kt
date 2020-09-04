package xunit

open class TestCase(val name: String) {
    fun run() {
        setUp()
        javaClass.getMethod(name).invoke(this)
        tearDown()
    }

    open fun setUp() {}
    open fun tearDown() {}
}