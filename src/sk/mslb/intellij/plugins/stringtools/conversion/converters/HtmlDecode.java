package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class HtmlDecode implements Converter {
	@Override
	public ConversionResult convert(String input) {
		return new ConversionResult().withResult(htmlUnescape(input));
	}

	private String htmlUnescape(String htmlString) {
		Matcher matcher = Pattern.compile("(&#\\d+;|&amp;|&quot;|&lt;|&gt;)").matcher(htmlString);
		while (matcher.find()) {
			String element = matcher.group(1);
			String replacement = null;
			switch (element) {
				case "&amp;":
					replacement = "&";
					break;
				case "&quot;":
					replacement = "'";
					break;
				case "&lt;":
					replacement = "<";
					break;
				case "&gt;":
					replacement = ">";
					break;
				default:
					replacement = String.valueOf((char) (Integer.parseInt(element.substring(2, element.length() - 1))));
			}
			htmlString = htmlString.replaceAll(element, replacement);
		}
		return htmlString;
	}

}
