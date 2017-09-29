package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringfunctions.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class BinaryToString implements Converter {

	@Override
	public ConversionResult convert(String input) {
		ConversionResult conversionResult = new ConversionResult().withResult("");
		if (input.length() != 0) {
			if (input.length() % 8 != 0) {
				conversionResult = conversionResult.withError();
			}

			String tmpStr = input.length() % 8 == 0 ? input : input.substring(0, 8 * (input.length() / 8));
			StringBuilder sb = new StringBuilder();
			for (String str : tmpStr.split("(?<=\\G.{8})")) {
				if (!isValidBinary(str)) {
					conversionResult = conversionResult.withError();
					sb.append("?");
				} else {
					sb.append((char) Integer.parseInt(str, 2));
				}
			}
			conversionResult = conversionResult.withResult(sb.toString());
		}

		return conversionResult;
	}

	private boolean isValidBinary(String str) {
		return str.length() == 8 &&
				str.chars().allMatch(c -> c == '0' || c == '1');
	}
}
