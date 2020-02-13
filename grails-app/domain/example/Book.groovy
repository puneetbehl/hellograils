package example

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Book {
    static mapping = {
        cache true
    }
    String title
    String author
    Date releaseDate
    Integer pages
}
