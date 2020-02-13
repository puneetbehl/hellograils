package example

import grails.validation.Validateable

class Person implements Validateable {

    String name

    static constraints = {
        name nullable: false, blank: false
    }
}
