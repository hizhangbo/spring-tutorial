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


*登录成功后，跳转到用户授权受阻的页面*
*private RequestCache requestCache = new HttpSessionRequestCache();*
*#登录之前请求的时候，在过滤器链的最后，会发现没有登录，抛出异常，这时候会将当前的request放到HttpSessionRequestCache中，其实就是将request放到了session中，session的名字是“SPRING_SECURITY_SAVED_REQUEST”，等下次执行了登录之后，会先从HttpSessionRequestCache将上一个request拿出来，继续这个request。因此就实现了上一个request继续跳转的功能。*

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
