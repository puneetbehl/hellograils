package example

import grails.converters.JSON

class DemoController {

    BookService bookService

    static responseFormats = ['json']

    def index() {
        def transientDomainObject = new CustomerContext()
        def result = new SearchResult(firstItem: 'foo', secondItem: transientDomainObject, thirdItem: 'bar')
        respond result
    }

    def createBook() {
        def bookJson  = bookService.saveBook("The Stand", "JD", new Date(), 100) as JSON
        render bookJson
    }

    def list() {
        def bookList = bookService.list() as JSON
        render(bookList)
    }
}
