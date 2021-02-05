package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware  {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        // 反射机制获取实现类
        Object object = null;

        try {
            Class service = Class.forName(serviceClass);
            Reflections reflections = new Reflections(this.getClass().getPackage().getName());
            Set<Class> implClass = reflections.getSubTypesOf(service);

            if (implClass!=null && !implClass.isEmpty()) {
                for (Class impl: implClass) {
                    object = impl.newInstance();
                    break;
                }
            }
            Class.forName(serviceClass,true, this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return object;

        //return this.applicationContext.getBean(serviceClass);
    }
}
