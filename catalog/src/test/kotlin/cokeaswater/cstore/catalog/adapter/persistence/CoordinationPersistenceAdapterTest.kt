package cokeaswater.cstore.catalog.adapter.persistence

import cokeaswater.cstore.catalog.adapter.persistence.mybatis.repository.CoordinationMybatisRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
@DisplayName("코디네이션 저장소 아답터")
internal class CoordinationPersistenceAdapterTest(
) {

    @Mock
    lateinit var repository: CoordinationMybatisRepository

    @InjectMocks
    lateinit var adapter: CoordinationPersistenceAdapter

    @Test
    @DisplayName("카테고리 코디네이션 마지막 기준키 조회")
    fun testFindLastCategoryCoordinationsPartitionKey() {
        val key = LocalDateTime.now()

        `when`(repository.findLastCategoryCoordinationsPartitionKey()).thenReturn(key)

        val res = adapter.findLastCategoryCoordinationsPartitionKey()

        checkNotNull(res)
        assertTrue(res.compareTo(key) == 0)

        verify(repository, times(1))
            .findLastCategoryCoordinationsPartitionKey()
    }

    @Test
    @DisplayName("브랜드 카테고리 코디네이션 마지막 기준키 조회")
    fun testFindLastBrandCategoryCoordinationsPartitionKey() {
        val brandCode = "";
        val key = LocalDateTime.now()

        `when`(repository.findLastBrandCategoryCoordinationsPartitionKey(anyString())).thenReturn(key)

        val res = adapter.findLastBrandCategoryCoordinationsPartitionKey(anyString())

        checkNotNull(res)
        assertTrue(res.compareTo(key) == 0)

        verify(repository, times(1))
            .findLastBrandCategoryCoordinationsPartitionKey(brandCode)
    }


    @Test
    @DisplayName("브랜드 카테고리 코디네이션 데이터 조회")
    fun testFindBrandCategoryCoordinations() {
        val brandCode = "";
        val now = LocalDateTime.now()

        `when`(repository.findBrandCategoryCoordinations(brandCode, now))
            .thenReturn(listOf())

        val list = adapter.findBrandCategoryCoordinations(brandCode, now)
        assertTrue(list.isEmpty())
        verify(repository, times(1))
            .findBrandCategoryCoordinations(brandCode, now)
    }


}