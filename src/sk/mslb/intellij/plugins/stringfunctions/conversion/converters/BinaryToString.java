package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import java.util.Arrays;

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
			Arrays.stream(tmpStr.split("(?<=\\G.{8})")).forEach(s -> sb.append((char) Integer.parseInt(s, 2)));
			conversionResult = conversionResult.withResult(sb.toString());
		}

		return conversionResult;
	}
}
