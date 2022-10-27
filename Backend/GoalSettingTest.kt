import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GoalSettingTest {

    @Test
    fun setGoal() {
        val newGoal = Goal()
        assertEquals("Save 300 USD", newGoal.setGoal("Save 300 USD"))

    }

    @Test
    fun deleteGoal() {
        val newGoal = Goal()
        assertTrue( newGoal.deleteGoal())
    }

}