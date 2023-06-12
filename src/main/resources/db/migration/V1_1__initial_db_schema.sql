CREATE TABLE IF NOT EXISTS contract(
    contract_number varchar(15) not null,
	monthly_rate double not null,
	customer_id varchar(15),
	vehicle_id varchar(15),
	primary key (contract_number)
);

CREATE TABLE IF NOT EXISTS customer (
    customer_id varchar(15) not null,
	date_of_birth timestamp,
	first_name varchar(255),
	last_name varchar(255),
	primary key (customer_id)
);

CREATE TABLE IF NOT EXISTS vehicle (
    vehicle_id varchar(15) not null,
	brand varchar(30),
	model varchar(30),
	model_year integer not null,
	price double not null,
	vin varchar(15),
	contract_number varchar(15),
	primary key (vehicle_id)
);

alter table contract
   add constraint customer_contract_fk
   foreign key (customer_id)
   references customer(customer_id);

alter table contract
   add constraint vehicle_contract_fk
   foreign key (vehicle_id)
   references vehicle(vehicle_id) ;