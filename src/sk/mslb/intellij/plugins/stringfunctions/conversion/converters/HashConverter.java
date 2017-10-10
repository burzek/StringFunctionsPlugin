package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringfunctions.conversion.Converter;

/**
 * @author boris.brinza 10-Oct-2017.
 */
public abstract class HashConverter implements Converter {
	protected enum HASH_TYPE {
		MD5("MD5"), SHA_256("SHA256"), SHA_512("SHA512");

		private String algName;

		HASH_TYPE(String algName) {
			this.algName = algName;
		}
		private String getAlgorithmName() {
			return algName;
		}
	}
	private Map<HASH_TYPE, MessageDigest> digestMap = new HashMap<>();

	@Override
	public final ConversionResult convert(String input) {
		try {
			MessageDigest messageDigest = getMessageDigestInstance();
			byte[] dig = messageDigest.digest(input.getBytes(Charset.defaultCharset()));
			return new ConversionResult().withResult(new String(dig, Charset.defaultCharset()));
		} catch (NoSuchAlgorithmException e) {
			return new ConversionResult().withResult("???").withError();
		}

	}

	private synchronized MessageDigest getMessageDigestInstance() throws NoSuchAlgorithmException {
		MessageDigest messageDigest = digestMap.get(getHashType());
		if (messageDigest == null) {
			messageDigest = MessageDigest.getInstance(getHashType().getAlgorithmName());
			digestMap.put(getHashType(), messageDigest);
		}
		return messageDigest;

	}

	protected abstract HASH_TYPE getHashType();
}
