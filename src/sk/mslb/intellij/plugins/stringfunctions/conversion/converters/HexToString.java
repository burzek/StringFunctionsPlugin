package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringfunctions.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class HexToString implements Converter {

	@Override
	public ConversionResult convert(String input) {
		ConversionResult conversionResult = new ConversionResult().withResult("");
		if (input.length() != 0) {

			if (input.length() % 2 != 0) {
				conversionResult = conversionResult.withError();
			}

			StringBuilder sb = new StringBuilder();
			String tmpStr = input.length() % 2 == 0 ? input : input.substring(0, input.length() - 1);
			for (String split : tmpStr.split("(?<=\\G.{2})")) {
				if (!isValidHex(split)) {
					conversionResult = conversionResult.withError();
					sb.append("?");
				} else {
					sb.append((char) Integer.parseInt(split, 16));
				}
			}
			conversionResult = conversionResult.withResult(sb.toString());
		}
		return conversionResult;
	}

	private boolean isValidHex(String str) {
		return str.length() == 2 &&
				Character.digit(str.charAt(0), 16) != -1 &&
				Character.digit(str.charAt(1), 16) != -1;
	}

}
