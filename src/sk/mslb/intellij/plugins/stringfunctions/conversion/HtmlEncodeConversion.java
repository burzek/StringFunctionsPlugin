package sk.mslb.intellij.plugins.stringfunctions.conversion;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class HtmlEncodeConversion implements Conversion {
	@Override
	public String convert(String input) {
		return htmlEscape(input, true);
	}


	private String htmlEscape(String str, boolean avoidDoubleEscape) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length() + 16);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
				case '&':
					// Avoid double escaping if already escaped
					if (avoidDoubleEscape && (isHtmlCharEntityRef(str, i))) {
						sb.append(c);
					} else {
						sb.append("&amp;");
					}
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\'':
					sb.append("&#39;");
					break;
				case '/':
					sb.append("&#47;");
					break;
				default:
					sb.append(c);
			}
		}
		return sb.toString();
	}

	private boolean isHtmlCharEntityRef(String str, int index) {
		if (str.charAt(index) != '&') {
			return false;
		}
		int indexOfSemicolon = str.indexOf(';', index + 1);
		if (indexOfSemicolon == -1) { // is there a semicolon sometime later ?
			return false;
		}
		if (!(indexOfSemicolon > (index + 2))) {   // is the string actually long enough
			return false;
		}
		if (followingCharsAre(str, index, "amp;")
				|| followingCharsAre(str, index, "lt;")
				|| followingCharsAre(str, index, "gt;")
				|| followingCharsAre(str, index, "quot;")) {
			return true;
		}
		if (str.charAt(index + 1) == '#') {
			if (str.charAt(index + 2) == 'x' || str.charAt(index + 2) == 'X') {
				// It's presumably a hex value
				if (str.charAt(index + 3) == ';') {
					return false;
				}
				for (int i = index + 3; i < indexOfSemicolon; i++) {
					char c = str.charAt(i);
					if (c >= 48 && c <= 57) {  // 0 -- 9
						continue;
					}
					if (c >= 65 && c <= 70) {   // A -- F
						continue;
					}
					if (c >= 97 && c <= 102) {   // a -- f
						continue;
					}
					return false;
				}
				return true;   // yes, the value is a hex string
			} else {
				// It's presumably a decimal value
				for (int i = index + 2; i < indexOfSemicolon; i++) {
					char c = str.charAt(i);
					if (c >= 48 && c <= 57) {  // 0 -- 9
						continue;
					}
					return false;
				}
				return true; // yes, the value is decimal
			}
		}
		return false;
	}


	private boolean followingCharsAre(String str, int startIndex, String nextChars) {
		return (str.indexOf(nextChars, startIndex + 1) == (startIndex + 1));
	}
}
