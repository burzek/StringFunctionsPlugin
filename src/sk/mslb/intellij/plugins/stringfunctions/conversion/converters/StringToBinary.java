package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringfunctions.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class StringToBinary implements Converter {

	@Override
	public ConversionResult convert(String input) {
		return new ConversionResult().withResult(asBinary(input));
	}

	private String asBinary(String input) {
		StringBuilder str = new StringBuilder();
		for (char c : input.toCharArray()) {
			for (int bit = 7; bit >= 0; bit--) {
				byte tstBit = (byte) (1 << bit);
				str.append((c & tstBit) == 0 ? '0' : '1');
			}
		}
		return str.toString();
	}
}