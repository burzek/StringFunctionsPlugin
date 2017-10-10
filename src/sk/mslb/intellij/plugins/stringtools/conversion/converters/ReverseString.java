package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class ReverseString implements Converter {

	@Override
	public ConversionResult convert(String input) {
		return new ConversionResult().withResult(new StringBuilder(input).reverse().toString());
	}
}