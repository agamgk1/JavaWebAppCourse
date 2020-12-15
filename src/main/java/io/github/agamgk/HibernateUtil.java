package io.github.agamgk;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//klasa ktora odpowie za tworzenie fabryki sesji itp potrzebnych do pracy z hibernate. Dodatkowo pozwoli pozystkiwac w repozytoriach SessionFactory
//SessionFactory - dzieki temu mozemy stworzyc sesje do bazy danych. W czasie trwania sesji Mozna wykonywac zapytania do bazy, wstawiac wiersze itp
class HibernateUtil {
    public static final SessionFactory sessionFactory = buildSessionFactory();

    //Częśc skopiowana z Hibernate toutorial - annotations

    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory()  {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            //wyrzucenie dalej wyjatku
            throw e;
        }
    }

    // nie bedzie mozna utworzyc obiektu i dziedziczyc
    private HibernateUtil() {
    }
}
