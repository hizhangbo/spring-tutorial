# spring-tutorial

## spring security

> **代码结构**
1. **spring-security-core**
> SecurityCoreConfig
>> SecurityProperties *#安全配置项*
>>> BrowserProperties *#浏览器登录配置项*<br/>
>>> ValidateCodeProperties *#验证码配置项*
>>>> ImageCodeProperties *#图片验证码配置项*

> ValidateCodeFilter
>> ValidateCodeController *#图片验证码在浏览器和app都用到，所以放在core里*

2. **spring-security-browser**
> MyUserDetailsService implements UserDetailsService -> loadUserByUsername

> BrowserSecurityConfig extends WebSecurityConfigurerAdapter -> configure

> BrowserSecurityController RESTful/HTML Login Redirect

3. **spring-security-app**
> 

4. **spring-security-demo**
> 

---

> **登录失效跳转**

> 集成WebSecurityConfigurerAdapter
1. 配置过滤器
2. 配置成功/失败处理器
3. 配置加密方式PasswordEncoder

>BrowserSecurityController
1. RESTful 接口方式提示
2. 登录页配置跳转

---

> **自定义登录方式**

1. BasicAuthentication

2. UsernamePasswordAuthentication

> **增加过滤器**

1. 图片验证码

2. 短信/邮件验证码
