package collections.streams;

import java.util.*;
import java.util.stream.Collectors;

class Employee {

    private int id;
    private String name;
    private int age;
    private String departNames;
    private String address;
    private double salary;

    private String gender;

    public Employee(int id, String name, int age, String departNames, String address, double salary, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.departNames = departNames;
        this.address = address;
        this.salary = salary;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartNames() {
        return departNames;
    }

    public void setDepartNames(String departNames) {
        this.departNames = departNames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", departNames='" + departNames + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                '}';
    }
}

public class EmployeeRunner {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Abraham", 29, "IT", "Mumbai", 20000, "Male"),
                new Employee(2, "Mary", 27, "Sales", "Chennai", 25000, "Female"),
                new Employee(3, "Joe", 28, "IT", "Chennai", 22000, "Male"),
                new Employee(4, "John", 29, "Sales", "Gurgaon", 29000, "Male"),
                new Employee(5, "Liza", 25, "Sales", "Bangalore", 32000, "Female"),
                new Employee(6, "Peter", 27, "Admin", "Mumbai", 31500, "Male"),
                new Employee(7, "Harry", 30, "Research", "Kochi", 21000, "Male")
        );

        System.out.println("Find list of employees whose name starts with alphabet A");
        List<String> q1 = employees.stream()
                .filter(employee -> employee.getName().startsWith("A"))
                .map(Employee::getName)
                .toList();
        System.out.println(q1);
        System.out.println("----------");

        System.out.println("Group The employees By Department Names");
        Map<String, List<Employee>> q2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartNames));
        System.out.println(q2);
        System.out.println("----------");

        System.out.println("Find the total count of employees using stream");
        long q3 = employees.stream().count();
        System.out.println(q3);
        System.out.println("----------");

        System.out.println("Find the max age of employees");
        Employee q4 = employees.stream()
                .max(Comparator.comparing(Employee::getAge)).get();
        System.out.println(q4);
        System.out.println("----------");

        System.out.println("Find all department names");
        List<String> q5 = employees.stream()
                .map(Employee::getDepartNames)
                .distinct()
                .toList();
        System.out.println(q5);
        System.out.println("----------");

        System.out.println("Find the count of employee in each department");
        Map<String, Long> q6 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.counting()));
        System.out.println(q6);
        System.out.println("----------");

        System.out.println("Find the list of employees whose age is less than 30");
        List<Employee> q7 = employees.stream()
                .filter(employee -> employee.getAge() < 30)
                .toList();
        System.out.println(q7);
        System.out.println("----------");

        System.out.println("Find the list of employees whose age is in between 26 and 31");
        List<Employee> q8 = employees.stream()
                .filter(employee -> employee.getAge() > 26 && employee.getAge() < 31)
                .toList();
        System.out.println(q8);
        System.out.println("----------");

        System.out.println("Find the average age of male and female employee");
        Map<String, Double> q9 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(q9);
        System.out.println("----------");

        System.out.println("Find the department who is having maximum number of employee");
        Map.Entry<String, Long> q10 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();
        System.out.println(q10.getKey());

        System.out.println("Find the Chennai who stays in Mumbai and sort them by their names");
        List<Employee> q11 = employees.stream()
                .filter(employee -> employee.getAddress().equals("Chennai"))
                .sorted(Comparator.comparing(Employee::getName))
                .toList();
        System.out.println(q11);
        System.out.println("----------");

        System.out.println("Find the average salary in all departments");
        Map<String, Double> q12 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartNames,
                        Collectors.collectingAndThen(Collectors.averagingDouble(Employee::getSalary),
                                salary -> Math.round(salary * 100) / 100.0)));
        System.out.println(q12);
        System.out.println("----------");

        System.out.println("Find the highest salary in each department");
        Map<String, Optional<Employee>> q13 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(q13);
        System.out.println("----------");

        System.out.println("Find the employee who has second lowest salary");
        Employee q14 = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(1)
                .findFirst().get();
        System.out.println(q14);
        System.out.println("----------");

        System.out.println("Find the list of employee and sort them by their salary");
        List<Employee> q15 = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .toList();
        System.out.println(q15);
        System.out.println("----------");

        System.out.println("Create a employee map with key as Id and value as salary");
        Map<Integer, Double> q16 = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getSalary, (e1, e2) -> e1, HashMap::new));
        System.out.println(q16);
    }
}
