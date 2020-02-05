SYSC 3303 - Project Specification - Iteration 1 -

Info:
The purpose of this project is to implement an elevator control system with a multithreaded simulator.


Prerequisites:
	- IDE used: Eclipse


Import Instructions:
TO IMPORT THE PROJECT INTO ECLIPSE AND RUN PROGRAM

1. Import project from archive file into Eclipse

2. From Eclipse
	- Select 'File' menu
	- Select 'Import' menu item
	- Under 'General' select 'Projects From File System or Archive File'
	- Select 'Archive...'
	- Locate 'Real-Time-Systems-Project.zip' as Import source.
	- Click 'Finish'
	
3. Setup file to read from
	- Rename the text file that contains all the events to read from to floorEvents.tsv (an example is provided)
	- place floorEvents.tsv in the project root (similar to the example)
	- floorEvents.tsv does not need to be a tab separated file, any whitespace character separation will work
	- However the extension of floorEvents.tsv MUST be a .tsv file (again please follow the example)
	- This workaround is because how we are supposed to ingest the event file was never explicitly specified
	
4. From within the project "Real-Time-Systems-Project"
	- Right click on the main package in Eclipse IDE
	- Select "Run As" then "Java Application" to run the application
	- Right click on the tests package in Eclipse IDE
	- Select "Run As" then "JUnit Test" to run the tests of the application
	

FILE EXPLANATIONS:
	- floorEvents.tsv An example file that contains the 
	- main.java
		- When run from main(), this will create new objects from the communicationSocket, ElevatorSubsystem, FloorSubsystem and SchedulerSubsystem. Instance of Java Thread are created for each Subsystem.


All Diagrams are located in the 'doc' folder
	- ClassDiagram.png: The UML diagram of the System for Iteration 1


BREAKDOWN OF RESPONSIBILITIES for Iteration 1

Chris Wang (100951354) 
	- Responsible for Project organization, project structuring, project architecture, and project implementation
	- Main.java, ElevatorSubsystem.java, FloorSubsystem.java, SchedulerSubsystem.java, CommunicationSocket.java, Event.java, TestEventReader.java, javadoc
	
Ehran Julien-Neitzert (101046053)
	- Responsible for project implementation, project validation, and design insights
	- EventReader.java, Elevator.java, TestCommunicationSocket.java, javadoc

Naomi Lui-hing (101040800)
	- Responsible for project implementation, project validation, and design insights
	- Floor.java, javadoc
	
Manel Oudjida (100945382)
	- Responsible for project documentation, class diagrams, and design insights
	- ClassDiagram.png, project structure insights, javadoc
	
Nathan Fohkens (100946190) 
	- Was very ill during most of the implementation of Iteration 1 (No fault)
	- Has begun to work on Iteration 2 implementation


The Github repository containing the branch for iteration 1:  
	- https://github.com/ehrenjn/Real-Time-Systems-Project/tree/christopher-wang-lean