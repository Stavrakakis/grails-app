package secodingchallenge

import grails.transaction.Transactional

@Transactional
class EmailService {

    def mailService

    // for mocking purposes, I had difficulty mocking mailService directly in the unit tests.
    def sendEmail(String toAddress, String emailSubject, String body) {

        mailService.sendMail {
           to toAddress
           subject emailSubject
           text body
        }
    }
}
