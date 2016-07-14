package de.hhu.propra.tddt.cycle;

/**
 * Created by zeljko On 23.06.2016
 */


import de.hhu.propra.tddt.compiler.CompileResults;
import de.hhu.propra.tddt.compiler.CompilerManager;



/*********************************************************
 * Class: Cycle
 * <p>
 * Task: Class handling the other class objects. The whole process should happen
 * here. Probably needs additional help functions The javaFX magics gonna happen
 * here (responding to the gui)
 *
 * @author Kevin
 ********************************************************/
public class Cycle {

    CycleEnum phase = CycleEnum.TEST;
    CompilerManager compManager = new CompilerManager();
    CompileResults compResults = new CompileResults();


<<<<<<< HEAD
    /**
     * Method: testPhase
=======
    /***************************************************************************
     * Method: add phase
>>>>>>> 0072d4deb703d115e87998d594179a94f8b1ce03
     * <p>
     * Task: Method that takes the testcode and the normal code and gives it with the
     * currentphase to the compilerManager in the refactor phase.
     *
     * @param testCode String testCode is the whole test the user typed into the textbox
     * @param currentPhase CycleEnum currentPhase gives information to the method with which phase
     *                     the user is currently working. More a failsafe check for the backend
     *                     processes than for the user.
     *
<<<<<<< HEAD
     * @return returns the current phase in which the cycle is at the moment
     */

    public CycleEnum testingPhase(String testCode, CycleEnum currentPhase){
        try{
        if (currentPhase.equals(CycleEnum.TEST)) {

            currentPhase = compManager.compileTest(testCode,currentPhase);

        } else {
            throw new IllegalStateException("Wrong function call");

        }}catch (Exception e){
            System.out.println(e.getMessage());
        }
        return currentPhase;

=======
     * @return void
     *************************************************************************/
    public void addPhase(CycleEnum cycleEnum){
        phaseList.add(cycleEnum);
>>>>>>> 0072d4deb703d115e87998d594179a94f8b1ce03
    }


    /**
     * Method: resetPhase
     * <p>
     * Task: Method that checks if the user is in the right phase to reset their code.
     * If not they'll get notified.
     *
     *
     * @param currentPhase CycleEnum currentPhase gives information to the method with which phase
     *                     the user is currently working.
     */

    public CycleEnum resetPhase(CycleEnum currentPhase){
        if (currentPhase.equals(CycleEnum.REFACTOR)){
            throw new IllegalStateException("Only allowed in the Phase CODE");
        }
        if (currentPhase.equals(CycleEnum.CODE)){
            return phase = CycleEnum.TEST;

        }
        if (currentPhase.equals(CycleEnum.TEST)){
            compResults.setCycleError(2);

        }
        return currentPhase;
    }

    /**
     * Method: codingPhase
     * <p>
     * Task: Method that takes the testcode and the normal code and gives it with the
     * currentphase to the compilerManager in the refactor phase.
     *
     * @param testCode String testCode is the whole test the user typed into the textbox
     * @param code  String code is the whole code which is going to be tested
     * @param currentPhase CycleEnum currentPhase gives information to the method with which phase
     *                     the user is currently working. More a failsafe check for the backend
     *                     processes than for the user.
     *
     * @return returns the current phase in which the cycle is at the moment
     */

    public CycleEnum codingPhase(String code, String testCode, CycleEnum currentPhase){
       try {
           if (currentPhase.equals(CycleEnum.CODE)) {
               currentPhase = compManager.compileCode(code, testCode, currentPhase);

           } else {
               throw new IllegalStateException("Wrong function call");
           }
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
        return currentPhase;
    }


<<<<<<< HEAD
    /**
     * Method: refactoringPhase
=======
    /*************************************************************************
     * Method: enterNextPhase
>>>>>>> 0072d4deb703d115e87998d594179a94f8b1ce03
     * <p>
     * Task: Method that takes the testcode and the normal code and gives it with the
     * currentphase to the compilerManager in the refactor phase.
     *
     * @param testCode String testCode is the whole test the user typed into the textbox
     * @param code  String code is the whole code which is going to be tested
     * @param currentPhase CycleEnum currentPhase gives information to the method with which phase
     *                     the user is currently working. More a failsafe check for the backend
     *                     processes than for the user.
     *
<<<<<<< HEAD
     * @return returns the current phase in which the cycle is at the moment
     */

    public CycleEnum refactoringPhase(String code, String testCode, CycleEnum currentPhase) {
        try {
            if (currentPhase.equals(CycleEnum.REFACTOR)) {
                currentPhase = compManager.compileRefactor(code, testCode, currentPhase);


            } else {
                throw new IllegalStateException("Wrong function call");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return currentPhase;
=======
     * @return void
     *************************************************************************/
    public void enterNextPhase(){
        currentPhaseAsInt++;
        currentPhaseAsInt = currentPhaseAsInt % phaseList.size();
>>>>>>> 0072d4deb703d115e87998d594179a94f8b1ce03
    }








}


