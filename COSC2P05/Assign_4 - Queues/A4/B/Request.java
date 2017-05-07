package B;

import java.io.Serializable;

public class Request implements Serializable {
 String date; //instance variables
 String time;
 String rNumber;
 String area;
 
 public Request(String date, String time, String rNumber, String area){
  this.date = date; //constructors
  this.time = time;
  this.rNumber = rNumber;
  this.area = area;
 }
 
 public String getDate(){ //returns date
  return date;
 }
 
 public String getTime(){ //returns time
  return time;
 }
 
 public String rNumber(){ //returns request #
  return rNumber;
 }
 
 public String getArea(){ //returns text area variable
  return area;
 }
}