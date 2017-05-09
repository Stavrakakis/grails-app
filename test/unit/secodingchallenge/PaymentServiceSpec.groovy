package secodingchallenge

import grails.test.mixin.TestFor
import spock.lang.Specification
import com.icegreen.greenmail.util.*


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PaymentService)
@Mock([Account, Transaction])
class PaymentServiceSpec extends Specification  {

    def emailServiceMock

    def creditAccount = new Account(name: "test", emailAddress: "chicken@pie.com", balance: 100)
    def debitAccount = new Account(name: "test", emailAddress: "beef@sausages.com", balance: 100)

    def setup() {

        creditAccount.save(flush: true) // id 1
        debitAccount.save(flush: true) // id 2 (not sure if there's a better way to set this)

        emailServiceMock = mockFor(EmailService)

        service.emailService = emailServiceMock.createMock()
    }

    def cleanup() {
    }

	void "test that balances are updated"() {
        given:

        emailServiceMock.demand.sendEmail() { String to, String subject, String body -> return }
        emailServiceMock.demand.sendEmail() { String to, String subject, String body -> return }
        
        when:
        
        service.makePayment(1, 2, 10)

        then:
        
        debitAccount.balance == 90
        creditAccount.balance == 110
    }

    void "test that the correct transaction is created"() {
        given:

        emailServiceMock.demand.sendEmail() { String to, String subject, String body -> return }
        emailServiceMock.demand.sendEmail() { String to, String subject, String body -> return }
        
        when:
        
        service.makePayment(1, 2, 10)

        then:
        
        def transaction = Transaction.get(1)
        
        transaction.creditAccount == creditAccount
        transaction.debitAccount == debitAccount
        transaction.value == 10
    }

    void "test that insufficient debit balance throws and doesn't update"() {
        given:

        emailServiceMock.demand.sendEmail() { String to, String subject, String body -> return }
        emailServiceMock.demand.sendEmail() { String to, String subject, String body -> return }
        
        when:
        
        service.makePayment(1, 2, 10000)

        then:
        
        thrown PaymentException

        Transaction.count() == 0

        debitAccount.balance == 100
        creditAccount.balance == 100

        // I should be testing the contents of the emails as well but I'm still figuring out the mocking 
        // framework and this would be on my list of tests todo.
    }
}
