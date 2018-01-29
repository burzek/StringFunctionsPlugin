package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;
import sk.mslb.intellij.plugins.stringtools.gui.i18n.ResourceKey;

/**
 * @author boris.brinza 09-Oct-2017.
 */
public abstract class NumBaseToString implements Converter {

	@Override
	public final ConversionResult convert(String input) {
		ConversionResult conversionResult = new ConversionResult().withResult("");
		if (input.length() != 0) {
			int base = getBase();
			int numDigits = getNumberDigits();

			if (input.length() % numDigits != 0) {
				conversionResult = conversionResult.withError(ResourceKey.ERR_INVALID_LENGTH.withParam("reqLen", numDigits));
			}

			StringBuilder sb = new StringBuilder();
			String tmpStr = input.length() % numDigits == 0 ? input : input.substring(0, input.length() - 1);
			String digitSplitter = "(?<=\\G.{" + numDigits + "})";
			for (String split : tmpStr.split(digitSplitter)) {
				if (!isValid(split)) {
					conversionResult = conversionResult.withError(ResourceKey.ERR_INVALID_INPUT);
					sb.append("?");
				} else {
					sb.append((char) Integer.parseInt(split, base));
				}
			}
			conversionResult = conversionResult.withResult(sb.toString());
		}
		return conversionResult;
	}

	private boolean isValid(String digit) {
		return digit.length() == getNumberDigits() &&
				digit.chars().allMatch(c -> Character.digit(c, getBase()) != -1);
	}


	protected abstract int getBase();
	protected abstract int getNumberDigits();


}
