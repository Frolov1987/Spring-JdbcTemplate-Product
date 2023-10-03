package org.example.app.utils;

import org.example.app.conf.AppConfig;
import org.example.app.controller.ProductController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppStarter {

    public static void startApp() {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ProductController productController = context.getBean(ProductController.class);
        productController.getOption();

        context.close();
    }
}
