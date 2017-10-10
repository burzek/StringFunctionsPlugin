package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import java.nio.charset.Charset;
import java.util.Base64;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class UrlEncode implements Converter {

	@Override
	public ConversionResult convert(String input) {
		return new ConversionResult().withResult(Base64.getUrlEncoder().encodeToString(input.getBytes(Charset.defaultCharset())));
	}
}
