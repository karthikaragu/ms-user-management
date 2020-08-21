DELETE FROM `autosparescm`.`userdetail`;
DELETE FROM `autosparescm`.`userrole`;

INSERT INTO `autosparescm`.`userrole`
(`rolecode`,
`roledescription`)
VALUES
('DLR','Dealer');

INSERT INTO `autosparescm`.`userrole`
(`rolecode`,
`roledescription`)
VALUES
('CUST','Customer');

INSERT INTO `autosparescm`.`userrole`
(`rolecode`,
`roledescription`)
VALUES
('ADMIN','Administrator');

INSERT INTO `autosparescm`.`userdetail`
(`userid`,
`firstname`,
`lastname`,
`dob`,
`accounttype`,
`pwd`,
`username`)
VALUES
(1,
'Test',
'Test',
'1963-06-03',
'DLR',
'$2a$10$uJBjJcMg70eL00gKsQNwp.DMPkLV0fmoLMTKlscsOgV3i6jFSlUC6',
'Test');