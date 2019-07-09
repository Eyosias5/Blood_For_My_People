package com.teambloodformypeople

import com.teambloodformypeople.util.PasswordValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for the Util_PasswordStrengthTest logic.
 */
class Util_PasswordStrengthTest {
    @Test
    fun emailValidator_CorrectPasswordSimple_ReturnsTrue() {
        assertTrue(PasswordValidator.isValidPassword("aKvBnj10"))
    }

    @Test
    fun emailValidator_CorrectPasswordSubDomain_ReturnsTrue() {
        assertTrue(PasswordValidator.isValidPassword("aK123456789vbnj"))
    }

    @Test
    fun emailValidator_InvalidPasswordNoText_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("1234567890@"))
    }

    @Test
    fun emailValidator_InvalidPasswordShortSize_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("123e1"))
    }

    @Test
    fun emailValidator_InvalidPasswordShortSizeWithNoNumber_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("pcom"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword(""))
    }

    @Test
    fun emailValidator_NullPassword_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword(null))
    }
}