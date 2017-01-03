package com.links86.trade.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * 主键生成器,我随便找的一个,就是连续取15次随机long,然后算base64值
 * Project name is links86-system
 * Created by xuli on 2016-09-08 14:56
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public class PkIdentifierGenerator implements IdentifierGenerator {
    private static int max = 0;

    @Override
    public Serializable generate(final SessionImplementor arg0,
                                 final Object arg1) throws HibernateException {
        final UUID randomUUID = UUID.randomUUID();
        long most = randomUUID.getMostSignificantBits();
        long least = randomUUID.getLeastSignificantBits();
        final byte[] uuidByte = new byte[16];
        for (int i = 15; i >= 8; i--) {
            uuidByte[i] = (byte) least;
            least >>>= 8;
        }
        for (int i = 7; i >= 0; i--) {
            uuidByte[i] = (byte) most;
            most >>>= 8;
        }

        return Base64.encodeBase64URLSafeString(uuidByte);
    }

//    public static void main(final String[] args) throws IOException {
//        PkIdentifierGenerator pk = new PkIdentifierGenerator();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(pk.generate(null,null));
//            System.out.println(pk.generate(null,null).toString().length());
//        }
//        System.out.println(PkIdentifierGenerator.max);
//    }
}
