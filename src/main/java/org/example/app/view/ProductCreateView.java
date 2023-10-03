package org.example.app.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ProductCreateView {

    public String[] getData() {
        Scanner scanner = new Scanner(System.in);
        String title = "Input name: ";
        System.out.print(title);
        String name = scanner.nextLine().trim();
        title = "Input quota: ";
        System.out.print(title);
        String quota = scanner.nextLine().trim();
        title = "Input price ";
        System.out.print(title);
        String price = scanner.nextLine().trim();
        return new String[]{name, quota, price};
    }

    public void getOutput(String output) {
        System.out.println(output);
    }
}
