# HANDIPRO-SpringBoot

## Simple REST app designeg for physiotherapists and patients

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
Main purpose of the app is to storing data patients and physiotherapists in H2 database. If users want to be a part of this service they have to 
go through registration process. Registartion is based on emial verification. If app get user's data like password, email, name and surname, it will send
emial with confirmation link. If user clicks in confirmation link, the app will generate confirmation token. In connection with app development process relational 
database was created. One physiotherapist can have many patients and also can upload rehabilitation tasks for them. One patient can have many tasks. Task entity consist of 
video and csv file.

## Technologies
* Java 13
* Spring boot
* Hibernate/JPA
* Flyway - SQL migration
* Maven
* JUnit
* Mockito

## Setup
The best way to setup this app is to use docker.

* Download docker from official docker website and intall
* Open Power Shell and move to project's directory
* Create new docker image with command: docker bulid -f Dockerfile -t [your_image_name]:v1 .
* Run app with command: docker run -p 8000:8080 [three_leading_numbers_from_img_id]

## Screenshots

![Database_schema](./database_giagram.png)
![JSON_view](./postman_registration.png)

