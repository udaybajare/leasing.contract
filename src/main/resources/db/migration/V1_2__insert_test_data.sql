INSERT INTO customer values ('USR1R75SN809HGB', '1980-03-04T00:00:00.000+00:00', 'Jane', 'Doe');
INSERT INTO customer values ('USR1R75SN9JHN65', '1999-04-09T00:00:00.000+00:00', 'Adam', 'Groth');
INSERT INTO customer values ('USR1R75SNTHGF4F', '1995-08-08T00:00:00.000+00:00', 'Peter', 'Parker');
INSERT INTO customer values ('USR1R75SN2MJNBV', '2000-07-13T00:00:00.000+00:00', 'Mary', 'Jane');


INSERT INTO vehicle values ('VHL525JALZLKM63', 'BMW', 'i8', 2016, 45000, '', null);
INSERT INTO vehicle values ('VHL525JALKMJ214', 'VW', 'Golf', 2020, 25000, '', null);
INSERT INTO vehicle values ('VHL525JALZ65LKM', 'BMW', 'm3', 2021, 25000, '', null);
INSERT INTO vehicle values ('VHL525JALZKJN65', 'BMW', 'X1', 2019, 35000, '', null);
INSERT INTO vehicle values ('VHL525JALGVFD25', 'VW', 'T-ROC', 2022, 35000, '', null);


INSERT INTO contract values ('LCTIV3X70AKJ87J', 350, 'USR1R75SNTHGF4F', 'VHL525JALZKJN65');
INSERT INTO contract values ('LCTIV3X70LKM876', 200, 'USR1R75SN809HGB', 'VHL525JALKMJ214');
INSERT INTO contract values ('LCTIV3X7063MKJH', 450, 'USR1R75SN2MJNBV', 'VHL525JALZLKM63');

UPDATE vehicle SET contract_number='LCTIV3X70AKJ87J', vin='XD98KKJ' where vehicle_id='VHL525JALZ65LKM';
UPDATE vehicle SET contract_number='LCTIV3X70LKM876' where vehicle_id='VHL525JALZKJN65';
UPDATE vehicle SET contract_number='LCTIV3X7063MKJH', vin='XD98KKJ' where vehicle_id='VHL525JALGVFD25';