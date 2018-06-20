/**
 * Copyright 2018, Radiant Solutions, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package util;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class aiding in parsing information out of Java Web Tokens (JWT).
 */
public class GeoAxisJWTUtility {
	private static final Logger log = LoggerFactory.getLogger(GeoAxisJWTUtility.class);
	private static final String PATH_TO_GX_CERT = "GxJWTCertificate.pem";

	/**
	 * Retrieves the JWT header, containing the algorithm name and the token type (must be JWT).
	 * 
	 * @param encodedJWT
	 *            Full Base64 encoded JWT String in the format of header.payload.signature>
	 * @return JSON block for the JWT Header
	 */
	public String getJWTHeader(final String encodedJWT) {
		if (isJWTStructureValid(encodedJWT)) {
			final String encodedJWTHeader = Arrays.asList(encodedJWT.split("\\.")).get(0);
			return new String(Base64.getUrlDecoder().decode(encodedJWTHeader));
		}

		return null;
	}

	/**
	 * Retrieves the JWT header, containing the payload of the token. This contains the "exp" expiration block (epoch
	 * seconds) and the "dn" distinguished name. It may also contain other related user metadata.
	 * 
	 * @param encodedJWT
	 *            Full Base64 encoded JWT String in the format of header.payload.signature>
	 * @return JSON block for the JWT Payload
	 */
	public String getJWTPayload(final String encodedJWT) {
		if (isJWTStructureValid(encodedJWT)) {
			final String encodedJWTPayload = Arrays.asList(encodedJWT.split("\\.")).get(1);
			return new String(Base64.getUrlDecoder().decode(encodedJWTPayload));
		}

		return null;
	}

	/**
	 * Checks the signature block of the JWT token with the local certificate, containing the public key, to verify the
	 * authenticity of the JWT token.
	 * <p>
	 * Currently, this only supports the single algorithm type SHA384withRSA
	 * 
	 * @param encodedJWT
	 *            Full Base64 encoded JWT String in the format of header.payload.signature>
	 * @return True if the signature block matches, false if not.
	 */
	public boolean isJWTValid(final String encodedJWT) {
		if (isJWTStructureValid(encodedJWT)) {
			final String encodedJWTSignature = Arrays.asList(encodedJWT.split("\\.")).get(2);
			final byte[] decodedJWTSignature = Base64.getUrlDecoder().decode(encodedJWTSignature);

			if (isJWTSignatureVerified(decodedJWTSignature)) {
				return true;
			}
		}

		return false;
	}

	private boolean isJWTStructureValid(final String encodedJWT) {
		if (encodedJWT == null || encodedJWT.isEmpty()) {
			log.debug("Provided encodedJWT is null!");
			return false;
		}

		final List<String> encodedJWTParts = Arrays.asList(encodedJWT.split("\\."));
		if (encodedJWTParts.size() != 3) {
			log.debug("Provided encodedJWT is malformed! Should only have 3 parts. Token: {}", encodedJWT);
			return false;
		}

		final String encodedJWTHeader = encodedJWTParts.get(0);
		if (!isStringBase64URLEncoded(encodedJWTHeader)) {
			log.debug("JWT Header is not Base64 URL Encoded! Header: {}", encodedJWTHeader);
			return false;
		}

		final String encodedJWTPayload = encodedJWTParts.get(1);
		if (!isStringBase64URLEncoded(encodedJWTPayload)) {
			log.debug("JWT Payload is not Base64 URL Encoded! Payload: {}", encodedJWTPayload);
			return false;
		}

		return true;
	}

	private boolean isStringBase64URLEncoded(final String candidate) {
		try {
			Base64.getUrlDecoder().decode(candidate);
		} catch (final IllegalArgumentException e) {
			return false;
		}

		return true;
	}

	private boolean isJWTSignatureVerified(final byte[] sigToVerify) {
		try {
			final Signature sig = Signature.getInstance("SHA384withRSA", "SunRsaSign");
			sig.initVerify(loadGeoAxisPublicCertificate());

			return sig.verify(sigToVerify);

		} catch (GeneralSecurityException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return false;
	}

	private PublicKey loadGeoAxisPublicCertificate() throws CertificateException, IOException {
		final CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		final InputStream fileInputStream = this.getClass().getClassLoader().getResource(PATH_TO_GX_CERT).openStream();
		final X509Certificate cer = (X509Certificate) certFactory.generateCertificate(fileInputStream);

		return cer.getPublicKey();
	}
}
