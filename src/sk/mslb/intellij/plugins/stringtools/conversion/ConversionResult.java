package sk.mslb.intellij.plugins.stringtools.conversion;

/**
 * @author boris.brinza 28-Sep-2017.
 */
public class ConversionResult {

	private String result;
	private boolean errorFlag;

	public ConversionResult withError() {
		errorFlag = true;
		return this;
	}

	public ConversionResult withResult(String result) {
		this.result = result;
		return this;
	}

	public String getResult() {
		return result;
	}

	public boolean isError() {
		return errorFlag;
	}
}
