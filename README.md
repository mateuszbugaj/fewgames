# Full-stack application

## Table of contents
1. [Description](#description)
2. [Technologies](#technologies)
3. [Launch](#launch)
4. [Todo](#todo)

## Description <a name="description"></a>
Full-stack app made with Angular and Spring-boot. Contains 'few games' written in TypeScript in which user
can play and save scores as anonymous or while logged in. Scores are saved on MySQL database and displayed
under game in decreasing order with additional info. For this moment, users log in via Basic Authentication.
Games are using P5JS library and are displayed as components.

![screenshot](https://imgur.com/o9YpEFF.jpg)

## Technologies <a name="technologies"></a>
- Java
- Maven
- Angular
- TypeScript
- MySQL
- Spring-boot
- Spring Security

## Launch <a name="launch"></a>
From the root directory, run the following to install modules:
```
mvn generate-resources
```
Then simply run project locally with
```
mvn spring-boot:run
```
Note, there must be MySQL server running on port `3306` with dtabase called `fewgames_dev`.

## Todo <a name="todo"></a>
- Authentication using JWT
- Frontend unit testing using Karma
- More tests for backend using Mockito and Junit
