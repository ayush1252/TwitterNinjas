package com.example.ayush.twitterninjas;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.HashMap;

/**
 * Created by ayush on 2/4/17.
 */

public class GetHeader {

    String header;
    OAuthClass oAuthClass=new OAuthClass();
    public GetHeader(String URL, String method, HashMap<String,String> querymap, HashMap<String,String> bodymap) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        header=oAuthClass
                .setConsumersecret("GqGwo5fbAGxhrHFpFwcctuVfK7vWX5PQJD8c16TdslyhNFoDzX")//These 4 should be fetched from Shared Prefrence
                .setTokensecret("RwZWKwcq2i4OcGiU2Eh9MNiXuNN9SYnqg57GvkkDmV33l")
                .setOauth_consumer_key("BkgJ2OWMLen9kBiaCE6zWL1Kd")
                .setOauth_token("1612537346-y5C72VPrTD4VnO8Yw04gsw1XdTgVQvefxascm3A")
                .setOauth_signature_method("HMAC-SHA1")
                .setOauth_version("1.0")

                .setOauth_nonce(randomString())//should be a random string everytime
                .setOauth_timestamp(System.currentTimeMillis()/1000+"") //current epoch time
                .setBody(bodymap) //set to null if there is no request body
                .setQuery(querymap)
                .setBaseurl(URL)
                .setMethod(method)
                .getAuthheader();
    }

    public String randomString(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);

    }
}
