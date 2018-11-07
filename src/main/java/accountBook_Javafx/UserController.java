package accountBook_Javafx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    static ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

    protected static AccountBook user = context.getBean("user", AccountBook.class);

}
