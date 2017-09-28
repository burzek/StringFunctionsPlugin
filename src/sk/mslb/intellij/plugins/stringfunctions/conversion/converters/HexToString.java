package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import java.util.Arrays;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringfunctions.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class HexToString implements Converter {

	@Override
	public ConversionResult convert(String input)  {
		ConversionResult conversionResult = new ConversionResult().withResult("");
		if (input.length() != 0) {

			if (input.length() % 2 == 0) {
				conversionResult = conversionResult.withError();
			}

			StringBuilder sb = new StringBuilder();
			String tmpStr = input.length() % 2 == 0 ? input : input.substring(0, input.length() - 1);
			Arrays.stream(tmpStr.split("(?<=\\G.{2})")).forEach(s -> sb.append((char) Integer.parseInt(s, 16)));
			conversionResult = conversionResult.withResult(sb.toString());
		}
		return conversionResult;
	}
	
}
