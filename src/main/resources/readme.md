performances issues to detect:
- N+1 queries : une query pour la liste principale + 1 par collection pour chaque item de la liste principale 
  - Solution : 
  - faire join fetch avec @Query
  - Faire @Batch : diminue le nombre de queries et pas d'explosion cartessiene
- Complexité cartessiene avec join fetch avec @Query
- fetch type eager : susceptible de faire des queries inutile si list pas utilisé
- pas d'index


Log deux fois de la query 
logging.level.org.hibernate.SQL=DEBUG -> affiche query en log
spring.jpa.properties.hibernate.format_sql=true -> affiche query brute

avec fetch join
6416500 nanoseconds spent preparing 1 JDBC statements;
11129500 nanoseconds spent executing 1 JDBC statements;

avec n+1 queries
2772300 nanoseconds spent preparing 3 JDBC statements;
9867200 nanoseconds spent executing 3 JDBC statements;