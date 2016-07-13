package de.hhu.propra.tddt.cycle;

/**
 * Created by zeljko On 01.07.2016
 */




/*********************************************************
 * Class: CycleManager
 * <p>
 * Task: Class handling the cycles, initializes the cycles
 * and gets the current cycle returned. Also commands the cycle
 * to move to the next phase.
 *
 * @author Kevin
 ********************************************************/


public class CycleManager {


    Cycle cycle = new Cycle();


    public CycleManager(){
        this.cycle = new Cycle();
    }

    /**************************************************************************
     * Method: getCurrentPhase
     * <p>
     * Calls the current Phase from the cycle class
     *
     * @return currentPhase of the cycle
     **************************************************************************/

    public CycleEnum getCurrentPhase() {

        return cycle.getCurrentPhase();
    }

    /**************************************************************************
     * Method: nextPhase
     * <p>
     * Calls the cycle and orders it to move to the next phase
     *
     * @return currentPhase of the cycle
     **************************************************************************/

    public CycleEnum nextPhase(){
        cycle.enterNextPhase();
        return getCurrentPhase();
    }

    /**************************************************************************
     * Method: initializePhases
     * <p>
     * fills the LinkedList in Cycle with the Phases (Enums)
     *
     * @return currentPhase of the cycle
     **************************************************************************/

    private void initializePhases(){
        cycle.phaseList.add(CycleEnum.CODE);
        cycle.phaseList.add(CycleEnum.TEST);
        cycle.phaseList.add(CycleEnum.REFACTOR);
    }

}
