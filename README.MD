# 코디 샵

## 프로젝트 개요
- 브랜드 / 브랜드 별 제품 등록 수정 삭제
- 제품 가격 기준의 통합 / 브랜드 세트 상품들의 추천 정보 제공

## API 제공 기능
- 제품 등록
- 제품 수정
- 제품 삭제
- 브랜드 등록
- 브랜드 수정
- 브랜드 삭제
- 전 브랜드 통합 카테고리 별 최저 가격 상품 조합 세트 조회 (aka 통합 세트 추천)
- 카테고리 세트 기준 최저가 단일 브랜드 및 브랜드 카테고리 상품 조합 세트 조회 (aka 브랜드 세트 추천)
- 카테고리 기준 최고 / 최저가 상품 조회 (aka 카테고리 상품 추천)


## 환경 / 빌드 및 실행
- Kotlin, Spring boot
- H2 embedded DB
- gradle
  - multi module structure
  - parent
    - common
    - catalog
    - api
```json
../cstore/gradlew clean :api:build
../cstore/java -jar ./api/build/libs/api-0.0.1-SNAPSHOT.jar
```

## 프로젝트 상세 설명
- 

## DB 스키마
![DB스키마](./readme/db_schema.png)


## API 명세


### 제품 등록

### 제품 수정

### 제품 삭제

### 브랜드 등록

### 브랜드 수정

### 브랜드 삭제