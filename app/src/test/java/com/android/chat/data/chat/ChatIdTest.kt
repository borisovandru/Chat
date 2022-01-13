package com.android.chat.data.chat

import org.junit.Assert.*
import org.junit.Test


class ChatIdTest {

    @Test
    fun test() {
        val chatId = ChatId("aVsfwersa", "Bwersdf")
        val actual = chatId.value()
        val expected = "aVsfwersa_Bwersdf"
        assertEquals(expected, actual)
    }

    @Test
    fun test_2() {
        val chatId = ChatId("AVsfwersa", "aBwersdf")
        val actual = chatId.value()
        val expected = "aBwersdf_AVsfwersa"
        assertEquals(expected, actual)
    }
}