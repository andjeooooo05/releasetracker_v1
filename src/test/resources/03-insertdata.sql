/*
-- Query: SELECT * FROM hb_release_tracker.validstatuses
LIMIT 0, 1000

-- Date: 2022-05-27 15:58
*/
INSERT INTO `valid_statuses` (`id`,`name`) VALUES (1,'Created');
INSERT INTO `valid_statuses` (`id`,`name`) VALUES (2,'In Development');
INSERT INTO `valid_statuses` (`id`,`name`) VALUES (3,'On DEV');
INSERT INTO `valid_statuses` (`id`,`name`) VALUES (4,'QA Done on DEV');
INSERT INTO `valid_statuses` (`id`,`name`) VALUES (5,'On staging');
INSERT INTO `valid_statuses` (`id`,`name`) VALUES (6,'QA done on STAGING');
INSERT INTO `valid_statuses` (`id`,`name`) VALUES (7,'On PROD');
INSERT INTO `valid_statuses` (`id`,`name`) VALUES (8,'Done');


INSERT INTO `neon_assignment`.`release_tracker` 
(`name`, `description`, `status`, `release_date`, `created_at`, `last_update_at`)
 VALUES ('neon_test1', 'opis za test 1', '1', now(), now(), now());
 INSERT INTO `neon_assignment`.`release_tracker` 
(`name`, `description`, `status`, `release_date`, `created_at`, `last_update_at`)
 VALUES ('neon_test2', 'opis za test 2', '1', now(), now(), now());
