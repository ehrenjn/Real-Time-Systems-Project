SYSC 3303 - Project Specification - Iteration 3 -

-----------------------------------------------------------------

Info:
The purpose of this project is to implement an elevator control system with a multithreaded simulator.

A video demonstration of the working code is included in this repository and named as "Final Presentation Video.mp4"

-----------------------------------------------------------------

Prerequisites:
	- IDE used: Eclipse


-----------------------------------------------------------------

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
	- This workaround is because of how we are supposed to ingest the event file was never explicitly specified

4. From within the project "Real-Time-Systems-Project"
	- Right click on the SchedulerSubsystem.java in the Scheduler package in Eclipse IDE
	- Select "Run As" then "Java Application" to run the application
	- Right click on the ElevatorSubsystem.java in the Elevator package in Eclipse IDE (on any computer)
	- Select "Run As" then "Java Application" to run the application (You will need to enter the IP of the Scheduler which will appear in the console when you run the SchedulerSubsystem)
	- Right click on the FloorSubsystem.java in the Floor package in Eclipse IDE (on any computer)
	- Select "Run As" then "Java Application" to run the application (You will need to enter the IP of the Scheduler which will appear in the console when you run the SchedulerSubsystem)
	- Right click on the tests package in Eclipse IDE
	- Select "Run As" then "JUnit Test" to run the tests of the application


-----------------------------------------------------------------

FILE EXPLANATIONS:
	- ElevatorSubsystem.java
		- When run from main(), initializes an elevator object with an elevator socket and an array of floors the elevator has to visit.
	- FloorSubsystem.java
		- When run from main(), initializes an array of floors, each with an associated floor socket.
	- SchedulerSubsystem.java
		- When run from main(), initializes a scheduler object with a floor socket and a elevator socket.


All Diagrams are located in the 'doc' folder
	- ClassDiagram.png: The UML diagram of the System for Iteration 1
	- ClassDiagramIteration2.png: The UML class diagrams for all of the state systems for Iteration2.
	- SequenceDiagramIteration2.png: The sequence diagram of the systems for Iteration 2.
	- Cooperative State Machine.png: The state machine diagram of the elevator and the scheduler for Iteration 2.


-----------------------------------------------------------------

Iteration 3 Reflection
	In iteration 3, there are now multiple threads in the Scheduler, compared to Iteration 2, so that the scheduler can handle event requests asynchronously.

-----------------------------------------------------------------
BREAKDOWN OF RESPONSIBILITIES
 ~ITERATION 1~

Chris Wang (100951354)
	- Responsible for Project organization, project structuring, project architecture, and project implementation
	- Main.java, ElevatorSubsystem.java, FloorSubsystem.java, SchedulerSubsystem.java, CommunicationSocket.java, Event.java, TestEventReader.java, javadoc

Ehren Julien-Neitzert (101046053)
	- Responsible for project implementation, project validation, and design insights
	- EventReader.java, Elevator.java, TestCommunicationSocket.java, javadoc

Naomi Lui-Hing (101040800)
	- Responsible for project implementation, project validation, and design insights
	- Floor.java, javadoc

Manel Oudjida (100945382)
	- Responsible for project documentation, class diagrams, and design insights
	- ClassDiagram.png, project structure insights, javadoc

Nathan Fohkens (100946190)
	- Was very ill during most of the implementation of Iteration 1 (No fault)
	- Has begun to work on Iteration 2 implementation


~ITERATION 2~

Chris Wang (100951354)
	- Responsible for the implementation of the Elevator Subsystem state machine design, Floor state Subsystem machine design and Scheduler Subsystem state machine design.
	- package: elevator, event.toElevator, floor, event, event.toScheduler, test

Ehren Julien-Neitzert (101046053)
	- Responsible for the implementation of the Elevator Subsystem state machine design, Floor state Subsystem machine design and Scheduler Subsystem state machine design.
	- package: scheduler floor, event, event.toScheduler

Naomi Lui-Hing (101040800)
	- Responsible for Elevator Subsystem state machine design, Floor Subsystem state machine design, project documentation
	- javadoc, tests

Manel Oudjida (100945382)
	- Responsible for Elevator Subsystem state machine design, project documentation, sequence diagram and UML class diagram.
	- javadoc , sequenceDiagram.png , UMLClassDiagram.png

Nathan Fohkens (100946190)
	- Responsible for Elevator Subsystem state machine design, Floor Subsystem state machine design and Scheduler Subsystem state machine.
	- Cooperative State Machine.png

~ITERATION 3~

Chris Wang (100951354)
	- Responsible for the implementation of the Elevator side of the RPC, and testing.
	- package: RPCSender, test

Ehren Julien-Neitzert (101046053)
	- Responsible for the implementation of the Scheduler side of the RPC, and the Serialization and Datagram/socket stuff.
	- package: RPCReceiver, network, common, elevator, event, event.toElevator, event.toScheduler, floor, scheduler

Naomi Lui-Hing (101040800)
	- Responsible for Serialization and DatagramSocket implementations, testing, and project documentation.
	- package: floor, elevator, network, tests

Manel Oudjida (100945382)
	- Responsible for sequence diagram, readme and UML class diagram
	- javadoc, sequenceDiagram.png , UMLClassDiagram.png

Nathan Fohkens (100946190)
	- Responsible for Scheduling Algorithm
	- package: scheduler

~ITERATION 4~

Chris Wang (100951354)
	- Responsible for project documentation, design insights, fault handling, testing
	- Javadoc, ElevatorState.java, ElevatorFailureState.java, TestElevatorFault.java

Ehren Julien-Neitzert (101046053)
	- Responsible for project documentation, design insights, tests, recording of final working system for submission
	- Javadoc,

Naomi Lui-Hing (101040800)
	- Responsible for project documentation, class diagrams, design insights, Final Report
	- UpdatedClassDiagram.png, Javadoc, Final report

Manel Oudjida (100945382)
	- Responsible for project documentation, class diagrams, design insights, Final Report
	- UpdatedClassDiagram.png, Javadoc, Final report

  Nathan Fohkens (100946190)
  	- Responsible for project documentation, design insights, testing
  	- Javadoc, Final report

The Github repository containing the branch for iteration 1:
	- https://github.com/ehrenjn/Real-Time-Systems-Project/tree/
