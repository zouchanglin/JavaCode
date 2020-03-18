# :moon:Magic-warehouse
om mni pdme hum

## 实现流程:page_facing_up:

1.服务端Socket
2.读取请求数据
	2.1解析请求数据，包装成请求对象
3.按照业务逻辑处理
	3.1 不同的URL，走不同的处理
	3.2 不同的方法走不同的处理
4.处理响应数据，包装成响应对象。

技术：

- 网络编程
- 多线程技术
- HTTP协议理解

##  技术要点 :apple:

> 1.服务器端利用SeverSocket实现与客户端（浏览器）通信
> 2.通过HTTP报文格式解析请求数据，将请求报文包装成对象
> 3.按照业务逻辑处理，不同的URL，不同的HTTP请求方法
> 4.使用线程池处实现理服务器的业务的处理
> 5.采用Maven项目管理工具管理项目

# 环境:earth_africa:

- JDK1.8
- IDEA开发工具
- Maven管理工具

## 功能

目前实现

- GET

## 支持类型

- html
- htm
- css
- js
- txt
- jpeg
- jpg
- gif
- png
- mp3
- mp4

## 扩展

- 页面缓存

## 应用

**编译打包** 

```
mvn clean package
```

**启动程序（设置参数：端口，静态文件根目录）**  

```
java -jar httpd-1.0.0jar --port=888 --ww=E:\workskpace\idea-work\java-httpd\static
```

