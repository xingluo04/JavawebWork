## 实现方式：
### 创建监听器类：
- 创建一个类 RequestLoggerListener 实现 javax.servlet.ServletRequestListener 接口。
- 实现 requestInitialized(ServletRequestEvent sre) 和 requestDestroyed(ServletRequestEvent sre) 方法。
### 记录请求开始时间：
- 在 requestInitialized 方法中，记录请求开始的时间戳。
### 记录请求结束时间和处理时间：
- 在 requestDestroyed 方法中，记录请求结束的时间戳，并计算从请求开始到结束的总处理时间。
### 日志格式：
- 使用 SimpleDateFormat 或其他日期时间格式化工具来格式化时间戳。
- 构建一个包含所需信息的 JSON 对象或自定义格式的字符串。
### 输出日志：
- 将格式化后的日志信息输出到控制台、日志文件或通过日志框架记录。
### 测试 Servlet：
- 创建一个简单的 TestServlet，当访问特定路径时，可以触发日志记录。