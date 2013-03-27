import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {
	private static ApplicationContext ctx;

    @Override
    public void onStart(Application app) {
        ctx = new ClassPathXmlApplicationContext("components.xml");
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        return ctx.getBean(clazz);
    }

//    public static <T> T getBean(Class<T> beanClass) {
//        if (ctx == null) {
//            throw new IllegalStateException("application context is not initialized");
//        }
//        return ctx.getBean(beanClass);
//    }
}
