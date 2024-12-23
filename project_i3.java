import java.util.Scanner;
public class project_i3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("hello hello hello");
        System.out.println("it is working as i planned");
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Hello " + name);
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        System.out.println("You are " + age + " years old");
        sc.close();
        
        
    }
}