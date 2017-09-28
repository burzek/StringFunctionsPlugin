package sk.mslb.intellij.plugins.stringfunctions.data;

import com.intellij.openapi.editor.Editor;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class ConversionData {

	private Editor openedEditor;
	private String originalText;
	private String convertedText;
	private Operation operation;
	private boolean invalidInputFlag;

	public ConversionData(Editor openedEditor, String originalText, String convertedText, Operation operation) {
		this.openedEditor = openedEditor;
		this.originalText = originalText;
		this.convertedText = convertedText;
		this.operation = operation;
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public String getConvertedText() {
		return convertedText;
	}

	public void setConvertedText(String convertedText) {
		this.convertedText = convertedText;
	}

	public Operation getOperation() {
		return operation;
	}

	public Editor getOpenedEditor() {
		return openedEditor;
	}

	public void setInvalidInputFlag(boolean invalidInputFlag) {
		this.invalidInputFlag = invalidInputFlag;
	}

	public boolean isInvalidInputFlag() {
		return invalidInputFlag;
	}
}
