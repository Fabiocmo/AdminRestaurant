/**Put data into tables**/
insert into INGREDIENT (name) value ('rucolla');
insert into INGREDIENT (name) value ('charry tomatos');
insert into INGREDIENT (name) value ('chiken');
insert into INGREDIENT (name) value ('sauce');
insert into INGREDIENT (name) value ('bread');
insert into INGREDIENT (name) value ('noodels');
insert into INGREDIENT (name) value ('fresh fish');
insert into INGREDIENT (name) value ('egg');
insert into INGREDIENT (name) value ('salt');
insert into INGREDIENT (name) value ('oil');
insert into INGREDIENT (name) value ('pepper');
insert into INGREDIENT (name) value ('berries');
insert into INGREDIENT (name) value ('water');
insert into INGREDIENT (name) value ('sugar');
insert into INGREDIENT (name) value ('butter');



insert into WAREHOUSE (ingred_id, amount) values(1, 90);
insert into WAREHOUSE (ingred_id, amount) values (2, 150);
insert into WAREHOUSE (ingred_id, amount) values(3, 78);
insert into WAREHOUSE (ingred_id, amount) values (5, 78);
insert into WAREHOUSE (ingred_id, amount) values (4, 50);
insert into WAREHOUSE (ingred_id, amount) values(6, 10);
insert into WAREHOUSE (ingred_id, amount) values (7, 30);
insert into WAREHOUSE (ingred_id, amount) values(8, 20);
insert into WAREHOUSE (ingred_id, amount) values (9, 3);
insert into WAREHOUSE (ingred_id, amount) values (10, 8);
insert into WAREHOUSE (ingred_id, amount) values(11, 10);
insert into WAREHOUSE (ingred_id, amount) values (12, 120);
insert into WAREHOUSE (ingred_id, amount) values(13, 100);
insert into WAREHOUSE (ingred_id, amount) values (14, 70);
insert into WAREHOUSE (ingred_id, amount) values (15, 6);

insert into DISH values(1,'Cezar salad', 'salads', 90, 300);
insert into DISH values (2, 'Green salad', 'salads', 50.45, 250);
insert into DISH values(3, 'Sauce', 'aditionals', 10.5, 30);
insert into DISH values(4, 'Grilled chiken', 'Main courses', 78, 180);
insert into DISH values(5, 'Noodels', 'Side', 40, 250);
insert into DISH values(6, 'Fish cuttlets', 'Main courses', 40, 180);
insert into DISH values(7, 'fruit-drink', 'Drinks', 20, 300);


insert into EMPLOYEE values(1,'Simpson', 'Jesika', '2015-02-01', '0967875225', 'waitress', 2000);
insert into EMPLOYEE values(2,'Kernal', 'Brain', '2012-07-08', '0936786525', 'waiter', 3500);
insert into EMPLOYEE values(3,'Parash', 'Nishant', '2010-03-27', '0982013440', 'administrator', 6000);
insert into EMPLOYEE values(4,'Sanalso', 'Nikola', '2010-01-10', '0675403664', 'chief', 8000);

insert into MENU values(1, 'vegitarian');
insert into MENU values(2, 'breakfast');
insert into MENU values(3, 'Main menu');
insert into MENU values(4, 'Children menu');

insert into ORDER_FROM_MENU (order_num, employee_id, table_num, order_date) value(1001, 1, 5, '2016-05-09 12:35:47');
insert into ORDER_FROM_MENU (order_num, employee_id, table_num, order_date) value(1002, 2, 3, '2016-05-09 15:54:01');
insert into ORDER_FROM_MENU (order_num, employee_id, table_num, order_date) value(1003, 2, 1, '2016-05-09 15:20:27');

insert into READY_MEALS (dish_numb, dish_id, employee_id, order_id, meal_date) value(1, 4, 4, 1, '2016-05-09 12:55:20');
insert into READY_MEALS (dish_numb, dish_id, employee_id, order_id) value(2, 1, 4, 1);
insert into READY_MEALS (dish_numb, dish_id, employee_id, order_id, meal_date) value(3, 3, 4, 1, '2016-05-09 12:50:56');

/*add to table DISH_INGREDIANT*/

		/**Cezar - Ingredients**/
		insert into DISH_INGTREDIENTS values(1, 1);
		insert into DISH_INGTREDIENTS values(1, 2);
		insert into DISH_INGTREDIENTS values(1, 3);
		insert into DISH_INGTREDIENTS values(1, 5);
		insert into DISH_INGTREDIENTS values(1, 4);

		/**Green salad - Ingredients**/
		insert into DISH_INGTREDIENTS values(2, 1);
		insert into DISH_INGTREDIENTS values(2, 2);
		insert into DISH_INGTREDIENTS values(2, 3);
		insert into DISH_INGTREDIENTS values(2, 4);

		/**Sause - Ingredients**/
		insert into DISH_INGTREDIENTS values(3, 3);

		/**Grilled chiken - Ingredients**/
		insert into DISH_INGTREDIENTS values(4, 5);
		insert into DISH_INGTREDIENTS values(4, 2);

		/**Noodels - Ingredients**/
		insert into DISH_INGTREDIENTS values(5, 6);
		insert into DISH_INGTREDIENTS values(5, 13);
		insert into DISH_INGTREDIENTS values(5, 8);
		insert into DISH_INGTREDIENTS values(5, 15);

		/**Fish Cuttlets - Ingredients**/
		insert into DISH_INGTREDIENTS values(6, 7);
		insert into DISH_INGTREDIENTS values(6, 8);
		insert into DISH_INGTREDIENTS values(6, 9);
		insert into DISH_INGTREDIENTS values(6, 10);
		insert into DISH_INGTREDIENTS values(6, 11);

		/**Fruit-drink - Ingredients**/
		insert into DISH_INGTREDIENTS values(7, 12);
		insert into DISH_INGTREDIENTS values(7, 13);
		insert into DISH_INGTREDIENTS values(7, 14);


/*Add to table MENU_DISH*/
		/**vegetarian menu - dishes**/
		insert into MENU_DISH values(1, 2);
		insert into MENU_DISH values(1, 3);
		/**brakfast menu - dishes**/
		insert into MENU_DISH values(2, 5);
		insert into MENU_DISH values(2, 7);
		insert into MENU_DISH values(2, 2);
		/**Main menu - dishes**/
		insert into MENU_DISH values(3, 4);
		insert into MENU_DISH values(3, 5);
		insert into MENU_DISH values(3, 6);
		/**Children menu - dishes**/
		insert into MENU_DISH values(4, 5);
		insert into MENU_DISH values(4, 6);
		insert into MENU_DISH values(4, 7);


/*Add to table ORDER_DISH*/
		insert into ORDER_DISH values(1, 3);
		insert into ORDER_DISH values(1, 1);
		insert into ORDER_DISH values(1, 4);
        
        insert into ORDER_DISH values(2, 2);
        insert into ORDER_DISH values(2, 5);
        insert into ORDER_DISH values(2, 7);
        
        insert into ORDER_DISH values(3, 4);
        insert into ORDER_DISH values(3, 6);
        insert into ORDER_DISH values(3, 7);