package com.passerby.apigateway.config;

import java.util.*;

public class WhiteList {
    public static final List<String> WHITELISTED_PATHS = Arrays.asList(
            "/api/v1/auth/**"
    );
}
