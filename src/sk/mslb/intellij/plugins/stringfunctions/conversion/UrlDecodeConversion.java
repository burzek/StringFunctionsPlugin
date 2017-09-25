package sk.mslb.intellij.plugins.stringfunctions.conversion;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class UrlDecodeConversion implements Conversion {
	@Override
	public String convert(String input) {
		return new String(Base64.getUrlDecoder().decode(input), Charset.defaultCharset());
	}
}
