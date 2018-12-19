# spring-tutorial

## spring security

>**代码结构**
>>**spring-security-core**
>>>SecurityCoreConfig
>>>>SecurityProperties
>>>>>BrowserProperties
>>>>>ValidateCodeProperties
>>**spring-security-browser**
>>>
>>**spring-security-app**
>>>
>>**spring-security-demo**
>>>

---

>**登录失效跳转**

>集成WebSecurityConfigurerAdapter
1. 配置过滤器
2. 配置成功/失败处理器
3. 配置加密方式PasswordEncoder

>BrowserSecurityController
1. RESTful 接口方式提示
2. 登录页配置跳转

---

>**自定义登录方式**

1. BasicAuthentication

2. UsernamePasswordAuthentication

>**增加过滤器**

1. 图片验证码

2. 短信/邮件验证码
