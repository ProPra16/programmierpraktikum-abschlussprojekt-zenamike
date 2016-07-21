package de.hhu.propra.tddt.compiler;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestFailure;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import de.hhu.propra.tddt.informationcore.InformationCore;

/**
 * Created by schiggy on 10.07.16.
 */
public class CompileResults {

	/*********************************************************
	 * Class: CompileResults
	 * <p>
	 * Task: Saves the information the internal Compiler produces into an
	 * LinkedList from type String, so that the GUI can work with the errors
	 * thrown by the Internal compiler. *
	 * 
	 * @author Kevin
	 ********************************************************/

	LinkedList<String> testResults = new LinkedList<>();
	LinkedList<String> compileResults = new LinkedList<>();
	static String errorMessage;

	String failedTests = null;
	String ignoredTests = null;
	String successfulTests = null;
	String testDuration = null;
	String compileDuration = null;
	String compileMessage = null;

	/**
	 * Method: addCompileErrors
	 * <p>
	 * Task: Method that saves the compileerrors in one convinent linked list
	 *
	 * @param compileErrors
	 *            A preprade string from Cycle.java which is nicely formated
	 *
	 * @return void
	 */

	protected void addCompileErrors(String compileErrors) {
		compileResults.add(compileErrors);
	}

	/**
	 * Method: getCompilerErrors
	 * <p>
	 * Task: Method that makes the LinkedList from setCompileErrors accessable
	 *
	 *
	 * @return LinkedList compilerErrorList
	 */

	public LinkedList<String> getCompilerErrors() {
		return compileResults;
	}

	/**
	 * Method: setTestResults
	 * <p>
	 * Task: Method that saves the errors/information from the internal compiler
	 * into an ArrayList.
	 *
	 * @param compiler
	 *            so that method can access the information
	 *
	 * @return void
	 */

	protected void setTestResults(JavaStringCompiler compiler) {
		CompilerResult compilerResult = compiler.getCompilerResult();
		testResults = new LinkedList<>();
		if (!compilerResult.hasCompileErrors()) {
			int failedTestsNumber = compiler.getTestResult().getNumberOfFailedTests();
			failedTests = Integer.toString(failedTestsNumber);
			int ignoredTestsNumber = compiler.getTestResult().getNumberOfIgnoredTests();
			ignoredTests = Integer.toString(ignoredTestsNumber);
			int successfulTestsNumber = compiler.getTestResult().getNumberOfSuccessfulTests();
			successfulTests = Integer.toString(successfulTestsNumber);
			Duration testDurationTime = compiler.getTestResult().getTestDuration();

			testResults.add(successfulTests);
			testResults.add(ignoredTests);
			testResults.add(failedTests);
		} else {
			testResults.add("");
			testResults.add("");
			testResults.add("");
		}
	}

	/**
	 * Method: getTestResults
	 * <p>
	 * Task: Method that makes the information from setTestResults accessable.
	 * **important** for the GUI
	 *
	 *
	 *
	 * @return ArrayList<String> testResults in this order: failedTests
	 *         ignoredTests successfulTests testDuration testMessage
	 */

	public LinkedList<String> getTestResults() {

		return testResults;
	}

	/**
	 * Method: setCompileResults
	 * <p>
	 * Task: Method that saves the errors/information from the internal compiler
	 * into an ArrayList.
	 *
	 * @param compiler
	 *            so that method can access the information
	 * @param compilationUnit
	 *            is needed for the CompileError collection in order to specify
	 *            with CompilationUnit should be looked at.
	 *
	 * @return void
	 */

	protected void setCodeResults(JavaStringCompiler compiler, CompilationUnit compilationUnit) {
		Duration compileDurationTime = compiler.getCompilerResult().getCompileDuration();
		compileDuration = Long.toString(compileDurationTime.getSeconds());
		// Collection<CompileError> compileMessages =
		// compiler.getCompilerResult().getCompilerErrorsForCompilationUnit(compilationUnit);
		/*
		 * if (!compileMessages.isEmpty()) { String arr[] = new
		 * String[compileMessages.size()]; compileMessages.toArray(arr);
		 * compileMessage = Arrays.toString(arr); }
		 */
		// compileResults.add(compileDuration);
		// compileResults.add(compileMessage);
	}

	/**
	 * Method: getCompileResults
	 * <p>
	 * Task: Method that makes the information from setTestResults accessable.
	 * **important** for the GUI
	 *
	 *
	 *
	 * @return LinkedList<String> compileResults in this order: compileErrors
	 *         compileDuration compileMessage
	 */

	public LinkedList<String> getCompileResults() {
		return compileResults;
	}

	/****
	 * Method: errorStringInit
	 * <p>
	 * Task: gets the compile errors and formats them in a readable way
	 *
	 *
	 * @return void
	 */

	protected void errorStringInit(JavaStringCompiler compiler, CompilationUnit cu) {
		String errorString = "";
		for (CompileError compileError : compiler.getCompilerResult().getCompilerErrorsForCompilationUnit(cu)) {
			errorString = "Line " + compileError.getLineNumber() + ": " + compileError.getMessage() +
					": \n " + compileError.getCodeLineContainingTheError() + "\n" +
					compileError.getMessage() + "\n";
			addCompileErrors(errorString);
		}
	}
}
