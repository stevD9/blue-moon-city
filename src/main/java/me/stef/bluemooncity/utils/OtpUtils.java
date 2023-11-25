package me.stef.bluemooncity.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OtpUtils {

    private OtpUtils() {}

    public static String encodeOtp(int playerId, String token) {
        String otp = playerId + ":" + token;
        return Base64.getEncoder().encodeToString(otp.getBytes(StandardCharsets.UTF_8));
    }

    public static int playerIdFromOtp(String otp) {
        return Integer.parseInt(new String(Base64.getDecoder().decode(otp.getBytes(StandardCharsets.UTF_8)))
                .split(":")[0]);
    }

    public static String tokenFromOtp(String otp) {
        return new String(Base64.getDecoder().decode(otp.getBytes(StandardCharsets.UTF_8)))
                .split(":")[1];
    }
}
