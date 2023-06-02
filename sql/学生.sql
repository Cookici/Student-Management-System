CREATE TABLE `t_student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `age` int NOT NULL,
  `major` varchar(20) NOT NULL,
  `math` int NOT NULL,
  `computer` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;