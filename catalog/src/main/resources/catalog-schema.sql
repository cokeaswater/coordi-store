DROP TABLE IF EXISTS `coordi_category_base`;
CREATE TABLE `coordi_category_base` (
  `product_id` VARCHAR(100) NOT NULL,
  `category` VARCHAR(100) NOT NULL,
  `brand_code` VARCHAR(100) NOT NULL,
  `price` INTEGER NOT NULL,
  `register` DATETIME(6) NOT NULL,
  KEY `coordi_cb_register` (`register`,`category`)
);

DROP TABLE IF EXISTS `coordi_brand_base`;
CREATE TABLE `coordi_brand_base` (
   `product_id` VARCHAR(100) NOT NULL,
   `brand_code` VARCHAR(100) NOT NULL,
   `category` VARCHAR(100) NOT NULL,
   `price` NUMERIC NOT NULL,
   `register` DATETIME(6) NOT NULL,
   KEY `coordi_bb_register` (`brand_code`, `register`,`category`)
);

DROP TABLE IF EXISTS `coordi_brand_summary`;
CREATE TABLE `coordi_brand_summary` (
    `brand_code` VARCHAR(100) NOT NULL,
    `sum_price` INTEGER NOT NULL,
    `count_category` INTEGER NOT NULL,
    `category_avg` INTEGER NOT NULL,
    `register` DATETIME(6) NOT NULL,
    KEY `coordi_bs_register` (`register`, `sum_price`)
);