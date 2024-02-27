create table customer (
      id bigint not null auto_increment primary key,
      customer_name varchar(50),
      address varchar(30),
      city varchar(30),
      state varchar(30),
      zip_code varchar(30),
      phone varchar(20),
      email varchar(255),
      created_date timestamp,
      last_modified_date timestamp
);

alter table order_header add column (
      customer_id bigint not null,
      constraint customer_id_fk FOREIGN KEY (customer_id) references customer(id)
);

alter table order_header drop column customer;

insert into customer (customer_name, address, city, state, zip_code, phone, email)
values ("Test Customer", "5 Avenue, 1", "New-York", "NY", "123456", "+1233456789", "test321@test.com");

update order_header set customer_id = (
select id from customer where customer_name = "Test Customer")
