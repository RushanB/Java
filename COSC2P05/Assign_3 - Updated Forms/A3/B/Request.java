package B;

import java.io.Serializable;
import A.*;

public class Request implements Serializable {
 String date; //instance variables
 String time;
 String order;
 String item;
 String quantity;
 String area;
 
 public Request(String date, String time, String order, String item, String quantity, String area){
  this.date = date; //constructors
  this.time = time;
  this.order = order;
  this.item = item;
  this.quantity = quantity;
  this.area = area;
 }
 
 public String getDate(){ //returns date
  return date;
 }
 
 public String getTime(){ //returns time
  return time;
 }
 
 public String order(){ //returns order #
  return order;
 }
 
 public String item(){ //returns item #
  return item;
 }
 public String quantity(){ //returns quantity
  return quantity;
 }
 public String area(){ //returns text area variable
  return area;
 }
}