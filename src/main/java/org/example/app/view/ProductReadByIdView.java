package org.example.app.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ProductReadByIdView {

    public String getData() {
        Scanner scanner = new Scanner(System.in);
        String title = "Input id: ";
        System.out.print(title);
        return scanner.nextLine().trim();
    }

    public void getOutput(String output) {
        System.out.println(output);
    }
}
