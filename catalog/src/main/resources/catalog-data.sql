--- BRAND
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('A', 'A', 'A 브랜드', NOW(), NOW());
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('B', 'B', 'A 브랜드', NOW(), NOW());
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('C', 'C', 'A 브랜드', NOW(), NOW());
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('D', 'D', 'D 브랜드', NOW(), NOW());
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('E', 'E', 'E 브랜드', NOW(), NOW());
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('F', 'F', 'F 브랜드', NOW(), NOW());
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('G', 'G', 'G 브랜드', NOW(), NOW());
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('H', 'H', 'H 브랜드', NOW(), NOW());
INSERT INTO `brand` (`id`, `code`, `name`, `register_at`, `last_modified_at`) VALUES ('I', 'I', 'I 브랜드', NOW(), NOW());



--- PRODUCT
-- A
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'TOP', 'A 브랜드 상의 1', 11200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'TOP', 'A 브랜드 상의 2', 11200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'OUTER', 'A 브랜드 아우터 1', 5500, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'OUTER', 'A 브랜드 아우터 2', 5500, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'PANTS', 'A 브랜드 바지 1', 4200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'PANTS', 'A 브랜드 바지 2', 4200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'SNEAKERS', 'A 브랜드 스니커즈 1', 9000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'SNEAKERS', 'A 브랜드 스니커즈 2', 9000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'BAG', 'A 브랜드 가방 1', 2000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'BAG', 'A 브랜드 가방 2', 2000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'HAT', 'A 브랜드 모자 1', 1700, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'HAT', 'A 브랜드 모자 2', 1700, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'SOCKS', 'A 브랜드 양말 1', 1800, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'SOCKS', 'A 브랜드 양말 2', 1800, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'ACC', 'A 브랜드 액세서리 1', 2300, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'A', 'ACC', 'A 브랜드 액세서리 2', 2300, NOW(), NOW());

-- B
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'TOP', 'B 브랜드 상의 1', 10500, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'TOP', 'B 브랜드 상의 2', 10500, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'OUTER', 'B 브랜드 아우터 1', 5900, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'OUTER', 'B 브랜드 아우터 2', 5900, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'PANTS', 'B 브랜드 바지 1', 3800, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'PANTS', 'B 브랜드 바지 2', 3800, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'SNEAKERS', 'B 브랜드 스니커즈 1', 9100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'SNEAKERS', 'B 브랜드 스니커즈 2', 9100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'BAG', 'B 브랜드 가방 1', 2100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'BAG', 'B 브랜드 가방 2', 2100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'HAT', 'B 브랜드 모자 1', 2000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'HAT', 'B 브랜드 모자 2', 2000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'SOCKS', 'B 브랜드 양말 1', 2000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'SOCKS', 'B 브랜드 양말 2', 2000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'ACC', 'B 브랜드 액세서리 1', 2200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'B', 'ACC', 'B 브랜드 액세서리 2', 2200, NOW(), NOW());

-- C
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'TOP', 'C 브랜드 상의 1', 10000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'TOP', 'C 브랜드 상의 2', 10000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'OUTER', 'C 브랜드 아우터 1', 6200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'OUTER', 'C 브랜드 아우터 2', 6200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'PANTS', 'C 브랜드 바지 1', 3300, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'PANTS', 'C 브랜드 바지 2', 3300, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'SNEAKERS', 'C 브랜드 스니커즈 1', 9200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'SNEAKERS', 'C 브랜드 스니커즈 2', 9200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'BAG', 'C 브랜드 가방 1', 2200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'BAG', 'C 브랜드 가방 2', 2200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'HAT', 'C 브랜드 모자 1', 1900, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'HAT', 'C 브랜드 모자 2', 1900, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'SOCKS', 'C 브랜드 양말 1', 2200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'SOCKS', 'C 브랜드 양말 2', 2200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'ACC', 'C 브랜드 액세서리 1', 2100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'C', 'ACC', 'C 브랜드 액세서리 2', 2100, NOW(), NOW());

-- D
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'TOP', 'D 브랜드 상의 1', 10100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'TOP', 'D 브랜드 상의 2', 10100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'OUTER', 'D 브랜드 아우터 1', 5100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'OUTER', 'D 브랜드 아우터 2', 5100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'PANTS', 'D 브랜드 바지 1', 3000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'PANTS', 'D 브랜드 바지 2', 3000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'SNEAKERS', 'D 브랜드 스니커즈 1', 9500, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'SNEAKERS', 'D 브랜드 스니커즈 2', 9500, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'BAG', 'D 브랜드 가방 1', 2500, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'BAG', 'D 브랜드 가방 2', 2500, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'HAT', 'D 브랜드 모자 1', 1500, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'HAT', 'D 브랜드 모자 2', 1500, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'SOCKS', 'D 브랜드 양말 1', 2400, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'SOCKS', 'D 브랜드 양말 2', 2400, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'ACC', 'D 브랜드 액세서리 1', 2000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'D', 'ACC', 'D 브랜드 액세서리 2', 2000, NOW(), NOW());

