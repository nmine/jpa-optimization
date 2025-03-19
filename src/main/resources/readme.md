performances issues to detect:
- N+1 queries : when we have a query that is executed for each element of a collection
- Solution : eager fetching
- Eager fetching


Log deux fois de la query 
logging.level.org.hibernate.SQL=DEBUG -> affiche query en log
spring.jpa.properties.hibernate.format_sql=true -> affiche query brute

avec fetch join
6416500 nanoseconds spent preparing 1 JDBC statements;
11129500 nanoseconds spent executing 1 JDBC statements;

avec n+1 queries
2772300 nanoseconds spent preparing 3 JDBC statements;
9867200 nanoseconds spent executing 3 JDBC statements;