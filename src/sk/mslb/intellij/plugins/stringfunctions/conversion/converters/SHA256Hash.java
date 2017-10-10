package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

/**
 * @author boris.brinza 10-Oct-2017.
 */
public class SHA256Hash extends HashConverter {
	@Override
	protected HASH_TYPE getHashType() {
		return HASH_TYPE.SHA_256;
	}
}
