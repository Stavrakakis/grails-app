package secodingchallenge

class AccountController {

    def accountService

    def index() {
        
        def accounts = accountService.getAccounts()

        render(view: "index", model: [ accountSummaries: accounts ])
    }

    def transactions() {
        
        def accountId = params.int('id')
        
        def account = accountService.getAccount(accountId)
        def accountTransactions = accountService.getTransactionsForAccount(accountId)

        render(view: "transactions", model: [ account: account, transactions: accountTransactions ])
    }
}
 