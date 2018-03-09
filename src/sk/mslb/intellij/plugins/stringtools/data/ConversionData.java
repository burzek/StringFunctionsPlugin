package sk.mslb.intellij.plugins.stringtools.data;

import com.intellij.openapi.editor.Editor;

import sk.mslb.intellij.plugins.stringtools.gui.i18n.ResourceKey;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class ConversionData {

	private Editor openedEditor;
	private String originalText;
	private String convertedText;
	private Operation operation;
	private ResourceKey errorMessage;

	public ConversionData(Editor openedEditor, String originalText, String convertedText, Operation operation) {
		this.openedEditor = openedEditor;
		this.originalText = originalText;
		this.convertedText = convertedText;
		this.operation = operation;
	}

	public String getOriginalText() {
		return originalText;
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

	public void setErrorMessage(ResourceKey errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ResourceKey getErrorMessageForInvalidInput() {
		return errorMessage;
	}

	public boolean isInvalidInput() {
		return errorMessage != null;
	}
}
