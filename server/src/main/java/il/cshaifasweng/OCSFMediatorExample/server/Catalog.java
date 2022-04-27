package il.cshaifasweng.OCSFMediatorExample.server;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;
import il.cshaifasweng.OCSFMediatorExample.server.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;

import org.hibernate.HibernateException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Catalog {
   public List<Item> items;
   public Catalog(){
      items=new ArrayList<Item>();
      SessionFactory sessionFactory = getSessionFactory();
      session = sessionFactory.openSession();

   }
   private static Session session;

   private static SessionFactory getSessionFactory() throws HibernateException {
      Configuration configuration = new Configuration();
      configuration.addAnnotatedClass(Item.class);
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
              .applySettings(configuration.getProperties())
              .build();

      return configuration.buildSessionFactory(serviceRegistry);
   }
   public void generateItems()
   {
      session.beginTransaction();
      Random random = new Random();
      Item roses= new Item("rose",random.nextInt(100)+100,"pretty roses");
      Item purpleFlower= new Item("purple flower",random.nextInt(100)+100, "purple unique flowers");
      Item greenFlower= new Item("green flower",random.nextInt(100)+100,"green flowers which can be found only in china");
      Item yellowSunflower=new Item("yellow sunflower",random.nextInt(100)+100,"unique sunflowers which can can be harvested only in israel");
      Item blackAndWhite= new Item("black and white flower",random.nextInt(100)+100,"unique black and white flowers which are imported from italy");
      session.save(roses);
      session.save(purpleFlower);
      session.save(greenFlower);
      session.save(yellowSunflower);
      session.save(blackAndWhite);
      session.flush();
      session.getTransaction().commit();
   }
   public void pullItemsFromCatalog()
   {
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Item> query = builder.createQuery(Item.class);
      query.from(Item.class);
      List<Item> data = session.createQuery(query).getResultList();
      items.addAll(data);
   }
   public CatalogData getCatalogData()
   {
      CatalogData cat=new CatalogData();
      for (Item item: items)
      {
         ItemData temp=new ItemData(item.getId(),item.getName(),item.getPrice(),item.getDescription());
         cat.itemsdata.add(temp);
      }
      return cat;
   }
   public void changePrice(int id,int newPrice)
   {
      session.beginTransaction();
      Item temp=new Item();
      for (Item item :items)
      {
         if(item.getId()==id)
         {
            temp=item;
         }
      }
      temp.setPrice(newPrice);
      session.save(temp);
      session.flush();
      session.getTransaction().commit();
   }

}