-- E
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'TOP', 'E 브랜드 상의 1', 10700, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'TOP', 'E 브랜드 상의 2', 10700, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'OUTER', 'E 브랜드 아우터 1', 5000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'OUTER', 'E 브랜드 아우터 2', 5000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'PANTS', 'E 브랜드 바지 1', 3800, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'PANTS', 'E 브랜드 바지 2', 3800, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'SNEAKERS', 'E 브랜드 스니커즈 1', 9900, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'SNEAKERS', 'E 브랜드 스니커즈 2', 9900, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'BAG', 'E 브랜드 가방 1', 2300, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'BAG', 'E 브랜드 가방 2', 2300, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'HAT', 'E 브랜드 모자 1', 1800, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'HAT', 'E 브랜드 모자 2', 1800, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'SOCKS', 'E 브랜드 양말 1', 2100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'SOCKS', 'E 브랜드 양말 2', 2100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'ACC', 'E 브랜드 액세서리 1', 2100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'E', 'ACC', 'E 브랜드 액세서리 2', 2100, NOW(), NOW());

-- F
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'TOP', 'F 브랜드 상의 1', 11200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'TOP', 'F 브랜드 상의 2', 11200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'OUTER', 'F 브랜드 아우터 1', 7200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'OUTER', 'F 브랜드 아우터 2', 7200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'PANTS', 'F 브랜드 바지 1', 4000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'PANTS', 'F 브랜드 바지 2', 4000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'SNEAKERS', 'F 브랜드 스니커즈 1', 9300, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'SNEAKERS', 'F 브랜드 스니커즈 2', 9300, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'BAG', 'F 브랜드 가방 1', 2100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'BAG', 'F 브랜드 가방 2', 2100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'HAT', 'F 브랜드 모자 1', 1600, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'HAT', 'F 브랜드 모자 2', 1600, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'SOCKS', 'F 브랜드 양말 1', 2300, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'SOCKS', 'F 브랜드 양말 2', 2300, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'ACC', 'F 브랜드 액세서리 1', 1900, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'F', 'ACC', 'F 브랜드 액세서리 2', 1900, NOW(), NOW());


-- G
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'TOP', 'G 브랜드 상의 1', 10500, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'TOP', 'G 브랜드 상의 2', 10500, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'OUTER', 'G 브랜드 아우터 1', 5800, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'OUTER', 'G 브랜드 아우터 2', 5800, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'PANTS', 'G 브랜드 바지 1', 3900, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'PANTS', 'G 브랜드 바지 2', 3900, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'SNEAKERS', 'G 브랜드 스니커즈 1', 9000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'SNEAKERS', 'G 브랜드 스니커즈 2', 9000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'BAG', 'G 브랜드 가방 1', 2200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'BAG', 'G 브랜드 가방 2', 2200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'HAT', 'G 브랜드 모자 1', 1700, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'HAT', 'G 브랜드 모자 2', 1700, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'SOCKS', 'G 브랜드 양말 1', 2100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'SOCKS', 'G 브랜드 양말 2', 2100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'ACC', 'G 브랜드 액세서리 1', 2000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'G', 'ACC', 'G 브랜드 액세서리 2', 2000, NOW(), NOW());


-- H
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'TOP', 'H 브랜드 상의 1', 10800, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'TOP', 'H 브랜드 상의 2', 10800, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'OUTER', 'H 브랜드 아우터 1', 6300, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'OUTER', 'H 브랜드 아우터 2', 6300, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'PANTS', 'H 브랜드 바지 1', 3100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'PANTS', 'H 브랜드 바지 2', 3100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'SNEAKERS', 'H 브랜드 스니커즈 1', 9700, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'SNEAKERS', 'H 브랜드 스니커즈 2', 9700, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'BAG', 'H 브랜드 가방 1', 2100, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'BAG', 'H 브랜드 가방 2', 2100, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'HAT', 'H 브랜드 모자 1', 1600, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'HAT', 'H 브랜드 모자 2', 1600, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'SOCKS', 'H 브랜드 양말 1', 2000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'SOCKS', 'H 브랜드 양말 2', 2000, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'ACC', 'H 브랜드 액세서리 1', 2000, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'H', 'ACC', 'H 브랜드 액세서리 2', 2000, NOW(), NOW());


-- I
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'TOP', 'I 브랜드 상의 1', 11400, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'TOP', 'I 브랜드 상의 2', 11400, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'OUTER', 'I 브랜드 아우터 1', 6700, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'OUTER', 'I 브랜드 아우터 2', 6700, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'PANTS', 'I 브랜드 바지 1', 3200, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'PANTS', 'I 브랜드 바지 2', 3200, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'SNEAKERS', 'I 브랜드 스니커즈 1', 9500, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'SNEAKERS', 'I 브랜드 스니커즈 2', 9500, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'BAG', 'I 브랜드 가방 1', 2400, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'BAG', 'I 브랜드 가방 2', 2400, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'HAT', 'I 브랜드 모자 1', 1700, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'HAT', 'I 브랜드 모자 2', 1700, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'SOCKS', 'I 브랜드 양말 1', 1700, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'SOCKS', 'I 브랜드 양말 2', 1700, NOW(), NOW());
INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'ACC', 'I 브랜드 액세서리 1', 2400, NOW(), NOW());
-- INSERT INTO `product` (`id`, `brand_code`, `category`, `name`, `price`, `register_at`, `last_modified_at`) VALUES (UUID(), 'I', 'ACC', 'I 브랜드 액세서리 2', 2400, NOW(), NOW());