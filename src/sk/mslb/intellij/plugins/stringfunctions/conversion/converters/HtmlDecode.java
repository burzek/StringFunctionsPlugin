package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String ret = htmlString
				.replaceAll("&amp;", "&")
				.replaceAll("&quot;", "\"")
				.replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">");

		Matcher matcher = Pattern.compile(".*(&#\\d+;).*").matcher(ret);
		if (matcher.groupCount() == 1) {


		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new HtmlDecode().convert("abc&amp;abc;&lt;&gt;abc&#65;&#66;&#67;").getResult());
	}

}
