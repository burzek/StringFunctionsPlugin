package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import java.nio.charset.Charset;
import java.util.Base64;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringfunctions.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class Base64Encode implements Converter {

	@Override
	public ConversionResult convert(String input) {
		return new ConversionResult().withResult(Base64.getEncoder().encodeToString(input.getBytes(Charset.defaultCharset())));
	}
}