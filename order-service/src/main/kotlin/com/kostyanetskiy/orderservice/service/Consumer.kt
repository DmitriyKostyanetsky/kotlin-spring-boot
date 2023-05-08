package com.kostyanetskiy.orderservice.service

import jakarta.jms.JMSException
import jakarta.jms.Message
import jakarta.jms.MessageListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Consumer : MessageListener {
    private val log: Logger = LoggerFactory.getLogger(Consumer::class.java)

    override fun onMessage(message: Message) {
        try {
            log.info("Received message: " + message.getBody(Any::class.java))
        } catch (ex: JMSException) {
            ex.printStackTrace()
        }
    }
}
