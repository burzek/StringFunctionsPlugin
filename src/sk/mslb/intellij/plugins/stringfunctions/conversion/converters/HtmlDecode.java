package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringfunctions.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class HtmlDecode implements Converter {
	@Override
	public ConversionResult convert(String input) {
		return new ConversionResult().withResult(htmlUnescape(input));
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
