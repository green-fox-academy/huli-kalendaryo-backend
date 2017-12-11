# huli-kalendaryo-backend

## Introduction
The goal of this project is to provide a simple application where the user can collect event data from multiple calendars into one calendar that can be shared with the right people. 

###### This is the project's backend part, [here](https://github.com/greenfox-academy/huli-kalendaryo-android) you can find the link to its android repository.

## Setting up the environment
To run the application's backend part you need to have the following things set up:
1. In order to get the necessery components (client id and client secret) for the authentication part you should register your application by following the instruction on [this](https://developers.google.com/google-apps/calendar/quickstart/java) website

2. Create a database with the help of MySQL.

3. In the IDE there should be the following Environmental variables set up:
* CLIENTID: this is given after registering the application
* CLIENTSECRET: this is given after registering the application
* RDSHOSTNAME: localhost
* RDSPORT 3306
* RDSDBNAME: the name of the database 
* RDSUSERNAME: username for the database
* RDSPASSWORD: password for the database
* OPALCALPORT: 8080





