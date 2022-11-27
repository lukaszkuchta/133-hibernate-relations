package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.hibernate.model.Student;

import java.util.Scanner;

public class Main_dodajStudenta {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("podaj imie");
        String imie = scanner.nextLine();
        System.out.println("podaj nazwisko");
        String nazwisko = scanner.nextLine();
        System.out.println("podaj rok rozpoczecia");
        String rokRozpoczeciaString = scanner.nextLine();
        int rokRozpoczecia = Integer.parseInt(rokRozpoczeciaString);

        Student student = Student.builder()
                .imie(imie)
                .nazwisko(nazwisko)
                .rokRozpoczecia(rokRozpoczecia)
                .build();

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();
        } catch (Exception e){
            System.err.println("Blad dodawania studenta do bazy");
        }
    }
}
