package baic.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class EncodeUtils {

	private static final Logger LOG = Logger.getLogger(EncodeUtils.class);

	public final static String md5(String param) {
		LOG.info("Inside SecurityUtils.md5");
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			return Base64.encodeBase64String(messageDigest.digest(param.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			LOG.error("MD5 encode error: " + e.getMessage());
		}
		return null;
	}
}
