package kz.bars.family.budget.receipt.api.JWT;

public class JWTSecurityConstants {
    public static final String[] UN_SECURED_URLs = {"/api-docs/**", "/swagger-ui/**",
            "/info", "/healthcheck", "/metrics"};
    public static final String[] SECURED_URLs = {"/api/**"};
    public static final String SECRET_KEY = "MySecretKeyGenAuthorizationToken";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
