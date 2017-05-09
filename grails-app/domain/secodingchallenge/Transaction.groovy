package secodingchallenge

class Transaction {

    double value
    
    Account debitAccount
    Account creditAccount
    
    Date dateCreated

    static constraints = {
        value(min: 0d, scale: 2)
    }

    static mapping = {
        sort dateCreated: "asc"
    }
}
