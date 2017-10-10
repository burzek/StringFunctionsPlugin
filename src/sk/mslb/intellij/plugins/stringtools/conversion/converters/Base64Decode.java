package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import java.nio.charset.Charset;
import java.util.Base64;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class Base64Decode implements Converter {
	@Override
	public ConversionResult convert(String input) {
		try {
			return new ConversionResult().withResult(new String(Base64.getDecoder().decode(input), Charset.defaultCharset()));
		} catch (IllegalArgumentException e) {
			return new ConversionResult().withResult("???").withError();
		}
	}
}
