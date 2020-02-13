package example

import grails.gorm.services.Service

@Service(Book)
interface BookService {

    Book saveBook(String title, String author, Date releaseDate, Integer pages)

    List<Book> list(Map args)
}
