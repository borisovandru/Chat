package com.android.chat.ui.search

import java.util.*

class Delay(private val block: (String) -> Unit) {

    private var timer: Timer? = null
    private var isRunning = false
    private var cached: String = ""
    private var time: Long = 0

    fun add(query: String) {
        cached = query
        time = System.currentTimeMillis()
        if (!isRunning)
            start()
    }

    fun clear() {
        timer?.purge()
        timer?.cancel()
        timer = null
        isRunning = false
    }

    private fun start() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (System.currentTimeMillis() - time >= DELAY) {
                    block(cached)
                    clear()
                }
            }
        }, 0, DELAY)
        isRunning = true
    }

    private companion object {
        const val DELAY = 300L
    }
}