package secodingchallenge

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PaymentController)
class PaymentControllerSpec extends Specification {

	def accountServiceMock = mockFor(AccountService)
	def paymentServiceMock = mockFor(PaymentService)

    def accounts = [ new Account(name:"nico") ]

    def setup() {
		
		accountServiceMock.demand.getAccounts() { -> return accounts }
		
		controller.accountService = accountServiceMock.createMock()
		controller.paymentService = paymentServiceMock.createMock()
    }

    def cleanup() {
    }

    void "index action renders with list of accounts"() {
    	
    	when:

        controller.index()

        then:

        view == '/payment/index'
        model.accountSummaries == accounts
    }

    void "payment action handles invalid transfer by redirecting back to index view."() {
    	given:

			def invalidTransfer = new TransferCommand(debitAccountId: 1, creditAccountId: 1, value: 10d)
    	when:

        controller.transfer(invalidTransfer)

        then:

        view == '/payment/index'
        model.accountSummaries == accounts
    }

    void "payment action sends valid transfer to service and redirects on success."() {
    	given:

			def transfer = new TransferCommand(debitAccountId: 1, creditAccountId: 2, value: 10d)
			
			paymentServiceMock.demand.makePayment(1,2,10d) { int d, int c, double val -> return }

    	when:

        controller.transfer(transfer)

        then:

        response.redirectedUrl == '/payment/index'
        flash.success == true
    }

    void "payment exception sets flash message and re-renders index."() {
    	given:

			def transfer = new TransferCommand(debitAccountId: 1, creditAccountId: 2, value: 10d)
			def exceptionMessage = "oh no!"

			paymentServiceMock.demand.makePayment(1,2,10d) { int d, int c, double val -> throw new PaymentException(exceptionMessage) }

    	when:

        controller.transfer(transfer)

        then:

        response.redirectedUrl == '/payment/index'
        flash.success == false
        flash.error == exceptionMessage
    }
}
