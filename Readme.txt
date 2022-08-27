/**
 * The default {@link ServerHttpSecurity} configuration.
 * @param http
 * @return
 */
private SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http
        .authorizeExchange() // this add authorization
            .anyExchange() // for any request
            .authenticated(); // Spring Security will authorize only authenticated users

    if (isOAuth2Present && OAuth2ClasspathGuard.shouldConfigure(this.context)) {
        OAuth2ClasspathGuard.configure(this.context, http);
    } else {
        http
            .httpBasic().and()
            .formLogin();
    }

    SecurityWebFilterChain result = http.build();
    return result;
}