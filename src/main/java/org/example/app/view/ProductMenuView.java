package org.example.app.view;

import org.example.app.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class ProductMenuView {

    Scanner scanner = new Scanner(System.in);

    public String getOption() {
        showMenu();
        String option = "";
        try {
            option = scanner.nextLine().trim();
        } catch (InputMismatchException ime) {
            System.out.println(Constants.INCORRECT_VALUE_MSG);
        }
        return option;
    }

    private void showMenu() {
        System.out.print("""
                
                ______ MENU ___________
                1 - Create product.
                2 - View all products.
                3 - View product by id.
                4 - Update product.
                5 - Delete product.
                0 - Close the App.
                """);
    }

    public void getOutput(String output) {
        if (output.equals(Constants.APP_CLOSE_MSG))
            System.out.println(output);
        scanner.close();
        System.exit(0);
    }
}
