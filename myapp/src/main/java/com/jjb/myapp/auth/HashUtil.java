package com.jjb.myapp.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class HashUtil {
    public String createHash(String cipherText) {
        // 'hashToString' : Salt와 함깨 해시를 생성
        return BCrypt.withDefaults().hashToString(12, cipherText.toCharArray());
    }
}
