package secodingchallenge

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AccountController)

class AccountControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test that index view is rendered with correct accounts."() {
    	given:

    	def accountServiceMock = mockFor(AccountService)
        def accounts = [ new Account(name:"nico") ]

        accountServiceMock.demand.getAccounts() { -> return accounts }
        controller.accountService = accountServiceMock.createMock()
    	
    	when:

        controller.index()

        then:

        view == '/account/index'
        model.accountSummaries == accounts
    }

    void "test that the transactions view is rendered with the correct account and transactions"() {
    	given:

    	def accountServiceMock = mockFor(AccountService)
		def account = [ new Account(id: 1, name:"nico") ]
		def transactions = [ new Transaction(id: 1), new Transaction(id: 2) ]

        accountServiceMock.demand.getAccount() { int id -> return account }
        accountServiceMock.demand.getTransactionsForAccount() { int id -> return transactions }

        controller.accountService = accountServiceMock.createMock()
    	controller.params.id = 1

    	when:

        controller.transactions()

        then:

        view == '/account/transactions'
        model.account == account
        model.transactions == transactions
    }
}
