package cz.morosystems.intellij.plugins.stringfunctions.data;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class Document {

	private String input;
	private String output;
	private Operation operation;

	public Document(String input, String output, Operation operation) {
		this.input = input;
		this.output = output;
		this.operation = operation;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public Operation getOperation() {
		return operation;
	}

}
