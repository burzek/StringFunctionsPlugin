package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class ROT13String implements Converter {

	@Override
	public ConversionResult convert(String input) {
		StringBuilder rot13 = new StringBuilder();
		input.chars().map(c -> ((char) ((c + 13) % 'a'))).forEach(rot13::append);
		return new ConversionResult().withResult(rot13.toString());
	}
}