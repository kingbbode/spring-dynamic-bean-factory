# spring-dynamic-bean-factory

*For beans that are not necessarily required but should be recovered.*

## example

```java
@Component
public class MemcachedClientDynamicGenerator extends DynamicBeanGeneratorAdapter<MemcachedClient> {

    @Autowired
    private Environment environment;

    @Override
    public MemcachedClient generate() throws IOException, URISyntaxException {
        ~~~
        return new MemcachedClient(uris, bucketName, pwd);
    }
```

If the connection fails and the bean(MemcachedClient) creation fails, the application loads normally.

```java
@Component
public class MemcachedSessionDynamicGenerator extends DynamicBeanGeneratorAdapter<MemcachedSession> {

    @DynamicAutowired
    private MemcachedClient memcachedClient;

    @Autowired
    private Environment environment;

    @Override
    public MemcachedSession generate() throws IOException, URISyntaxException {
        MemcachedSession session = new MemcachedSession();
        ~~~
        session.setMemcachedClient(memcachedClient);
        return session;
    }
}
```

The `@DynamicAutowire` annotation can be used to Dependency Injection the dynamic load bean.

```java
@Component
public class MemcachedSecurityContextRepositoryDynamicGenerator extends DynamicBeanGeneratorAdapter<MemcachedSecurityContextRepository> implements Executable{

    @DynamicAutowired
    private MemcachedSession memcachedSession;
    
    @Override
    public MemcachedSecurityContextRepository generate() throws IOException, URISyntaxException {
        return new MemcachedSecurityContextRepository(memcachedSession);
    }


    @Autowired
    private FilterChainProxy filterChainProxy;
    
    @Override
    public void execute() {
        ~~~
        Map<String, List<Filter>> filterChainMap = filterChainProxy.getFilterChainMap();
        filterChainMap.put((String) urlMatcher.compile("/**"), ~~);
        filterChainProxy.setFilterChainMap(filterChainMap);
        ~~~
    }
    
    @Override
    public int getDelaySeconds() {
        return 30;
    }
}
```


If you implement the `Executable` Interface, you can perform additional actions.


If you override `getDelaySeconds` and specify a time, you will attempt to recover the bean at the appropriate time when the bean creation fails.