package sk.mslb.intellij.plugins.stringfunctions.gui;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class ResourceKeys {

	public static final ResourceKeys WINDOW_TITLE = new ResourceKeys("window.title");

	public static final ResourceKeys ORIGINAL_TEXT = new ResourceKeys("originalText.text.label");
	public static final ResourceKeys CONVERTED_TEXT = new ResourceKeys("convertedText.text.label");

	public static final ResourceKeys CONVERSION_TITLE = new ResourceKeys("conversion.title");
	public static final ResourceKeys CODING_TITLE = new ResourceKeys("coding.title");

	public static final ResourceKeys HEX_TO_STRING_ACTION = new ResourceKeys("hex.to.string.action");
	public static final ResourceKeys STRING_TO_HEX_ACTION = new ResourceKeys("string.to.hex.action");
	public static final ResourceKeys BINARY_TO_STRING_ACTION = new ResourceKeys("bin.to.string.action");
	public static final ResourceKeys STRING_TO_BINARY_ACTION = new ResourceKeys("string.to.bin.action");
	public static final ResourceKeys BASE_64_ENCODE_ACTION = new ResourceKeys("base64.encode.action");
	public static final ResourceKeys BASE_64_DECODE_ACTION = new ResourceKeys("base64.decode.action");
	public static final ResourceKeys URL_ENCODE_ACTION = new ResourceKeys("url.encode.action");
	public static final ResourceKeys URL_DECODE_ACTION = new ResourceKeys("url.decode.action");
	public static final ResourceKeys HTML_ENCODE_ACTION = new ResourceKeys("html.encode.action");
	public static final ResourceKeys HTML_DECODE_ACTION = new ResourceKeys("html.decode.action");



	public static final ResourceKeys CONVERT_ACTION = new ResourceKeys("convert.action");
	public static final ResourceKeys REPLACE_ACTION = new ResourceKeys("replace.action");
	public static final ResourceKeys COPY_TO_CPB_ACTION = new ResourceKeys("copy.to.cpb.action");
	public static final ResourceKeys CLOSE_ACTION = new ResourceKeys("cancel.action");

	private String resourceKey;

	private ResourceKeys(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String getResourceKey() {
		return resourceKey;
	}

}
