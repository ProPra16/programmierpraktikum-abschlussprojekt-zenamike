# Protocol from July 6 2016, 14:30 - 16:00

_Participants_: Kevin Dreyer, Michael Liske, Zeljko Bekcic, Nadine Foerster

## Today's topics
* Debriefing on last weeks topics
* Discussing function and use Gradle and Travis 
* Overview on our project's status


### Debriefing on last weeks topics

We simply skimmed across last weeks results. We also clarified organisational issues and talked about task that stil need to be done, etc.

### Discussing function and use Gradle and Travis 

Since we still had some time at the end of our meeting, we decided to talk about the function and use of Gradle and Travis again, not only concerning our project itself, but also in a more generalized way.

### Overview on our project's status

Since we had a lot of changes concerning the project's structure and other aspects, we decided to make an overview on our project regarding packages and classes.

| Package: Main  					| 
|-----------------------------------|
|contains: Main.java                |
| 								    |

**_NOTE:_** Main.java ist the startpoint in our application. It instantiates a MainController-Object using a stage as an parameter.

| Package: Cycle  					| 
|-----------------------------------|
|contains: CycleEnum.java, CycleInformation.java, Cycle.java                |
| 								    |

| Package: Settings 					| 
|-----------------------------------|
|contains: Setting.java, Settings.java,SettingManager.java, SettingException.java  |
| 								    |

| Package: Plugin 					| 
|-----------------------------------|
|contains: Plugin.java, PluginLoader.java, PluginManager.java, subpackage: babystep, subpackage: tracker  |
| 								    |

| Subpackage: babystep 					| 
|-----------------------------------|
|contains: BabySteps.java  |
| 								    |

| Subpackage: tracker 					| 
|-----------------------------------|
|contains: Tracker.java  |
| 								    |

| Package: contentManager 					| 
|-----------------------------------|
|contains: CycleManager.java, TextManager.java  |
| 								    |

| Package: util 					| 
|-----------------------------------|
|contains: subpackage: classnameparser, subpackage: xml |
| 								    |

| Subpackage: classnameparser 					| 
|-----------------------------------|
|contains: ClassNameParser.java, ClassNameParserException.java  |
| 								    |

| Subpackage: xml 					| 
|-----------------------------------|
|contains: subpackage: reader, subpackage: writer  |
| 								    |

| Subpackage: reader 					| 
|-----------------------------------|
|contains: XMLReader.java, XMLReaderDrive.java, XMLReaderHandler.java, XMLInformationExtractor.java  |
| 								    |

| Subpackage: writer 					| 
|-----------------------------------|
|contains: XMLWriter.java  |
| 								    |

| Package: controller 					| 
|-----------------------------------|
|contains: MainController.java, CustomController.java, ProgramController.java, SettingsController.java, StartController.java  |
| 								    |

| Package: controllerLoader 					| 
|-----------------------------------|
|contains: ControllerLoader.java, CustomController.java, ProgramController.java  |
|SettingsControllerLoader.java, StartControllerLoader.java,MainControllerLoader.java   |
|TDDControllerLoader.java								    |


Task that remain to be done/task that may be improved:

Nadine: XML-Reader (improve), XML-Writer (implement), XML-InformationExtraction (improve)

Michael & Nadine: Package contoller & controllerLoader (partly implement, partly improve), integrating cycle & plugins

Zeljko: Tracking (implement (needs xml-related classes)), settings (needs xml-related classes)

Kevin:  Making tests for cycle and ensure that cycle works









