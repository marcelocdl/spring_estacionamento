package br.ufsm.csi.poow2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /*public DaoAuthenticationProvider authProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authProvider;
    } */

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authProvider());
    } */

    @Autowired
    public void configureAutenticacao(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public FiltroAutenticacao filtroAutenticacao() throws Exception{
        return new FiltroAutenticacao();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //.authenticationProvider(this.authProvider())
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/teste").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/**").hasAnyAuthority("ADMIN");
             /*   .antMatchers(HttpMethod.GET, "/cliente/").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/cliente/cadastrar/").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/estadia").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/estadia/estadia/").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/veiculo").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/veiculo/veiculo").hasAnyAuthority("ADMIN"); */
                //.and().formLogin();

        http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsFilter corsFilter(){
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);

    }

}
