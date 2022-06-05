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
   }

   public void generateItems()
   {
      App.session.beginTransaction();
      Random random = new Random();
      Item roses= new Item("rose",random.nextInt(100)+100,"pretty roses");
      Item purpleFlower= new Item("purple flower",random.nextInt(100)+100, "purple unique flowers");
      Item greenFlower= new Item("green flower",random.nextInt(100)+100,"green flowers which can be found only in china");
      Item yellowSunflower=new Item("yellow sunflower",random.nextInt(100)+100,"unique sunflowers which can can be harvested only in israel");
      Item blackAndWhite= new Item("black and white flower",random.nextInt(100)+100,"unique black and white flowers which are imported from italy");
      App.session.save(roses);
      App.session.save(purpleFlower);
      App.session.save(greenFlower);
      App.session.save(yellowSunflower);
      App.session.save(blackAndWhite);
      roses= new Item("red Flower",random.nextInt(100)+100,"pretty red flowers from the east");
      purpleFlower= new Item("Brown flowers",random.nextInt(100)+100, "unique brown flowers");
      greenFlower= new Item("navy flower",random.nextInt(100)+100,"navy flowers which can be found only in usa");
      yellowSunflower=new Item("sakura sunflower",random.nextInt(100)+100,"unique sakura which can can be harvested only in japan");
      blackAndWhite= new Item("orange flower",random.nextInt(100)+100,"orange flowers from the sun");
      App.session.save(roses);
      App.session.save(purpleFlower);
      App.session.save(greenFlower);
      App.session.save(yellowSunflower);
      App.session.save(blackAndWhite);
      App.session.flush();
      App.session.getTransaction().commit();
   }
   public void pullItemsFromCatalog()
   {
      CriteriaBuilder builder = App.session.getCriteriaBuilder();
      CriteriaQuery<Item> query = builder.createQuery(Item.class);
      query.from(Item.class);
      List<Item> data = App.session.createQuery(query).getResultList();
      items.clear();
      items.addAll(data);
   }

   public void addItem(String name, int price, String description){
      App.session.beginTransaction();
      Item item = new Item(name, price, description);
      App.session.save(item);
      App.session.flush();
      App.session.getTransaction().commit();

      items.add(item);
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

   public Item SearchItemById(int id){
      for(Item item : items){
         if(item.getId() == id){
            return item;
         }
      }
      return null;
   }

   public void changePrice(int id,int newPrice)
   {
      App.session.beginTransaction();
      Item temp=new Item();
      for (Item item :items)
      {
         if(item.getId()==id)
         {
            temp=item;
         }
      }
      temp.setPrice(newPrice);
      App.session.save(temp);
      App.session.flush();
      App.session.getTransaction().commit();
   }

   public void changeDescription(int id,String description)
   {
      App.session.beginTransaction();
      Item temp=new Item();
      for (Item item :items)
      {
         if(item.getId()==id)
         {
            temp=item;
         }
      }
      temp.setDescription(description);
      App.session.save(temp);
      App.session.flush();
      App.session.getTransaction().commit();
   }

   public void changeName(int id,String name)
   {
      App.session.beginTransaction();
      Item temp=new Item();
      for (Item item :items)
      {
         if(item.getId()==id)
         {
            temp=item;
         }
      }
      temp.setName(name);
      App.session.save(temp);
      App.session.flush();
      App.session.getTransaction().commit();
   }

   public void removeItem(int id) {
      for(Item item: items)
      {
         if(item.getId()==id)
         {
            App.session.delete(item);
            items.remove(item);
         }
      }
   }
}
