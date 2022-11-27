package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.hibernate.model.Ocena;
import pl.sda.hibernate.model.Student;

import java.util.Scanner;

public class Main_usunOcene {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("co chcesz usunac? (student / ocena");
        String odpowiedz = scanner.nextLine();

        if(odpowiedz.equalsIgnoreCase("ocena")){
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                System.out.println("podaj id oceny");
                String idOcenyString = scanner.nextLine();
                Long idOceny = Long.parseLong(idOcenyString);

                Ocena ocena = session.get(Ocena.class, idOceny);
                if (ocena == null){
                    System.err.println("ocena nie istnieje.");
                } else {
                    session.remove(ocena);
                }
                transaction.commit();
            }catch (Exception e){
                System.err.println("blad");
            }
        } else if(odpowiedz.equalsIgnoreCase("student")) {
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                System.out.println("podaj id studenta");
                String idStudentaString = scanner.nextLine();
                Long idStudenta = Long.parseLong(idStudentaString);

                Student student = session.get(Student.class, idStudenta);
                if (student != null) {
                    if(!(student.getOceny().isEmpty())){
                        for(Ocena ocena : student.getOceny()){
                            session.remove(ocena);
                        }
                    }
                    session.remove(student);
                } else {
                    System.err.println("nie ma takiego studenta");
                }

                transaction.commit();
            } catch (Exception e) {
                System.err.println("blad");
            }
        }
    }
}
