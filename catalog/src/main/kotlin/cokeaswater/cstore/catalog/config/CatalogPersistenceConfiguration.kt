package cokeaswater.cstore.catalog.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan(basePackages = ["cokeaswater.cstore.catalog.adapter.persistence.jpa.entity"])
@EnableJpaRepositories(basePackages = ["cokeaswater.cstore.catalog.adapter.persistence.jpa.repository"])
@MapperScan(basePackages = ["cokeaswater.cstore.catalog.adapter.persistence.mybatis.repository"])
@EnableTransactionManagement
@PropertySource(value = ["classpath:/application-catalog.yml"])
class CatalogPersistenceConfiguration(
    @PersistenceContext
    val entityManager: EntityManager,
) {
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}