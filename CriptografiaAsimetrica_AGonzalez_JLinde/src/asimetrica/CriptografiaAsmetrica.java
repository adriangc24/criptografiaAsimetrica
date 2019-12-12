package asimetrica;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.util.Base64; 


public class CriptografiaAsmetrica {
	byte[] bytes = Files.readAllBytes(Paths.get("p1"));
	PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
	KeyFactory kf = KeyFactory.getInstance("RSA");
	PrivateKey pvt = kf.generatePrivate(ks);
	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	cipher.init(Cipher.ENCRYPT_MODE, pvt);
	FileInputStream in = new FileInputStream("inFile");
	     FileOutputStream out = new FileOutputStream("outFile");
	    processFile(ci, in, out);
	}
	static private void processFile(Cipher ci,InputStream in,OutputStream out)
		    throws javax.crypto.IllegalBlockSizeException,
		           javax.crypto.BadPaddingException,
		           java.io.IOException
		{
		    byte[] ibuf = new byte[1024];
		    int len;
		    while ((len = in.read(ibuf)) != -1) {
		        byte[] obuf = ci.update(ibuf, 0, len);
		        if ( obuf != null ) out.write(obuf);
		    }
		    byte[] obuf = ci.doFinal();
		    if ( obuf != null ) out.write(obuf);
		}
}