//package com.cxy.weberpby.security;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @author CXY
// * @version Create Time: 2022/7/6
// * @Description SpringSecurity 配置
// */
//
//@EnableWebSecurity
//public class mySecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // super.configure(http);
//        // 定製請求的授權規則
//        http.authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers("/bwBuild/**").hasRole("bwBuild")
//                .antMatchers("/XXZLList/**").hasRole("XXZLList");
//
//        // 開啟自動配置的登入功能
//        http.formLogin();
//    }
//
//    // 定義認證規則
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // super.configure(auth);
//        // auth.inMemoryAuthentication().withUser("Eric").password("1234").roles("Test");
//
//        // There is no PasswordEncoder mapped for the id “null”异常解决办法
//        // Spring security 5.0中新增了多种加密方式,也改变了默认的密码格式.
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("Eric").password(new BCryptPasswordEncoder().encode("123")).roles("bwBuild","XXZLList")
//                .and().
//                withUser("05E30312").password(new BCryptPasswordEncoder().encode("123456")).roles("bwBuild","XXZLList");
//    }
//}
