# Protocol from June 29 2016, 14:30 - 17:15

_Participants_: Kevin Dreyer, Michael Liske, Zeljko Bekcic, Nadine Foerster

## Today's topics
* Debriefing on last weeks topics
* Presenting problems and propositions to each area of responsibility
* Creating possible user stories
* Addition: description of back-end and front-end processes (Zeljko)


### Debriefing last weeks topics

We simply skimmed across last weeks results including the CRC-cards and the distribution of the tasks. We also clarified organisational issues. 

### Presenting problems and propositions to each area of responsibility

More or less each member gave an update to their area of responsibility. The following issues were discussed:

* dependencies between the packages/ different areas of responsibility
* adding a ContentManager to the CRC-cards (new package)
* adding a Main package/Main class 
* adding a MainController 
* a rough design of the graphical design and the representation of the different functionalities (several stages, several windows, representation of the process, ...)

| Package: Main  					          | 
|-----------------------------------|
|__Dependencies:__                  |
| (Main-)Controller				          |

| Package: Controller  			      	| 
|-----------------------------------|
|__Dependencies:__                  |
| Main, ContentManager			        |

| Package: Cycle  				        	| 
|-----------------------------------|
|__Dependencies:__                  |
| ContentManager				            |

| Package: util  				          	| 
|-----------------------------------|
|__Dependencies:__                  |
| ContentManager,Plugins		        |

| Package: ContentManager  			    | 
|-----------------------------------|
|__Dependencies:__                  |
| Controller, util, Cycle		        |

**_NOTE:_** There may be more packages or packgages might be altered or deleted. This is just a rough outline for now.

### Creating possible user stories

We created a possible user story and also described the processes in the back-end assigning each process to an area of responsibility:

|User Story                        | Prozesse im Hintergrund                    | 
|----------------------------------|--------------------------------------------|
| User startet das Programm       | eine Instanz von Main wird erzeugt, womit der Maincontroller initialisiert und die GUI erzeugt wird _M_|
|Nutzer werden Aufgaben aus Aufgabenkatalog vorgeschlagen | ein spezifiziertes Verzeichnis wird nach vorliegenden Augaben durchsucht, die daraufhin aufgelistet dargestelt werden _M_; Aufgaben liegen in XML-Format vor, die mit XML-Reader gelesen werden _N_ |
|Nutzer wählt Aufgabe aus und Aufgabe wir geladen | Aufgabeninhalt wird angezeigt und kann durch ein GUI-Element bestätigt werden _M_; wird folglich auch geladen(Tracker) _Z_|
|Scene ändert sich und User kann einen fehlschlagenden Test schreiben| verschiedene Szenarien müssen berücksichtigt werden, Kompilierfehler aufgrund verschiedener Ursachen; Cycle-Funktionalität _K_|
|Nutzer darf Code (rechts) editieren _BEDINGUNGEN_: Man darf nach Red zurück, alle Tests müssen laufen kompilierbar sein,Nutzer teilt Phasenübergang explizit mit| rechtes Textfeld offen, linkes Textfeld geschlossen; Button-CLickEvent (neuer Code wird gelöscht); Überprüfung bei Übergang in Refactor -> Textfelder bleiben & Code muss/darf verbessert werden|
|Nutzer schließt Refactor ab | Wechsel in Red|








### Addition: description of back-end and front-end processes (Zeljko)

# USERSTORY

## OR: HOW THE PROGRAMM COULD/SHOULD WORK

This is a possible userstory written by Zeljko Bekcic.

_EDIT: I added this here after nearly finishing the `FRONT-END`. It is not a userstory by definition, but rather a general description of the programm. Not how it has to look like, rather what it has to look like, what it has to contain to work how we intend it to. Therefore I first describe the `FRONT-END` to make this part clear. Secondly the `BACK-END` to explain how the parts work together._

## SUMMED UP DESCRIPTION
---------------

The user starts the programm, currently it is not defined where and how he starts it, because it does not matter for now.

