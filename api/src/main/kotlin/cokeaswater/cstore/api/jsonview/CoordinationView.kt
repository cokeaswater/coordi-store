package cokeaswater.cstore.api.jsonview

interface CoordinationView {

    interface Default

    interface CategoryInclude : Default

    interface BrandInclude : Default

    interface AllInclude : CategoryInclude, BrandInclude
}