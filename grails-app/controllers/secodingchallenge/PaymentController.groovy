package secodingchallenge

class PaymentController {

    def paymentService
    def accountService

    def index() { 
        def accounts = accountService.getAccounts()
        render(view: "index", model: [ accountSummaries: accounts])
    }

    def transfer(TransferCommand transfer) {
        
        if (transfer.validate()) {
            paymentService.makePayment(transfer.creditAccountId, transfer.debitAccountId, transfer.value)
            flash.success = true
            redirect(action: "index")
        }
        else {
            def accounts = accountService.getAccounts()
            render(view: "index", model: [ accountSummaries: accounts, transfer: transfer ])
        }
    }

    def handlePaymentException(PaymentException e) {
        
        flash.success = false
        flash.error = e.message

        redirect(view: "index")
    }
}
