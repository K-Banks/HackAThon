#### _Hack-A-Thon Planner_

#### By: _**Kayl Eubanks**_

## Description

_This application is designed for organizing teams for a Hack-A-Thon style event. Users will be able to submit a team with members, a team name, and a brief description. Users will also be able to update teams._

## Setup/Installation Requirement for Developers:

* Clone repository on your local computer from https://github.com/K-Banks/HackAThon.
* If you already have Java and IntelliJ IDEA installed, then skip to "Running Application"

  #### Java Installation Instructions:
  * Navigate to http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
  * Download appropriate Java SDK
  * Install file
  * Navigate to https://www.java.com/en/
  * Download Java JRE
  * Install file
  * open Command Line/Bash/Console
  * run command '$ java -version' to confirm installation

  #### IntelliJ IDEA Installation Instructions:
  * Navigate to https://www.jetbrains.com/idea/#chooseYourEdition
  * Download the community version (available for free)
  * Install file

  #### Running Application:
  * Open IntelliJ IDEA
  * Go to File -> Open
  * Find the project folder and open from root directory of project
  * Select src -> main -> java -> App.java
  * Right click on App.java
  * Press 'Run'
  * Open web browser to local host ip address given in the run window

## Specs
 * User will be able to create a new team. Upon creation, user will provide:
    * A list of member names
    * A team name
    * A brief description of the team
 * User will be able to add or remove members of the team after initial submission.
 * User will be able to change team name of a team after initial submission.
 * User will be able to change description of the team after initial submission.
 * User will be able to delete team after initial submission.
 * User will be required to enter a valid team name and at least one member upon new team submission

## Known Bugs

_All members can be removed from a team, undermining requirement that teams start with at least one member._
_Team name can be changed to an invalid name after initial submission._
_Please contact author at kayleubanks@gmail.com with any additional bugs._

## Technologies Used

 * Java
 * IntelliJ IDEA
 * Gradle
 * JUnit
 * Spark
 * Handlebars/Mustache

### License

This software is licensed under the MIT license.

Copyright (c) 2018 ****_Kayl Eubanks_****