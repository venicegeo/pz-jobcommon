package util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

public class GeoAxisJWTUtility {

	private static final Logger LOG = LoggerFactory.getLogger(GeoAxisJWTUtility.class);

	@Value("${GEOAXIS_JWT_CERT}")
	private String gxJwtCert;

	public Jwt decodeAndVerify(final String encodedJWT) {
		try {
			final RSAPublicKey gxPublicJWTCert = this.loadGeoAxisPublicCertificate();
			final RsaVerifier rsaVerifier = new RsaVerifier(gxPublicJWTCert, "SHA384withRSA");

			return JwtHelper.decodeAndVerify(encodedJWT, rsaVerifier);
		}
		catch (CertificateException | IOException e) {
			LOG.error("Failed to validate JWT: {}", e);
			e.printStackTrace();
		}

		return null;
	}

	private RSAPublicKey loadGeoAxisPublicCertificate() throws CertificateException, IOException {

	    final CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
	    final InputStream fileInputStream = new ByteArrayInputStream(gxJwtCert.getBytes("UTF-8"));
	    final X509Certificate cer = (X509Certificate)certFactory.generateCertificate(fileInputStream);

	    return (RSAPublicKey) cer.getPublicKey();
	}
}