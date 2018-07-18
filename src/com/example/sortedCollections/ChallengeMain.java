package com.example.sortedCollections;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ChallengeMain {
    private static StockList stockList = new StockList();


    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10,7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50,2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0,10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50,200);
        stockList.addStock(temp);

        //if we add duplicates
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95,4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50,36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99,35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40,80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76,40);
        stockList.addStock(temp);




        Basket myBasket = new Basket("AJ");

        addItemToBasket(myBasket,"car",1);
        addItemToBasket(myBasket,"vase",20);
        removeItemFromBasket(myBasket,"car",1);
        removeItemFromBasket(myBasket,"vase",2);
        checkOut(myBasket,stockList);
        addItemToBasket(myBasket,"car",1);
        checkOut(myBasket,stockList);
        System.out.println(myBasket.Items());
        System.out.println(stockList);

        //checkOut(myBasket,stockList);










    }

//    public static int sellItem(Basket basket, String item, int quantity){
//        // retrieve the item from stock list
//        StockItem stockItem = stockList.get(item);
//        if(stockItem == null){
//            System.out.println("We don't sell " + item);
//            return 0;
//        }
//        if(stockList.sellStock(item, quantity) != 0){
//            basket.addToBasket(stockItem, quantity);
//            return quantity;
//        }
//        return 0;
//    }

    public static int addItemToBasket(Basket basket,String item, int quantity){
        StockItem stockItem = stockList.get(item);
        if(stockItem == null){
            System.out.println("We do not have " + item);
            return 0;
        }
        if((stockItem.quantityInStock() >= quantity) && (stockItem.quantityInStock() > stockItem.getReserved())){
            System.out.println(item + " added to basket");
            basket.addToBasket(stockItem,quantity);
            return quantity;
        }

        System.out.println("we dont have that many");
        return 0;
    }


    // need to check the map from the basket item to see if it is present.
    public static int removeItemFromBasket(Basket basket, String item, int quantity){

       Map<StockItem, Integer> tempMap = new TreeMap<>(basket.Items());
       for(Map.Entry<StockItem, Integer> o: tempMap.entrySet()){
           StockItem stockItem = o.getKey();
           if(stockItem == null){

               System.out.println(item + " is not in your basket.");
               return 0;
           }
           if(quantity <= stockItem.getReserved() && (stockItem.getReserved() > 0) && (stockItem.getName() == item)){
               System.out.println(stockItem);
               basket.removeItem(stockItem,quantity);
               System.out.println(item + " removed from basket");
               return quantity;
           }
       }
        System.out.println("There are not that many of that item in your basket");
        return 0;

    }

    public static int checkOut(Basket basket,StockList stockList){
        Map<StockItem, Integer> tempMap = new TreeMap<>(basket.Items());
        for(Map.Entry<StockItem, Integer> o: tempMap.entrySet()){
            System.out.println(o.getKey().getReserved() + " " + o.getKey().getName() + " sold");
            stockList.sellStock(o.getKey().getName(),o.getKey().getReserved());
            removeItemFromBasket(basket,o.getKey().getName(),o.getKey().getReserved());

        }

        return 0;

    }


}


