package secodingchallenge

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Account)
class AccountSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test that balance validation works"() {
        expect: "an invalid account"

        account.validate() == false

        where: "the balance is negative"
        // I would normally use a test data generation plugin here instead of the constructor.
        // I tried one but I had some errors using it. I would normally do this to prevent
        // having to refactor tests when adding new [required] properties however.
        account << new Account(name: "test", emailAddress: "someone@sausages.com", balance: -10)
    }

    void "test that email validation works"() {
        expect: "an invalid account"

        account.validate() == false

        where: "the email address invalid"

        account << new Account(name: "test", emailAddress: "sausages.com", balance: 10)
    }

    void "test that validation is successful"() {
        expect: "a valid account"

        account.validate() == true

        where: "the account is valid"

        account << new Account(name: "test", emailAddress: "beef@sausages.com", balance: 10)
    }
}
