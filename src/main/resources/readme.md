## Setup
1) setup en local d'une db postresql (idéalement configure la connection dans Intelij)
2) mettre dans application.properties les infos de connections à la db
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/exercise
   spring.datasource.username=postgres
   spring.datasource.password=admin
   spring.datasource.driver-class-name=org.postgresql.Driver
   ```
3) toujours dans applications properies mettre 
    ```properties
    spring.jpa.hibernate.ddl-auto=create
    ```
pour créer les tables dans ta db locale
4) lance le test unitaire PersonServiceTest.initData() pour créer 100 001 personnes et 5 adresses par personnes.
Il y aura deux personnes avec le name 'Person 500'.

## Logs et métriques
1) Active l'affichage formaté des requêtes SQL générées par Hibernate, ainsi que les statistiques des requêtes.
2) Démarre le test unitaire PersonServiceTest.testFindPersonByName()
3) Quelles sont les requêtes générées par Hibernate ?
4) Quel est le temps d'exécution des requêtes ?

## Plan d'éxécution
1) Rejoue la requête SQL sur la table person en utilisant l'outil EXPLAIN de PostgreSQL.
2) Quel est le plan d'exécution de la requête ? Note chaque étape du plan d'exécution.

## Index
1) Crée un index sur la colonne name de la table person.
2) Rejoue la requête SQL sur la table person en utilisant l'outil EXPLAIN de PostgreSQL, et compare les résultats avec l'étape précédente.

## Eager/Lazy
1) Configure les relations entre person et address, ainsi qu'entre person et child, avec le fetch type LAZY
2) Rejoue le test unitaire PersonServiceTest.testFindPersonByName() et observe les requêtes générées par Hibernate.
3) Configure les relations entre person et address, ainsi qu'entre person et child, avec le fetch type EAGER.
4) Rejoue le test unitaire PersonServiceTest.testFindPersonByName() et observe les requêtes générées par Hibernate.

## N+1 queries
1) Configure les relations entre person et address, ainsi qu'entre person et child, avec le fetch type EAGER.
2) Configure les relations entre person et address, ainsi qu'entre person et child, avec le fetch type EAGER.
3) Décris le problème de N+1 queries en commentant les queries

## Join Fetch
1) Utilise une requête JOIN FETCH dans PersonServiceTest.testFindPersonByName() :
```java 
@Query("SELECT p FROM Person p JOIN FETCH p.addresses JOIN FETCH p.children")
List<Person> findByName(String name);  
```
2) Rejoue le test unitaire et observe les requêtes générées par Hibernate.
3) Compare avec les requêtes générées à l’étape N+1 queries.
4) Rejoue la requête générée par Hibernate sur la base PostgreSQL et commente les résultats en expliquant le problème de complexité cartésienne

## Batch
1) Ajoute l'annotation suivante sur les relations entre person et address, ainsi qu'entre person et child :
   ```java
        @BatchSize(size = 5)
   ```
2) Rejoue le test unitaire :
    ```java
        PersonServiceTest.testFindPersonByName()
    ```
3) Compare avec les requêtes générées à l'étape N+1 queries.
4) Rejoue la requête générée par Hibernate sur la base PostgreSQL.
5) Pourquoi cela résout-il le problème du N+1 et celui de la complexité cartésienne ?




