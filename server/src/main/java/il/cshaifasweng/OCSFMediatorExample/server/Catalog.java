package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
      App.SafeStartTransaction();
      Random random = new Random();
      Item roses = new Item("rose",random.nextInt(100)+100,"pretty roses", "https://www.desicomments.com/dc3/19/439319/4393191.jpg");
      Item purpleFlower = new Item("purple flower",random.nextInt(100)+100, "purple unique flowers", "https://www.allaboutgardening.com/wp-content/uploads/2021/08/Weeds-With-Purple-Flowers-in-Field-1200x667.jpg");
      Item greenFlower = new Item("green flower",random.nextInt(100)+100,"green flowers which can be found only in china", "https://gardenerspath.com/wp-content/uploads/2019/01/Green-Flowers-From-Beautiful-to-Bizarre.jpg");
      Item yellowSunflower =new Item("yellow sunflower",random.nextInt(100)+100,"unique sunflowers which can can be harvested only in israel", "https://www.johnnyseeds.com/dw/image/v2/BBBW_PRD/on/demandware.static/-/Sites-jss-master/default/dw663570cc/images/products/flowers/01898g_01_zohar.jpg?sw=387&cx=403&cy=58&cw=1000&ch=1000");
      Item blackAndWhite = new Item("black and white flower",random.nextInt(100)+100,"unique black and white flowers which are imported from italy", "https://www.petalrepublic.com/wp-content/uploads/2021/08/38-Black-Flowers-and-Plants-for-the-Home-and-Garden.jpeg");
      App.session.save(roses);
      App.session.save(purpleFlower);
      App.session.save(greenFlower);
      App.session.save(yellowSunflower);
      App.session.save(blackAndWhite);
      roses = new Item("red Flower",random.nextInt(100)+100,"pretty red flowers from the east", "https://www.saga.co.uk/contentlibrary/saga/publishing/verticals/home-and-garden/gardening/garden-ideas/planting/colour/red-hibiscus-rosa-sinensis.jpg");
      purpleFlower = new Item("Brown flowers",random.nextInt(100)+100, "unique brown flowers", "https://i.pinimg.com/originals/a1/24/e9/a124e9dc234d230d7d94b89fc66017a5.jpg");
      greenFlower = new Item("navy flower",random.nextInt(100)+100,"navy flowers which can be found only in usa", "https://cdn11.bigcommerce.com/s-0d1a4/images/stencil/1280x1280/products/191/5919/NavyStem__01879.1636709297.jpg?c=2");
      yellowSunflower =new Item("sakura sunflower",random.nextInt(100)+100,"unique sakura which can can be harvested only in japan", "https://jw-webmagazine.com/wp-content/uploads/2019/06/jw-5d15f032182f45.92816921.jpeg");
      blackAndWhite = new Item("orange flower",random.nextInt(100)+100,"orange flowers from the sun", "https://www.thespruce.com/thmb/9wSFip9fQEtqRa0A05x6zOvW2FY=/2848x2848/smart/filters:no_upscale()/pictures-of-orange-flowers-4061768-hero-af9e809318964fbcae6e922aa3cc8182.JPG");
      blackAndWhite.setPriceAfterDiscount(65);
      App.session.save(roses);
      App.session.save(purpleFlower);
      App.session.save(greenFlower);
      App.session.save(yellowSunflower);
      App.session.save(blackAndWhite);
      App.session.flush();
      App.SafeCommit();
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

   public void addItem(String name, int price, String description, String imageUrl){
      App.SafeStartTransaction();
      Item item = new Item(name, price, description, imageUrl);
      App.session.save(item);
      App.session.flush();
      App.SafeCommit();

      items.add(item);
   }

   public CatalogData getCatalogData()
   {
      CatalogData cat=new CatalogData();
      for (Item item: items)
      {
         ItemData temp=new ItemData(item.getId(),item.getName(),item.getPrice(),item.getDescription(),item.getImageUrl(),item.getPriceAfterDiscount());
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
      App.SafeStartTransaction();
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
      App.SafeCommit();
   }

   public void changeDescription(int id,String description)
   {
      App.SafeStartTransaction();
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
      App.SafeCommit();
   }

   public void changeName(int id,String name)
   {
      App.SafeStartTransaction();
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
      App.SafeCommit();
   }

   public void removeItem(int id) {
      for(Item item: items)
      {
         if(item.getId()==id)
         {
            App.SafeStartTransaction();
            for (BasketItem basketItem : item.getBasketsInside()){
               basketItem.getListItems().remove(item);
               App.session.saveOrUpdate(basketItem);
            }
            App.session.delete(item);
            items.remove(item);
            App.session.flush();
            App.SafeCommit();
            return;
         }
      }
   }

    public void changeDiscount(int id, int newDiscountedPrice) {
       Item item = SearchItemById(id);
       App.SafeStartTransaction();
       item.setPriceAfterDiscount(newDiscountedPrice);
       App.session.saveOrUpdate(this);
       App.session.flush();
       App.SafeCommit();
    }
}
