package secodingchallenge

import grails.transaction.Transactional

@Transactional
class AccountService {

    def getAccount(int accountId) {
        return Account.get(accountId)
    }

    def getAccounts() {
        return Account.list()
    }

    def getTransactionsForAccount(int accountId) {
        return Transaction.where {
            creditAccount.id == accountId || debitAccount.id == accountId
        }.list()
    }
}