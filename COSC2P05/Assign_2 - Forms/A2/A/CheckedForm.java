package A;

import BasicIO.BasicForm;
import BasicIO.Formats;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;


public class CheckedForm extends BasicForm { //creates CheckedForm using BasicIO
 Hashtable<String, Type> hashTable = new Hashtable<String, Type>(); //stores field types
 
 public CheckedForm() {
  super();
 }
 
 public void addField(String name, String field, Type kind) { //adds a field using BasicIO
  switch (kind) {
  case DATE:
   this.addTextField(name, field, Formats.getDateInstance());
   this.addLabel(name + "field");
   hashTable.put(name, Type.DATE);  //date
   break;
  case TIME:
   this.addTextField(name, field, Formats.getTimeInstance());
   this.addLabel(name + "field");
   hashTable.put(name, Type.TIME); //time
   break;
  case CURRENCY:
   this.addTextField(name, field, Formats.getCurrencyInstance());
   this.addLabel(name + "field");
   hashTable.put(name, Type.CURRENCY); //currency
   break;
  case DECIMAL:
   this.addTextField(name, field, Formats.getDecimalInstance(20));
   this.addLabel(name + "field");
   hashTable.put(name, Type.DECIMAL); //decimal
   break;
  case INTEGER:
   this.addTextField(name, field, Formats.getIntegerInstance());
   this.addLabel(name + "field");
   hashTable.put(name, Type.INTEGER); //integer
   break;
  case PERCENT:
   this.addTextField(name, field, Formats.getPercentInstance());
   this.addLabel(name + "field");
   hashTable.put(name, Type.PERCENT); //percent
   break;
  case STRING:
   this.addTextField(name, field);
   this.addLabel(name + "field");
   hashTable.put(name, Type.STRING); //string
   break;
  }
 }

 public void addText(String name, String field) { //adds Textarea using BasicIO
  this.addTextArea(name, field);
  this.addLabel(name + "field");
 }
 
 public Object readField(String name) { //parse data and catches errro
  String s = this.readString(name);
  switch (hashTable.get(name)) {
  case DATE:
   try { //date
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
    Date parse = dateFormat.parse(s);
    this.writeString(name + "field", "");
    return parse;
   }catch (ParseException e) {
    this.writeString(name + "field", "Error!");
   }
   break;
  case TIME:
   try { //time
    DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
    Date parse = timeFormat.parse(s);
    this.writeString(name + "field", "");
    return parse;
   }catch (ParseException e) {
    this.writeString(name + "field", "Error!");
   }

   break;
  case CURRENCY:
   try { //currency
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    Number parse = currencyFormat.parse(s);
    this.writeString(name + "field", "");
    return parse;
   }catch (ParseException e) {
    this.writeString(name + "field", "Error!");
   }
   break;
  case DECIMAL:
   try { //decimal
    NumberFormat decimalFormat = DecimalFormat.getNumberInstance();
    Number parse = decimalFormat.parse(s);
    this.writeString(name + "field", "");
    return parse;
   }catch (ParseException e) {
    this.writeString(name + "field", "Error!");
   }
   break;
  case INTEGER:
   try { //parse for integer
    NumberFormat integerFormat = NumberFormat.getIntegerInstance();
    Number parse = integerFormat.parse(s);
    this.writeString(name + "field", "");
    return parse;
   } catch (ParseException e) {
    this.writeString(name + "field", "Error!");
   }
   break;
  case PERCENT:
   try { //percent
    NumberFormat percentFormat = NumberFormat.getPercentInstance();
    Number parse = percentFormat.parse(s);
    this.writeString(name + "field", "");
    return parse;
   } catch (ParseException e) {
    this.writeString(name + "field", "Error!");
   }
   break;
  case STRING: //String if empty returns error
   this.writeString(name + "field", "");
   if (s == null || s.isEmpty()){
    this.writeString(name + "field", "Error!");
    return null;
   }
   return s;
  default:
   return null;
  }
  return null;
 }

 public String readText(String name) { //reads Textarea from BasicIO
  char c = this.readC(name);
  int ch = c;
  String s = "";
  while (ch <= 128 && ch >= 0) { //ASCII 
   s += Character.toString(c);
   c = this.readC(name);
   ch = c;
  }
  if (s == null || s.isEmpty()){
   this.writeString(name + "field", "Error!");
   return null;
  }else{
   this.writeString(name + "field", "");
  }
  return s;
 }
}