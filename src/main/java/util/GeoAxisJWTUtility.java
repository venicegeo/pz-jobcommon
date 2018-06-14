package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JWT Format:
 *  { “token": “< base64_encoded_header>.< base64_encoded_payload >.<RSASHA384 (<base64_encoded_header> + '.' +<base64_encoded_payload>, privateKey)>”}
 * 
 */
public class GeoAxisJWTUtility {

	private static final Logger log = LoggerFactory.getLogger(GeoAxisJWTUtility.class);

	private static final String PATH_TO_GX_CERT = "file://TBD";

	public String getJWTHeader(final String encodedJWT) {

		if( isJWTStructureValid(encodedJWT) ) {
			final String encodedJWTHeader = Arrays.asList(encodedJWT.split("\\.")).get(0);
			return new String(Base64.getUrlDecoder().decode(encodedJWTHeader));
		}

		return null;
	}

	public String getJWTPayload(final String encodedJWT) {

		if( isJWTStructureValid(encodedJWT) ) {
			final String encodedJWTPayload = Arrays.asList(encodedJWT.split("\\.")).get(1);
			return new String(Base64.getUrlDecoder().decode(encodedJWTPayload));
		}

		return null;
	}

	public boolean isJWTValid(final String encodedJWT) {

		if( isJWTStructureValid(encodedJWT) ) {
			final String jwtSignature = Arrays.asList(encodedJWT.split("\\.")).get(2);

			if( isJWTSignatureVerified(jwtSignature) ) {
				return true;
			}
		}

		return false;
	}

	private boolean isJWTStructureValid(final String encodedJWT) {

		if( encodedJWT == null || encodedJWT.isEmpty()) {
			log.debug("Provided encodedJWT is null!");
			return false;
		}

		final List<String> encodedJWTParts = Arrays.asList(encodedJWT.split("\\."));
		if( encodedJWTParts.size() != 3 ) {
			log.debug("Provided encodedJWT is malformed! Should only have 3 parts. Token: {}", encodedJWT);
			return false;
		}

		final String encodedJWTHeader = encodedJWTParts.get(0);
		if( !isStringBase64URLEncoded(encodedJWTHeader) ) {
			log.debug("JWT Header is not Base64 URL Encoded! Header: {}", encodedJWTHeader);
			return false;
		}

		final String encodedJWTPayload = encodedJWTParts.get(1);
		if( !isStringBase64URLEncoded(encodedJWTPayload) ) {
			log.debug("JWT Payload is not Base64 URL Encoded! Payload: {}", encodedJWTPayload);
			return false;
		}

		return true;
	}

	private boolean isStringBase64URLEncoded(final String candidate) {
		try {
			Base64.getUrlDecoder().decode(candidate);
		}
		catch( final IllegalArgumentException e ) {
			return false;
		}

		return true;
	}

	private boolean isJWTSignatureVerified(final String sigToVerify) {

		try {
			final Signature sig = Signature.getInstance("SHA384withRSA", "SunRsaSign");
			sig.initVerify(loadGeoAxisPublicCertificate());

			return sig.verify(sigToVerify.getBytes());

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private PublicKey loadGeoAxisPublicCertificate() throws CertificateException, FileNotFoundException {

	    final CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
	    final FileInputStream fileInputStream = new FileInputStream(PATH_TO_GX_CERT);
	    final X509Certificate cer = (X509Certificate)certFactory.generateCertificate(fileInputStream);

	    return cer.getPublicKey();
	}

	public static void main(String[] args) {

		final String encodedJWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzM4NCJ9.eyANCiAgICJpc3MiOiAiaHR0cHM6Ly9neGlzYWNjZXNzLmNpLmd4YXdzLmRldiIsIA0KICAgImV4cCI6ICIxNTI4OTI1MjE5IiwgDQogICAic3ViIjogIkNOPUhhbWxpbiBKZWZmcmV5IEEgZ3hqeGgwMSwgT1U9UGVvcGxlLCBPPVUuUy4gR292ZXJubWVudCwgQz1VUyIsDQogICAiZG4iOiAiY249aGFtbGluIGplZmZyZXkgYSBneGp4aDAxLG91PXBlb3BsZSxvdT1uZ2Esb3U9ZG9kLG89dS5zLiBnb3Zlcm5tZW50LGM9dXMiLA0KICAgImNvb2tpZSI6ICJWRVJTSU9OXzV-Y25OQ3JTdUc1SFh1SU5BSi9PSlJhQT09fjVFZHp1ckg5TmV3RVVVaWxuTThLekxsWC9CTER4TFg5dGpwZ2MvdU9SM1ZZbUxoaVNIN0FHN3NlKzh0S1MrdDhna3A2cjltMXN3UjY3MllOa3JYRUMzMUJrMkxuU0QyZ2IwYXpzeC9odWpnVFJCRDRmdERPamlBdnZaRHZZOVRjbUZtTGV1T2lnMDArSmFtT0orMlVQMFRFRFp4UmRETzNzQVFsOEhwTUUzVVc0MjdBcFdRRTltVzNnMXRGVUpzQkppZElWUzV1d2QrZGpJWUo5TW5acHdhRzBnY3FEQWgxVjZXZGg4L0YvTzMxc0o5enpLMHNxY0R1bUxKbm5xcktNc2c4Y0FMamR4aTZrL0ltRWxsTVBLZkZyRWNLcVEraS9ZV29xS1BpUWcwZFNhaEFaMlJpbzBWc3libXEwQWRzQXh4cG1VeXZOeDNHajQraXFqUG9uSHVna2NJbzQ4YnRuSzlpeUFkVEJXNXBjUnprbks2cmFwQk93aHdzYmZhRlg2QXhQT2x6QXZIUG5ha3ZMWmp3bEtqdXdIMUpaYUNGT0Q3R0ZHQi9SWi9sTFUzR0FqWXZpMk9WSStzSFhaTHdPd1VIMTFTU0RQRGNkT21laE9SRWdRRzZzQnRDMU93TXdhVmN5RTlSMTVRPSINCn0.g5ljBeU9mnwjvaVDWbPAvUk_YrCWrgsy-x9t6ek06CiplIStZqMrAEvcpJejKp8rCcCPmhnOUUXT2MT5m457b9fKqL3PYuqLCPP6ggO1e2OplsMV1OoogCCEjLTrZnv7OUHjZkdwG_9XXfxjB1vZgSj17Be0HIg18sfs9GdTOPoeLXK9PTp69X51-0wAoGl3RTIxy-WeBqHIpCR_vb2mcN9a8H5XXKzjF5VJjkOC3gbTUK4AGzBO6fDQ3UdjajXCXqIH8lmTs_TSpVASdxWm5D0ir_wQoN3ZZ6Jq7KS644eoeRp4kuyWwimWyO-_BDwRf9Ah4PkfUScz3T50jMbHuw";

		final GeoAxisJWTUtility gaJWTUtil = new GeoAxisJWTUtility();
		System.out.println("Header: " + gaJWTUtil.getJWTHeader(encodedJWT));
		System.out.println("Payload: " + gaJWTUtil.getJWTPayload(encodedJWT));
		System.out.println("IsValid: " + gaJWTUtil.isJWTValid(encodedJWT));
	}
}
