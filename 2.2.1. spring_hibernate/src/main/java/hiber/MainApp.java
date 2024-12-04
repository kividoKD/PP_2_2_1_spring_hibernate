package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("car1", 11);
      Car car2 = new Car("car2", 22);
      Car car3 = new Car("car3", 33);
      Car car4 = new Car("car4", 44);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      userService.add(user1, car1);
      userService.add(user2, car2);
      userService.add(user3, car3);
      userService.add(user4, car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car ID = "+user.getCar().getId());
         System.out.println("Car Model = "+user.getCar().getModel());
         System.out.println("Car Series = "+user.getCar().getSeries());
         System.out.println();
      }

      System.out.println(userService.whoDrivesThisCar("car2", 22));

      context.close();
   }
}
