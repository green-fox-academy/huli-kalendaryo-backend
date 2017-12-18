# Huli Kalendaryo Backend

## Introduction
The goal of this project is to provide a simple application where the user can collect event data from multiple calendars into one calendar that can be shared with the right people.

** This is the project's backend part, [here](https://github.com/greenfox-academy/huli-kalendaryo-android) you can find the link to its android repository.**

## Setting up the environment
To run the application's backend part you need to have the following things set up:
1. In order to get the necessary components (client id and client secret) for the authentication part you should register your application by following the instructions on [this](https://developers.google.com/google-apps/calendar/quickstart/java) website

2. Create a database with the help of MySQL.

3. You should set up the following environmental variables:


* CLIENT\_ID: this is given after registering the application
* CLIENT\_SECRET: this is given after registering the application
* RDS\_HOSTNAME: your hostname in MySQL
* RDS\_PORT your port in MySQL
* RDS\_DB\_NAME: the name of the database
* RDS\_USERNAME: username for the database
* RDS\_PASSWORD: password for the database
* OPAL\_CAL\_PORT: your local port or the aws port


## Dependencies

Compile the following dependencies in order to use the necessary google libraries:
* 'com.google.apis:google-api-services-calendar:v3-rev271-1.23.0'
* 'com.google.oauth-client:google-oauth-client-jetty:1.23.0'
* 'com.google.api-client:google-api-client:1.23.0'

## Deploy instructions

For deployment we use AWS Elastic Beanstalk and you need to follow these steps for successful deploy for updating:
* Set the SNAPSHOT-version in `build.gradle`.
* Build a `jar` file of the project.
* Find this newly created file in build/libs/huli-kalendaryo-backend-version-number-SNAPSHOT.jar.
* Upload & Deploy the `jar` file.
