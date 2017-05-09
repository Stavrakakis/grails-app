package secodingchallenge

import grails.transaction.Transactional
import grails.validation.ValidationException

@Transactional
class PaymentService {

    def emailService

    // my understanding of grails is that this being inside a service makes it 
    // safely transactional?
    def makePayment(int creditAccountId, int debitAccountId, double paymentValue) {
        
        // should check and throw for null here
        def creditAccount = Account.get(creditAccountId)
        def debitAccount = Account.get(debitAccountId)


        if (debitAccount.balance - paymentValue < 0) {
            throw new PaymentException("Not enough balance in the account for this payment. £${debitAccount.balance} available.")
        }

        creditAccount.balance += paymentValue
        debitAccount.balance -= paymentValue

        def transaction = new Transaction(
            creditAccount: creditAccount,
            debitAccount: debitAccount,
            value: paymentValue)

        transaction.save(true)
        creditAccount.save(true)
        debitAccount.save(true)

        emailService.sendEmail(
            creditAccount.emailAddress,
            "Payment recieved from ${debitAccount.name}",
            "You just received a payment of ${paymentValue} from ${debitAccount.name}.")

        emailService.sendEmail(
            debitAccount.emailAddress,
            "Payment sent to ${creditAccount.name}",
            "Your payment of ${paymentValue} has been sent to ${creditAccount.name}.")
    }
}