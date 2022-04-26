package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static SimpleServer server;
    public static void main( String[] args ) throws IOException
    {
        /*Catalog catalog=new Catalog();
        catalog.generateItems();
        catalog.pullItemsFromCatalog();
        for(Item item: catalog.items)
        {
            System.out.println(item);
        }testing the catalog*/
        server = new SimpleServer(3000);
        server.listen();
    }
}
