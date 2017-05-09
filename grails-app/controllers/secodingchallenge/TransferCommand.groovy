package secodingchallenge

@grails.validation.Validateable(nullable=false)
class TransferCommand {
    double value
    int creditAccountId
    int debitAccountId

    static constraints = {
        value(min: 0d, scale: 2)
        creditAccountId(
            min: 1,
            nullable: false,
            validator: { val, obj, errors ->
                if ((obj.debitAccountId == val)) errors.rejectValue('debitAccountId', 'sameAccount')
            })
        debitAccountId(min: 1, nullable: false)
    }
}