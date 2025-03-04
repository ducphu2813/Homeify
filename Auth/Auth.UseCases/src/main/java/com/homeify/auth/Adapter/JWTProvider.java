package com.homeify.auth.Adapter;

import javax.crypto.SecretKey;
import java.util.List;

public interface JWTProvider {

    String generateToken(String username, List<String> roles);

    SecretKey getKey();

}
