package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import java.nio.charset.Charset;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class StringToHex implements Converter {

	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	@Override
	public ConversionResult convert(String input) {
		return new ConversionResult().withResult(asHex(input.getBytes(Charset.forName("ASCII"))));
	}

	private String asHex(byte[] buf) {
		char[] chars = new char[2 * buf.length];
		for (int i = 0; i < buf.length; ++i) {
			chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
			chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
		}
		return new String(chars);
	}
}
