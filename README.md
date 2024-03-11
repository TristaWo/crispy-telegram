# COMP3005 A3 Q1
Trista Wang
101231212

# Files
- README.md
- students.sql
- CRUPApp.java

# Installation
- Install IntelliJ, no other packages needed

# Set Up
**PgAdmin**
- Create database in pgAdmin (Mine was named COMP3005Assignment3)
- Run SQL query found in sql/students.sql to create the "students" table and insert values
  
**IntelliJ**
- Clone this program into IntelliJ
- In main, update the following code to match your local PgAdmin database name and credentials
```
String url = "jdbc:postgresql://localhost:5432/COMP3005Assignment3";
        String user = "postgres";
        String password = "admin";
```
- Run CRUDApp.java

# Video Demo
https://youtu.be/wGCHOgXaQyw
