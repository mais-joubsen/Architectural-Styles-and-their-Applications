
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(18) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
