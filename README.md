##ICY-ClaTable
i重邮项目课表查询以及校历查看功能模块。by android

###网络请求模块(Retrofit2使用的小技巧)

+ Retrofit+Rxjava。

+ HeaderIntercepteors: 拦截请求头和响应头，动态进行Http缓存。

+ HttpLoggingInterceptor: 拦截请求头和响应头，打印request和response的信息。
  打印信息Like this:

  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: <-- 200 OK       http://219.153.62.77/kebiao.php?id=2014210876 (2936ms)
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: Date: Sat, 09 Apr 2016 06:01:21 GMT
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: Server: Apache
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: X-Powered-By: PHP/5.3.27-pl0-gentoo
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: Access-Control-Allow-Origin: *
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: Content-Length: 5144
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: Keep-Alive: timeout=15, max=100
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: Connection: Keep-Alive
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: Content-Type: text/html
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: OkHttp-Sent-Millis: 1460210993763
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: OkHttp-Received-Millis: 1460210996695
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: Cache-Control: public, max-age=3600
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: {"class":[{"mutilple":[{"classname":"数据结构","teachername":"刘友军  "..........
  04-09 10:09:56.693 25351-656/com.example.zane.icy_clatable D/OkHttp: <-- END HTTP (5144-byte body)
  
+ StethoInterceptor: FaceBook出品的安卓调试工具:Stetho。FaceBook出品必属精品。

+ SchedulerTransform: 线程调度转换器。Compose+Transform结合使用。进行所有网络请求的线程调度。

+ ErrorTransform: 错误处理转换器。也是Compose+Transform结合使用。进行所有的错误处理。所以Presenter层只需要关心onNext()方法
  只需要关心获取到的数据对象，直接用new Action1(XXX xxx)，这样处理数据更加优雅。
