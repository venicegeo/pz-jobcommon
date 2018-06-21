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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

/**
 * Utility class aiding in parsing information out of Java Web Tokens (JWT).
 */
public class GeoAxisJWTUtility {

	private static final Logger LOG = LoggerFactory.getLogger(GeoAxisJWTUtility.class);

	@Value("${GEOAXIS_JWT_CERT}")
	private String gxJwtCert;

	/**
	 * Retrieves the JWT payload, containing the payload of the token. This contains the "exp" expiration block (epoch
	 * seconds) and the "dn" distinguished name. It may also contain other related user metadata.
	 * 
	 * Checks the signature block of the JWT token with the local certificate, containing the public key, to verify the
	 * authenticity of the JWT token.
	 *
	 * Currently, this only supports the single algorithm type SHA384withRSA
	 * 
	 * @param encodedJWT
	 *            Full Base64 encoded JWT String in the format of header.payload.signature>
	 * @return Jwt wrapper object with JWT Payload in the 'claims' field
	 */
	public Jwt decodeAndVerify(final String encodedJWT) {
		try {
			final RSAPublicKey gxPublicJWTCert = this.loadGeoAxisPublicCertificate();
			final RsaVerifier rsaVerifier = new RsaVerifier(gxPublicJWTCert, "SHA384withRSA");

			return JwtHelper.decodeAndVerify(encodedJWT, rsaVerifier);
		}
		catch (RuntimeException | CertificateException e) {
			LOG.error("Failed to validate JWT: {}", e);
			e.printStackTrace();
		}

		return null;
	}

	private RSAPublicKey loadGeoAxisPublicCertificate() throws CertificateException {

	    final CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
	    final InputStream fileInputStream = new ByteArrayInputStream(gxJwtCert.getBytes());
	    final X509Certificate cer = (X509Certificate)certFactory.generateCertificate(fileInputStream);

	    return (RSAPublicKey) cer.getPublicKey();
	}
}