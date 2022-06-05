package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static SimpleServer server;
    public static Catalog catalog;
    public static Users users;
    public static Orders orders;
    public static void main( String[] args ) throws IOException
    {
        catalog=new Catalog();
        catalog.generateItems();
        catalog.pullItemsFromCatalog();
        /*for(Item item: catalog.items)
        {
            System.out.println(item);
        }testing the catalog*/

        users = new Users();
        users.pullUsersFromDB();

        orders = new Orders();
        orders.pullOrdersFromDB();

        server = new SimpleServer(3024);
        server.listen();
    }
}
