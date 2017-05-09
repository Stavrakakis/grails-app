package secodingchallenge

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Transaction)
class TransactionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test that value cannot be negative"() {
        expect: "an invalid transaction"

        transaction.validate() == false

        where: "the value is negative"

        transaction << new Transaction(value: -10)
    }

    void "test that a transaction is valid"() {
        expect: "a valid transaction"

        transaction.validate() == true

        where: "the transaction is valid"

        transaction << new Transaction(value: 10, debitAccount: new Account(id:1), creditAccount: new Account(id:1))
    }
}
