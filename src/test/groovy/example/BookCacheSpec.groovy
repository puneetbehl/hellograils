package example


import grails.test.hibernate.HibernateSpec
import org.hibernate.dialect.H2Dialect
import org.hibernate.stat.Statistics

class BookCacheSpec extends HibernateSpec {

    @Override
    Map getConfiguration() {
        return super.getConfiguration() + [
                'dataSource.url'         : "jdbc:h2:mem:grailsDB;MVCC=TRUE;LOCK_TIMEOUT=10000",
                'dataSource.dbCreate'    : 'update',
                'dataSource.dialect'     : H2Dialect.name,
                'dataSource.formatSql'   : 'true',
                'dataSource.logSql'      : 'true',
                'hibernate.flush.mode'   : 'COMMIT',
                'hibernate.cache.queries': 'true',
                'hibernate.cache'        : ['use_second_level_cache': true, 'region.factory_class': 'org.hibernate.cache.ehcache.EhCacheRegionFactory'],
                'hibernate.hbm2ddl.auto' : 'create',
        ]
    }

    @Override
    List<Class> getDomainClasses() {
        super.getDomainClasses() + [Book] as List<Class>
    }

    void "Test second level cache"() {

        when:
        sessionFactory.getStatistics().setStatisticsEnabled(true)
        def id = Book.withNewTransaction {
            new Book(title: "The Stand", author: "JD", releaseDate: new Date(), pages: 100).save()
            Book.first().id
        }

        Statistics stats = sessionFactory.statistics

        then:
        stats
    }
}
