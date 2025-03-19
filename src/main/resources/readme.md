performances issues to detect:
- N+1 queries : when we have a query that is executed for each element of a collection
- Solution : eager fetching
- Eager fetching


Log deux fois de la query 
logging.level.org.hibernate.SQL=DEBUG -> affiche query en log
spring.jpa.properties.hibernate.format_sql=true -> affiche query brute
