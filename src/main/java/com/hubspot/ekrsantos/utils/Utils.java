package com.hubspot.ekrsantos.utils;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

import java.time.Duration;

public class Utils {

    private static final Bucket RATE_LIMITER = Bucket4j.builder()
            .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofSeconds(1))))
            .build();

    public static Bucket rateLimit() {
        return RATE_LIMITER;
    }

}
