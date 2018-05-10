# Huli Kalendaryo Backend

## Introduction
The goal of this project is to provide a simple application where the user can collect event data from multiple calendars into one calendar that can be shared with the right people.

** This is the project's backend part, [here](https://github.com/greenfox-academy/huli-kalendaryo-android) you can find the link to its android repository.**

## Setting up the environment
To run the application's backend part you need to have the following things set up:

1. Create a a database schema with the help of MySQL

2. Ask us for the `debug.keystore` file (temporary method during development), which you need to put in your Users/"yourusername"/.android folder (in case of windows)

3. Use "dev" as an @ActiveProfiles annotation in the main class (import the library, if necessary)

4. Set the Active Profile to "dev" in your IDE (in IntelliJ Run/Edit Configurations)

5. You should set up the following environmental variables:

* CLIENT\_ID: this is created in the Google API Console to validate the application (ask us for the exact CLIENT_ID for the time being)
 * CLIENT\_SECRET: this is created in the Google API Console to validate the application (ask us for the exact CLIENT_SECRET for the time being)
* RDS\_HOSTNAME: your hostname in MySQL
* RDS\_PORT your port in MySQL
* RDS\_DB\_NAME: the name of the database
* RDS\_USERNAME: username for the database
* RDS\_PASSWORD: password for the database
* OPAL\_CAL\_PORT: your local port or the aws port