# zuul-app-sample

```bash
java -jar target/xxx.jar -Dserver.port=8761 -Deureka.host=127.0.0.1 -Deureka.port=8762
java -jar target/xxx.jar -Dserver.port=8762 -Deureka.host=127.0.0.1 -Deureka.port=8761
```

发现在相互注册的eureka之间可以共享所有的注册服务,
也就是说, 只要向一个eureka server去注册, 整个集群中的eureka都会知道.