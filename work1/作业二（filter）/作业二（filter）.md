## 实现方式：
### 创建过滤器类：
- 创建一个类 LoginFilter 实现 javax.servlet.Filter 接口。
- 实现 init(FilterConfig filterConfig), doFilter(ServletRequest request, ServletResponse response, FilterChain chain), 和 destroy() 方法。
### 配置过滤器：
- 使用 @WebFilter 注解或 web.xml 配置文件来映射过滤器到特定的 URL 模式（例如，对所有请求进行过滤）。
### 登录验证逻辑：
- 在 doFilter 方法中，将 ServletRequest 转换为 HttpServletRequest。
- 检查用户是否已经登录（通常是通过检查 session 中的用户属性）。
- 如果用户未登录，则重定向到登录页面。
- 如果用户已登录，则调用 chain.doFilter(request, response) 继续过滤链。
### 排除不需要过滤的 URL：
- 定义一个方法来检查请求的 URI 是否在排除列表中（例如，登录页面和静态资源）。
- 如果请求的 URI 在排除列表中，则直接调用 chain.doFilter(request, response)。