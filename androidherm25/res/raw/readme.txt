To export data from mysql database :

sx0419@cetrellaptop:~/polyface$ mysql -u root -p

mysql> use polyface
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> select * from Aliases into outfile '/tmp/aliases.csv';
Query OK, 59 rows affected (0.14 sec)

mysql> select * from RegisteredUsers into outfile '/tmp/registeredusers.csv';
Query OK, 14 rows affected (0.00 sec)
__________________________

Database is created in /data/data/net.stenuit.xavier.androidherm25/databases/polyface.db


