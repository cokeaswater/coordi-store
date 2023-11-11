package cokeaswater.cstore.api

import cokeaswater.cstore.catalog.domain.converter.ProductCategoryDeSerializer
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class ApiConfiguration : WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(ProductCategoryDeSerializer())
    }
}