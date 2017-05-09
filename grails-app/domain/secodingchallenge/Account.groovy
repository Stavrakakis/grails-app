package secodingchallenge

class Account {

    String name
    String emailAddress
    double balance

    Date dateCreated
    Date lastUpdated

    static constraints = {
        emailAddress(email: true)
        balance(min: 0d, scale: 2)
    }
}
