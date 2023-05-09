package com.kostyanetskiy.deliveryservicekotlin.service

import jakarta.mail.Flags
import jakarta.mail.Folder
import jakarta.mail.Message
import jakarta.mail.Session
import jakarta.mail.internet.InternetAddress
import jakarta.mail.search.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@Service
class EmailSenderService(
    val javaMailSender: JavaMailSender,
    @Value("\${spring.mail.username}") val sender: String,
    @Value("\${mail.imap.host}") val host: String,
    @Value("\${mail.imap.port}") val port: String,
    @Value("\${mail.imap.ssl}") val ssl: Boolean,
    @Value("\${mail.imap.username}") val username: String,
    @Value("\${mail.imap.password}") val password: String,
) {

    fun sendMail(toEmail: String, subject: String, body: String) {
        val message = SimpleMailMessage()
        message.from = sender
        message.setTo(toEmail)
        message.subject = subject
        message.text = body

        javaMailSender.send(message)

        println("Mail sent successfully...")
    }

    fun checkMail(): Array<out Message>? {
        val terms = mutableListOf<SearchTerm>()

        val properties = Properties()
        properties["mail.pop3.host"] = host
        properties["mail.pop3.port"] = port
        properties["mail.pop3.starttls.enabled"] = ssl
        val session = Session.getDefaultInstance(properties)

        val store = session.getStore("pop3s")
        store.connect(host, username, password)

        val folder = store.getFolder("INBOX")
        folder.open(Folder.READ_WRITE)

        val flags = Flags(Flags.Flag.SEEN)
        val flagTerm = FlagTerm(flags, false)
        terms.add(flagTerm)

        val term = getDatesTerm(Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()))
        terms.add(term)


        val resultTerms = AndTerm(terms.toTypedArray())

        val messages = folder.search(resultTerms)

        for (message in messages.reversed()) {
            println("Message number: ${message.messageNumber}")
            println("Message from: ${message.from}")
            println("Message folder: ${message.folder}")
            println("Message subject: ${message.subject}")
            println("Message session: ${message.session}")
            println("Message receivedDate: ${message.receivedDate}")
        }

        return messages
    }

    private fun getDatesTerm(recievingDate: Date): SearchTerm {
        val calStart = Calendar.getInstance()
        calStart.time = recievingDate
        calStart[Calendar.HOUR] = 0
        calStart[Calendar.MINUTE] = 0
        calStart[Calendar.SECOND] = 0
        calStart[Calendar.MILLISECOND] = 0
        val calEnd = Calendar.getInstance()
        calStart.time = recievingDate
        calEnd[Calendar.HOUR] = 23
        calEnd[Calendar.MINUTE] = 59
        calEnd[Calendar.SECOND] = 59
        calEnd[Calendar.MILLISECOND] = 999
        val dateStartTerm = ReceivedDateTerm(ComparisonTerm.GE, calStart.time) //greater than
        val dateEndTerm = ReceivedDateTerm(ComparisonTerm.LE, calEnd.time) //less than
        val allTerms: Array<ReceivedDateTerm?> = arrayOfNulls(2)
        allTerms[0] = dateStartTerm
        allTerms[1] = dateEndTerm
        return AndTerm(allTerms)
    }
}
