# Protocol from July 13 2016, 14:30 - 17:00

_Participants_: Michael Liske, Zeljko Bekcic, Nadine Foerster, Kevin Dreyer

# Today's topics
* Further delving into our classes and how Micheal implements them into the GUI
* Testbehaviour
* Discussion about the XML classes
* User Manual 
* Discussion about Tracker and Compiler
* Discussion about Settings
* Git merge (Kevin alone)


## Further delving into our classes and how Micheal implements them into the GUI
This topic was spanned across the whole meeting since Zeljiko and Micheal tried to work together. One can say it was our main topic from which other topics branched off.


-------------------------------------------------------------------------------------------------------------------------

## Testbehaviour
Everyone should write more tests.


-------------------------------------------------------------------------------------------------------------------------

## Discussion about the XML classes
###Mainquestion:
How are the tasks getting loaded into the program?

###Solution:
Since our project askes us to make the task loadable by the user we discussed how to solve this problem. We decided after a long debate that we use Zeljiko's solution for now, which means that when the program is started the user is asked to put in a path to the exercises. That path is checked by our TDDT and if no exercises are found, an alert box is going to plop up.


---------------------------------------------------------------------------------------------------------------------------

## User Manual
Nadine and Kevin are going to write it.


-------------------------------------------------------------------------------------------------------------------------

## Discussion about Tracker and Compiler
### Mainquestion: 
How does the tracker class get the compile results?

### Solution: 
A list which contains both Lists (compile & test results) is given to the tracker class.


-------------------------------------------------------------------------------------------------------------------------

## Discussion about Settings
### Mainquestion(by Micheal): 
How do I implement your code, since I don't understand it?

### Solution:
Zeljiko explains the usage of his code.


-------------------------------------------------------------------------------------------------------------------------

## Git merge
### Problem:
Kevin tried to merge his testingcycle branch into the master. While he did, he made a mistake by merging the master into the branch and not otherwise. Since there have been pushs after that, he merged the branch into the master and by thus he created no conflicts but many files had a wrong state, since the merge into the branch.


### Solution:

Kevin reseted both master and branch to a commit right before the merge and merge it rightfully. Some commits have been lost due to this.

