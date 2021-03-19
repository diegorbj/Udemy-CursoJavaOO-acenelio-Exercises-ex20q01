package br.com.domum.application;

import br.com.domum.entities.Employees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String fullFilePath = sc.nextLine();

        List<Employees> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fullFilePath))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                employees.add(new Employees(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.print("Enter Salary: ");
        Double salary = sc.nextDouble();

        System.out.printf("Email of people whose salary is more than %.2f:%n", salary);

        List<String> emails = employees.stream()
                .filter(e -> e.getSalary() > salary)
                .map(e -> e.getEmail())
                .sorted()
                .collect(Collectors.toList());

        emails.forEach(System.out::println);

        Double sum = employees.stream()
                .filter(e -> e.getName().charAt(0) == 'M')
                .map(e -> e.getSalary())
                .reduce(0.0, (x, y) -> x + y);

        System.out.printf("Sum of salary of people whose name starts with 'M': %.2f%n", sum);

        sc.close();
    }
}
