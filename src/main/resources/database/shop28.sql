CREATE TABLE `user` (
  `id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255),
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL UNIQUE,
  `phone` varchar(255) NOT NULL UNIQUE,
  `is_lock` boolean DEFAULT FALSE,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `address` (
  `id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `note` varchar(255),
  `number` varchar(255) NOT NULL,
  `ward` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `role` (
  `id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `user_has_role` (
  `id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `role_id` integer NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `product` (
  `id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `star` float NOT NULL,
  `price` integer NOT NULL,
  `stock_quantity` integer NOT NULL,
  `sold_quantity` integer NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `product_detail` (
  `id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `product_id` integer NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `color_id` integer NOT NULL,
  `size_id` integer NOT NULL,
  `star` float NOT NULL,
  `price` integer NOT NULL,
  `stock_quantity` integer NOT NULL,
  `sold_quantity` integer NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `size` (
  `id` integer PRIMARY KEY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `color` (
  `id` integer PRIMARY KEY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `transaction` (
  `id` integer PRIMARY KEY KEY NOT NULL AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `order_id` integer NOT NULL,
  `total_price` integer NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `order` (
  `id` integer PRIMARY KEY KEY NOT NULL AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `product_detail_id` integer NOT NULL,
  `quantity` integer NOT NULL,
  `total_price` integer NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `cart` (
  `id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `product_detail_id` integer NOT NULL,
  `quantity` integer NOT NULL,
  `price` integer NOT NULL,
  `created_at` datetime,
  `updated_at` datetime
);

ALTER TABLE `user_has_role` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `user_has_role` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE;

ALTER TABLE `address` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `transaction` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `order` ADD FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`) ON DELETE CASCADE;

ALTER TABLE `product_detail` ADD FOREIGN KEY (`color_id`) REFERENCES `color` (`id`) ON DELETE CASCADE;

ALTER TABLE `product_detail` ADD FOREIGN KEY (`size_id`) REFERENCES `size` (`id`) ON DELETE CASCADE;

ALTER TABLE `product_detail` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `cart` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `cart` ADD FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`) ON DELETE CASCADE;

ALTER TABLE `order` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `transaction` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE;

INSERT INTO `role` (`name`, `created_at`, `updated_at`) 
VALUES 
('USER', NOW(), NOW()),
('ADMIN', NOW(), NOW());