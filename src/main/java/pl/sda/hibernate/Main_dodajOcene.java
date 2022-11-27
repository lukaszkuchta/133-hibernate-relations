package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.hibernate.model.Ocena;
import pl.sda.hibernate.model.Student;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main_dodajOcene {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("podaj id studenta");
        String idStudenta = scanner.nextLine();
        Long id = Long.parseLong(idStudenta);



        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Student szukanyStudent = session.get(Student.class, id);

            if(szukanyStudent != null) {
                System.out.println("podaj ocene");
                String dodawanaOcena = scanner.nextLine();
                double wartoscOceny = Double.parseDouble(dodawanaOcena);

                Ocena nowaOcena = Ocena.builder()
                        .uczen(szukanyStudent)
                        .wartosc(wartoscOceny)
                        .build();

                session.persist(nowaOcena);
            }else{
                System.err.println("taki student nie istnieje");
            }

            transaction.commit();
        } catch (Exception e){
            System.err.println("Blad dodawania studenta do bazy");
        }
    }
}
