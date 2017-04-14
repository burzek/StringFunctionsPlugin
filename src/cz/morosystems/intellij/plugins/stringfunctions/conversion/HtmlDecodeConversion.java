package cz.morosystems.intellij.plugins.stringfunctions.conversion;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class HtmlDecodeConversion implements Conversion {
	@Override
	public String convert(String input) {
		return htmlUnescape(input);
	}


	private String htmlUnescape(String htmlString) {
		return htmlString
				.replaceAll("&amp;", "&")
				.replaceAll("&quot;", "\"")
				.replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">")
				.replaceAll("&#39;", "'")
				.replaceAll("&#47;", "/");
	}



}
