SYSC 3303 - Project Specification - Iteration 2 -

-----------------------------------------------------------------

Info:
The purpose of this project is to implement an elevator control system with a multithreaded simulator.


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
	- Right click on the main package in Eclipse IDE
	- Select "Run As" then "Java Application" to run the application
	- Right click on the tests package in Eclipse IDE
	- Select "Run As" then "JUnit Test" to run the tests of the application
	

-----------------------------------------------------------------

FILE EXPLANATIONS:
	- main.java
		- When run, this will create new objects from the communicationSocket, ElevatorSubsystem, FloorSubsystem and SchedulerSubsystem. Instances of Java Thread are created for each Subsystem.
	- ElevatorSubsystem.java
		- When run from main(), initializes an elevator object with an elevator socket and an array of floors the elevator has to visit.
	- FloorSubsystem.java
		- When run from main(), initializes an array of floors, each with an associated floor socket.
	- SchedulerSubsystem.java 
		- When run from main(), initializes a scheduler object with a floor socket and a elevator socket.
<<<<<<< HEAD

=======
	-ElevatorState.java
		- Abstract class that implements the base state for the elevator.
	-ElevatorCloseDoorState.java, ElevatorClosingDoorState.java, ElevatorOpenDoorState.java, ElevatorOpeningDoorState.java, ElevatorMovingState.java
		- Implements the five finite states of the elevator along with one additional failure state (ElevatorFailureState.java)
>>>>>>> 5794880d2396c8959ac788ca526f5b9627f69b07

All Diagrams are located in the 'doc' folder
	- ClassDiagram.png: The UML diagram of the System for Iteration 1
	- ClassDiagramIteration2.png: The UML class diagrams for all of the state systems for Iteration2.
	- SequenceDiagramIteration2.png: The sequence diagram of the systems for Iteration 2.
	- Cooperative State Machine.png: The state machine diagram of the elevator and the scheduler for Iteration 2.


-----------------------------------------------------------------

BREAKDOWN OF RESPONSIBILITIES
 ~ITERATION 1~

Chris Wang (100951354) 
	- Responsible for Project organization, project structuring, project architecture, and project implementation
	- Main.java, ElevatorSubsystem.java, FloorSubsystem.java, SchedulerSubsystem.java, CommunicationSocket.java, Event.java, TestEventReader.java, javadoc
	
Ehran Julien-Neitzert (101046053)
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
	
Ehran Julien-Neitzert (101046053)
	- Responsible for the implementation of the Elevator Subsystem state machine design, Floor state Subsystem machine design and Scheduler Subsystem state machine design.
	- package: scheduler floor, event, event.toScheduler

Naomi Lui-Hing (101040800)
	- Responsible for Elevator Subsystem state machine design, Floor Subsystem state machine design, project documentation, 
	- javadoc 

Manel Oudjida (100945382)
	- Responsible for Elevator Subsystem state machine design, project documentation, sequence diagram and UML class diagram.
	- javadoc , sequenceDiagram.png , UMLClassDiagram.png
	
Nathan Fohkens (100946190) 
	- Responsible for Elevator Subsystem state machine design, Floor Subsystem state machine design and Scheduler Subsystem state machine.
	- Cooperative State Machine.png

The Github repository containing the branch for iteration 1:  
<<<<<<< HEAD
	- https://github.com/ehrenjn/Real-Time-Systems-Project/tree/christopher-wang-lean


BREAKDOWN OF RESPONSIBILITIES for Iteration 2


Chris Wang (100951354) 
	- Responsible for Elevator Subsystem state machine design, Floor state Subsystem machine design, Scheduler Subsystem state machine design, project documentation, 
	- 
	
Ehran Julien-Neitzert (101046053)
	- Responsible for Elevator Subsystem state machine design, Floor Subsystem state machine design,  Scheduler Subsystem state machine design, project documentation, 
	- 

Naomi Lui-Hing (101040800)
	- Responsible for Elevator Subsystem state machine design, Floor Subsystem state machine design, project documentation, 
	- 

Manel Oudjida (100945382)
	- Responsible for project documentation, sequence diagram, UML class diagram
	- 
	
Nathan Fohkens (100946190) 
	- Responsible for Elevator Subsystem state machine design, Floor Subsystem state machine design, Scheduler Subsystem state machine, project documentation, 
	- 

The Github repository containing the branch for iteration 1:  
	- https://github.com/ehrenjn/Real-Time-Systems-Project/tree/

=======
	- https://github.com/ehrenjn/Real-Time-Systems-Project/tree/
>>>>>>> 5794880d2396c8959ac788ca526f5b9627f69b07
