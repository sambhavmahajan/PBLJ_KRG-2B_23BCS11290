import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentDAO studentDAO = context.getBean(StudentDAO.class);
        BankingService bankingService = context.getBean(BankingService.class);

        // Spring + Hibernate CRUD
        Student s1 = new Student("John",22);
        studentDAO.save(s1);

        System.out.println("Students List:");
        studentDAO.getAll().forEach(System.out::println);

        // Transaction example
        try{
            bankingService.transfer(101,102,500);
            System.out.println("Transaction Successful!");
        } catch(Exception e){
            System.out.println("Transaction Failed: "+e.getMessage());
        }

        context.close();
    }
}
