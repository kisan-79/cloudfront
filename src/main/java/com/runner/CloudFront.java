package com.runner;

import com.amazonaws.services.cloudfront.CloudFrontUrlSigner;
import com.amazonaws.services.cloudfront.util.SignerUtils.Protocol;
import java.io.File;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

public class CloudFront {

    public static void main(String[] args) throws IOException, InvalidKeySpecException {
        Protocol protocol = Protocol.https;
        String distributionDomain = "d1ymlw8kq11tcy.cloudfront.net";
        String s3ObjectKey = "private/msd.jpg";
        //all 3 creates cloudfront distribution private url i.e. https://d1ymlw8kq11tcy.cloudfront.net/private/msd.jpg

        File privateKeyFile = new File("/home/kisan/intelj-workspace/cloudfront/cloudfront.pem"); // private key corresponding to public key attached to distribution
        String keyPairId = "KW1PC63HP23SN"; // id of public key attached to distribution


        long fiveMinutesInMillis = 5 * 60 * 1000; // 5 minutes in milliseconds
        Date expirationTime = new Date(System.currentTimeMillis() + fiveMinutesInMillis);


        String url1 = CloudFrontUrlSigner.getSignedURLWithCannedPolicy(protocol, distributionDomain, privateKeyFile, s3ObjectKey, keyPairId, expirationTime);

        System.out.println("Signed url for Cloudfront cdn is - " +url1);
    }
}