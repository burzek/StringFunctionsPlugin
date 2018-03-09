package sk.mslb.intellij.plugins.stringtools.gui.i18n;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.java.generate.exception.PluginException;
import com.intellij.ide.plugins.PluginManager;

//import org.apache.commons.lang3.ObjectUtils;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class ResourceKey implements Cloneable {

	public static final ResourceKey WINDOW_TITLE = new ResourceKey("window.title");

	public static final ResourceKey ORIGINAL_TEXT = new ResourceKey("originalText.text.label");
	public static final ResourceKey CONVERTED_TEXT = new ResourceKey("convertedText.text.label");

	public static final ResourceKey CONVERSION_TITLE = new ResourceKey("conversion.title");
	public static final ResourceKey CODING_TITLE = new ResourceKey("coding.title");
	public static final ResourceKey HASH_CRC_TITLE = new ResourceKey("hash-crc.title");

	public static final ResourceKey HEX_TO_STRING_ACTION = new ResourceKey("hex.to.string.action");
	public static final ResourceKey STRING_TO_HEX_ACTION = new ResourceKey("string.to.hex.action");
	public static final ResourceKey BINARY_TO_STRING_ACTION = new ResourceKey("bin.to.string.action");
	public static final ResourceKey STRING_TO_BINARY_ACTION = new ResourceKey("string.to.bin.action");
	public static final ResourceKey OCT_TO_STRING_ACTION = new ResourceKey("oct.to.string.action");
	public static final ResourceKey STRING_TO_OCT_ACTION = new ResourceKey("string.to.oct.action");

	public static final ResourceKey BASE_64_ENCODE_ACTION = new ResourceKey("base64.encode.action");
	public static final ResourceKey BASE_64_DECODE_ACTION = new ResourceKey("base64.decode.action");
	public static final ResourceKey URL_ENCODE_ACTION = new ResourceKey("url.encode.action");
	public static final ResourceKey URL_DECODE_ACTION = new ResourceKey("url.decode.action");
	public static final ResourceKey HTML_ENCODE_ACTION = new ResourceKey("html.encode.action");
	public static final ResourceKey HTML_DECODE_ACTION = new ResourceKey("html.decode.action");
	public static final ResourceKey ROT13_ACTION = new ResourceKey("rot13.action");
	public static final ResourceKey MD5_HASH_ACTION = new ResourceKey("md5.hash.action");
	public static final ResourceKey SHA_256_ACTION = new ResourceKey("sha256.hash.action");
	public static final ResourceKey SHA_512_ACTION = new ResourceKey("sha512.hash.action");
	public static final ResourceKey CRC32_ACTION = new ResourceKey("crc32.action");
	public static final ResourceKey LUHN_DIGIT_GEN_ACTION = new ResourceKey("luhn.digit.generator.action");


	public static final ResourceKey REPLACE_ACTION = new ResourceKey("replace.action");
	public static final ResourceKey COPY_TO_CPB_ACTION = new ResourceKey("copy.to.cpb.action");
	public static final ResourceKey CLOSE_ACTION = new ResourceKey("cancel.action");

	public static final ResourceKey STATUS_DEFAULT_TEXT = new ResourceKey("status.text.default");
	public static final ResourceKey COPIED_TO_CLIPBOARD_STATUS = new ResourceKey("copied.to.clipboard.status");
	public static final ResourceKey REPLACE_DONE_STATUS = new ResourceKey("replace.done.status");
	public static final ResourceKey NO_SELECTION_STATUS = new ResourceKey("no.selection.status");

	public static final ResourceKey ERR_INVALID_LENGTH = new ResourceKey("err.invalid.length");
	public static final ResourceKey ERR_INVALID_INPUT = new ResourceKey("err.invalid.input");
	public static final ResourceKey ERR_INTERNAL = new ResourceKey("err.internal");

	private String resourceKey;
	private Map<String, Object> params = new HashMap<>();

	private ResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String getResourceKey() {
		return resourceKey;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		super.clone();
		ResourceKey rk = new ResourceKey(this.resourceKey);
		rk.params.putAll(this.params);
		return rk;
	}

	public ResourceKey withParam(String paramName, Object param) {
		ResourceKey resourceKey = null;
		try {
			resourceKey = (ResourceKey) this.clone();
		} catch (CloneNotSupportedException e) {
			PluginManager.getLogger().error("Internal fatal error, cannot clone resource " + getResourceKey());
			throw new PluginException("Internal fatal error, cannot clone resource " + getResourceKey(), e);
		}
		resourceKey.params.put(paramName, param);
		return resourceKey;
	}

	public Collection<String> getParameterNames() {
		return Collections.unmodifiableSet(params.keySet());
	}

	public String getParameterValueFor(String parameterName) {
		Object val = params.getOrDefault(parameterName, "???");
		return val == null ? "???" : val.toString();
	}

}