When the user starts the programm, he expects to access the settings and start the "regular" programm. With "regular" programm I mean that he is able to choose a task out of a list of tasks and then work on it in the TDD-cycle until he finishes the task.

## DETAILED USERSTORIES
----------------
----------------
### FRONT-END 
-----------------
#### STARTING THE PROGRAMM
The user starts the programm and enters a menu where he can access through GUI-elements a `settings-menu` and the `'task-list'-menu`.

In the `settings-menu` the user can turn the plugins on and off and and controll other settings.
E.g. if you want to set a custom timespan in the `Babysteps-Plugin`, you should be able to do so in the settings.

In the `'task-list'-menu` you will get a list of tasks which are in a specified directory on you computer. You can choose one and then the programm starts the `TDD-cycle` with the selected task. While you are selecting the task out of the list. you should be able to see the entire informations about the task . E.g. explanaition for the task, given code and so on.

After you have choosen your task out of the allmighty `'task-list'-menu`, you can confirm you task an proceed to the `TDD-cycle`.

### STARTING THE TDD-CYCLE

The user has selected a task and entered the `TDD-cycle`. The GUI has two textareas, one for the code and one for the test. These text areas will be controlled through the phases of the `TDD-cycle`.
I expect whoever reads this illustration has a basic knowledge of the`TDD-cycle` and so I will not go any further into it. Somewhere has to be a mark/sign to show you in which phase you are. Furthermore the GUI should let you compile the written code/test. If your code/test fits the particular conditions __the tool will switch into the next phase__ (_NOTE: This should and will only happen in the phase where you write code to your test and the phase where you write test to the (probably) not existing code)_.  Additionaly there has to be a GUI-element to step from the green-phase back to the red-phase.

---------------
---------------
### BACK-END
----------------
### STARTING THE PROGRAMM AND USING THE PROGRAMM

The user starts the programm with the `Main`. It's `main-method` intitalizes an instace of the `MainController`. The `MainController` is the tool which handels all the other controller and lets them interact with each other (keyword: `Nested Controller`). The default Scene of the `MainController` is a Scene where you can access the `settings-menu` and the `'task-list'-menu`. Let's call it the `StartMenuScene`. Through the `StartMenuScene` you can access a scene, where you can see the settings and edit them, the `SettingsScene`. And a scene where you enter a "pre-phase" of the TDD-cycle, where you can select the task you want to do/solve. I am going to call it `TaskListScene`.

To each of (not only those above, also the incoming) scenes belongs a `Controller` and a `ControllerLoader`.

After the user has selected a task, he will be directed into a new scene, where he can solve this task inside of the TDD-cycle. I will call this scene `TDDScene`.

#### SWITCHING THE PHASES

When the user switches the phases, the programm does the following things.
The button accesses the `CycleManager` and sets the current value to the value of the next phase of the cycle.

 ` --> [CYCLE.TEST] --> [CYCLE.CODE] --> [CYCLE.REFACTOR] --> [CYCLE.TEST] --> ... `

It should be obvious that the phases are represented through enums and the `CycleManager` inteprets these enums.

### MANAGING THE WRITTEN CODE AND SHARING THE INFORMATION

The user writes code and the code has to be accessed by at least on object. That requieres a well-conceived structure. We could use a `ContentManager` which will be splitted up into a `CodeManager` and a `TestManager` to access the textarea's contents.

Why are we doing it like this? Why no direct access? With this construction we can let multiple instances of what we want access this information. And here comes the `Plugin` and `PluginManager`. They can access these information eaily through this construction.

The same apllies to the `CycleManager` and the (not mentioned) `SettingsManager`.
It should be trivial what the task of the `SettingsManager` is.

### MAKING HANDLING PLUGINS EASIER

The mentioned `PluginHandler` is the key to solve this problem. A `Plugin` can be detected by a `PluginLoader`. Now we have a GUI running (JavaFX, which runns in an own thread) and should be able to run a plugin while the GUI runns. A `PluginThread` can solve this problem.








