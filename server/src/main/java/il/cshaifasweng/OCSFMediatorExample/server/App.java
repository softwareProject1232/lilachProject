package il.cshaifasweng.OCSFMediatorExample.server;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

	private static SimpleServer server;
    public static Catalog catalog;
    public static Branches branches;
    public static Session session;
    public static void main( String[] args ) throws IOException
    {
        session = getSessionFactory().openSession();

        catalog=new Catalog();
        catalog.generateItems();
        catalog.pullItemsFromCatalog();
        /*for(Item item: catalog.items)
        {
            System.out.println(item);
        }testing the catalog*/

        branches = new Branches();
        branches.GenerateValues();


        /*users = new Users();
        users.generateUsers();
        users.pullUsersFromDB();

        orders = new Orders();
        orders.pullOrdersFromDB();*/

        server = new SimpleServer(3024);
        server.listen();
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Complaint.class);
        configuration.addAnnotatedClass(BasketItem.class);
        configuration.addAnnotatedClass(Branch.class);
        configuration.addAnnotatedClass(Orders.class);
        configuration.addAnnotatedClass(Users.class);


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }


    private static int TransactionDepth = 0;
    public static void SafeStartTransaction(){
        if(TransactionDepth++ == 0) {
            session.beginTransaction();
        }
    }
    public static void SafeCommit(){
        if(--TransactionDepth == 0) {
            session.getTransaction().commit();
        }
    }
}
