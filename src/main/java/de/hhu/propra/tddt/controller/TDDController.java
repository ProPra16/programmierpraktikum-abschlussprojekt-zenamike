package de.hhu.propra.tddt.controller;

import de.hhu.propra.tddt.compiler.CompilerManager;
import de.hhu.propra.tddt.contentmanager.TextManager;
import de.hhu.propra.tddt.cycle.CycleEnum;
import de.hhu.propra.tddt.informationcore.InformationCore;
import de.hhu.propra.tddt.loader.StartLoader;
import de.hhu.propra.tddt.plugin.PluginLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Created by MichaelLiske on 09.07.16
 */

/**
 * Class: handles the scenes' buttons (namely back and compile button)
 */

public class TDDController implements Initializable {

	private String code = TaskListController.classCode;
	private String test = TaskListController.testCode;

	@FXML
	private Button Back;
	@FXML
	private Button Compile;
	@FXML
	public TextArea codeArea;
	@FXML
	public TextArea testArea;
	boolean compiliert;

	public void setCode(String codeInput) {
		code = codeInput;
		codeArea.setText(code);
	}

	public void setTest(String testInput) {
		test = testInput;
		testArea.setText(test);
	}

	@FXML
	private Label PhaseLabel;

	@FXML
	public void handleBackButton(ActionEvent actionEvent) throws IOException {
		switch (InformationCore.informationCore().getCycleManager().getCurrentPhase()) {
		case TEST: {
			new StartLoader(StartLoader.getWindow());
			stopPluginCatchExeption();
			break;
		}
		case CODE: {
			einePhaseZurück();
			PhaseLabel.setText(CurrentPhase());
			testArea.setEditable(true);
			codeArea.setEditable(false);
			stopAndStartPlugins();
			break;
		}
		case REFACTOR: {
			einePhaseZurück();
			PhaseLabel.setText(CurrentPhase());
			testArea.setEditable(false);
			codeArea.setEditable(true);
			stopAndStartPlugins();
			break;
		}
		}
		System.out.println("Back");
	}

	@FXML
	public void handleCompileButton(ActionEvent actionEvent) {

		System.out.println("\n\nCompilation Nr: "
				+ InformationCore.informationCore().getCompileManager().getCompilationNumber() + "\n");
		switch (InformationCore.informationCore().getCycleManager().getCurrentPhase()) {
		case TEST:
			compiliert = false;
			compilierenTest();
			CompileResultsPrüfenMitEinemFail();
			ResultsAusgeben();

			if (!compiliert) {
				uiUpdateForCode();
				InformationCore.informationCore().getCycleManager().nextPhase();
			}
			testPhaseCycle();
			PhaseLabel.setText(CurrentPhase());
			break;

		case CODE:

			// zum überprüfen ob compiliert
			compiliert = false;

			// Test Compilieren
			compilierenTest();
			CompileResultsPrüfenVonTest();
			ResultsAusgeben();

			if (compiliert) {
				uiUpdateForRefactor();
				InformationCore.informationCore().getCycleManager().nextPhase();
			}
			testPhaseCycle();
			PhaseLabel.setText(CurrentPhase());

			break;

		case REFACTOR:

			System.out.println("Compiling in REFACTOR");

			// zum überprüfen ob compiliert
			compiliert = false;

			// Code Compilieren
			compilierenCode();
			CompileResultsPrüfenVonTest();
			ResultsAusgeben();

			if (compiliert) {
				uiUpdateForTest();
				InformationCore.informationCore().getCycleManager().nextPhase();
			}
			testPhaseCycle();
			PhaseLabel.setText(CurrentPhase());
			break;
		}

	}

	public void CompileResultsPrüfenMitEinemFail() {
		System.out.println("Test compiliert");
		CompilerManager compileManager = InformationCore.informationCore().getCompileManager();
		LinkedList<LinkedList<String>> theList = compileManager.getCompileResultList();
		LinkedList<String> elementList = theList.get(0);
		if (elementList.get(0).isEmpty() || elementList.get(2).equals("1")) {
			compiliert = false;
		} else {
			compiliert = true;
		}
	}

	public void CompileResultsPrüfenVonTest() {
		LinkedList<LinkedList<String>> compileResultList = InformationCore.informationCore().getCompileManager()
				.getCompileResultList();
		if (compileResultList.get(0).get(2)
				.equals("0")) {
			compiliert = true;
		} else {
			compiliert = false;
		}
	}

	public void uiUpdateForCode() {
		stopAndStartPlugins();
		codeArea.setEditable(true);
		testArea.setEditable(false);
	}

	public void uiUpdateForRefactor() {
		stopAndStartPlugins();
		codeArea.setEditable(true);
		testArea.setEditable(true);
	}

	public void uiUpdateForTest() {
		stopAndStartPlugins();
		PhaseLabel.setText(CurrentPhase());
		codeArea.setEditable(false);
		testArea.setEditable(true);
	}

	public void compilierenTest() {
		String testCode = InformationCore.informationCore().getTestManager().getText();
		String code = InformationCore.informationCore().getCodeManager().getText();
		InformationCore.informationCore().getCompileManager().compileTest(code, testCode);
	}

	public void compilierenCode() {
		compilierenTest();
	}

	public void ResultsAusgeben() {
		System.out.println("CompilerResultList.get0");
		System.out.println(InformationCore.informationCore().getCompileManager().getCompileResultList().get(0));
		System.out.println();

	}

	public void einePhaseZurück() {
		InformationCore.informationCore().getCycleManager().nextPhase();
		InformationCore.informationCore().getCycleManager().nextPhase();
	}

	public void stopAndStartPlugins() {
		PluginLoader.pluginLoader().stopAllPlugins();
		PluginLoader.pluginLoader().startAllPlugins();
	}

	public String CurrentPhase() {
		return InformationCore.informationCore().getCycleManager().getCurrentPhase().toString();
	}

	public void stopPluginCatchExeption() {
		try {
			PluginLoader.pluginLoader().stopAllPlugins();
		} catch (IllegalStateException e) {
		}
	}

	public void testPhaseCycle() {
		System.out.println("++++++++++++++");
		System.out.println(CurrentPhase());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		InformationCore.informationCore().setCodeManager(new TextManager(codeArea));
		InformationCore.informationCore().setTestManager(new TextManager(testArea));
		PluginLoader.pluginLoader().startAllPlugins();

		codeArea.setEditable(false);
		PhaseLabel.setText(CurrentPhase());

	}
}