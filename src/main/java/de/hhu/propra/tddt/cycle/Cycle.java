package de.hhu.propra.tddt.cycle;

/**
 * Created by zeljko On 23.06.2016
 */


import java.util.LinkedList;


/*********************************************************
 * Class: Cycle
 * <p>
 * Task: Class handling the cycle. The cycle is initialized with
 * a linked list.
 *
 * @author Kevin
 * @version the current version is changed in order
 * to work with the information core
 ********************************************************/

public class Cycle {
    LinkedList<CycleEnum> phaseList = new LinkedList<>();
    int currentPhaseAsInt = 0;



    /**************************************************************************
     * Method: add phase
     * <p>
     * adds a new cycleenum to the list
     *
     * @param cycleEnum the list is filled with enums.
     *
     * @return void
     **************************************************************************/
    public void addPhase(CycleEnum cycleEnum){
        phaseList.add(cycleEnum);
    }

    /**************************************************************************
     * Method: numberOfPhases
     * <p>
     * returns the size of the linked list thus giving the number of the initiliazed
     * cycles phases
     *
     *
     * @return currentPhase of the cycle
     **************************************************************************/

    public int numberOfPhases(){
        return phaseList.size();
    }

    /**************************************************************************
     * Method: getCurrentPhase
     * <p>
     * returns the active cycle to the caller
     *
     * @return currentPhase
     **************************************************************************/

    public CycleEnum getCurrentPhase(){
        return phaseList.get(currentPhaseAsInt);
    }


    /**************************************************************************
     * Method: enterNextPhase
     * <p>
     * increases the currentPhasesAsInt in order to increases the cycle phase.
     * The modulo operation insures that the cycle counter variable stays inside
     * the number of initilized cycles in the linked list
     *
     * @return void
     **************************************************************************/
    public void enterNextPhase(){
        currentPhaseAsInt++;
        currentPhaseAsInt = currentPhaseAsInt % phaseList.size();
    }
}


