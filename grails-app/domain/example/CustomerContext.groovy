package example

class CustomerContext {

    String accountId
    String contactId

    static constraints = {
        accountId nullable: true
        contactId nullable: true
    }
}
