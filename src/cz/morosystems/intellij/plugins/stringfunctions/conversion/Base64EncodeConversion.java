package cz.morosystems.intellij.plugins.stringfunctions.conversion;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class Base64EncodeConversion implements Conversion {

	@Override
	public String convert(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes(Charset.defaultCharset()));
	}
}