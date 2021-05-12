package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CarMain {
    protected SessionFactory sessionFactory;

    protected void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected void exit() {
        sessionFactory.close(); // code to close Hibernate Session factory
    }

    protected void create() {
        Car car = new Car();
        car.setBrand("Ford");
        car.setModel("Focus");
        car.setPrice(2500);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(car);

        session.getTransaction().commit();
        session.close();

    }

    protected void read() {
        Session session = sessionFactory.openSession();

        long carId = 20;
        Car car = session.get(Car.class, carId);

        System.out.println("Brand: " + car.getBrand());
        System.out.println("Model: " + car.getModel());
        System.out.println("Price: " + car.getPrice());

        session.close();
    }

    protected void update() {
        Car car = new Car();
        car.setId(1);
        car.setBrand("Ford");
        car.setModel("Fiesta");
        car.setPrice(4500);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(car);

        session.getTransaction().commit();
        session.close();
    }

    protected void delete() {
        Car car = new Car();
        car.setId(4);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(car);

        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        CarMain main = new CarMain();
        main.setup();
        main.delete();
        main.exit();
    }

}
