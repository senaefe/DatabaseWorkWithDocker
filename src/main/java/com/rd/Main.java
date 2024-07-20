package com.rd;
import com.rd.Database.CrudOperations;
import com.rd.Database.Personel;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CrudOperations crudOperations = new CrudOperations();

        crudOperations.deleteAllPersons();

        crudOperations.createPerson("Sena", "Efe", "Software Engineer", "IT", 50000);
        crudOperations.createPerson("Bora", "Bayraktar", "Manager", "HR", 150000);
        crudOperations.createPerson("Aylin", "Umut", "HR", "HR", 30000);


        System.out.println("Personnel List");
        List<Personel> personels = crudOperations.readAllPersons();
        for (Personel personel : personels) {
            System.out.println(personel);
        }
        System.out.println("-----");

        crudOperations.updatePerson(1, "Sena", "Efe", "Senior Software Engineer", "IT", 60000);

        crudOperations.deletePerson(2);

        System.out.println("Updated Personel List:");
        personels = crudOperations.readAllPersons();
        for (Personel personel : personels) {
            System.out.println(personel);
        }
    }
}
