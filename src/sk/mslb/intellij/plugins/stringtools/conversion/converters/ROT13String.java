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
		input.chars().map(c ->  {
			if (c >= 'a' && c <= 'z') {
				return 'a' + (((c - 'a' + 13) % 26));
			} else if (c >= 'A' && c <= 'Z') {
				return 'A' + (((c - 'A' + 13) % 26));
			} else {
				return c;
			}
			}).forEach(c -> rot13.append((char) c));
		return new ConversionResult().withResult(rot13.toString());
	}

}