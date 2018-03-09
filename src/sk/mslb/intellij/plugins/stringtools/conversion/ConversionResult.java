package sk.mslb.intellij.plugins.stringtools.conversion;

import sk.mslb.intellij.plugins.stringtools.gui.i18n.ResourceKey;

/**
 * @author boris.brinza 28-Sep-2017.
 */
public class ConversionResult {

	private String result;
	private ResourceKey errorResourceKey;

	public ConversionResult withError(ResourceKey errorResourceKey) {
		this.errorResourceKey = errorResourceKey;
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
		return errorResourceKey != null;
	}

	public ResourceKey getErrorResourceKey() {
		return errorResourceKey;
	}

}
