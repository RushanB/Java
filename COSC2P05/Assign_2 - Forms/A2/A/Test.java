package A;

public class Test { //tests CheckedForm for Part A

 public static void main(String[] args) {
  CheckedForm form = new CheckedForm(); //initialize form
  form.addField("dateTest", "Date", Type.DATE);
  form.addField("timeTest", "Time", Type.TIME);
  form.addField("currencyTest", "Currency", Type.CURRENCY);
  form.addField("decimalTest", "Decimal", Type.DECIMAL);
  form.addField("integerTest", "Integer", Type.INTEGER);
  form.addField("percentTest", "Percent", Type.PERCENT);
  form.addText("areaTest", "Text");
  while (true) {
   int button = form.accept("OK", "Quit");
   if (button == 0) {
    form.readField("dateTest"); //reads fields
    form.readField("timeTest");
    form.readField("currencyTest");
    form.readField("decimalTest");
    form.readField("integerTest");
    form.readField("percentTest");
    form.readField("stringTest");
    String s = form.readText("areaTest");
    form.clear("areaTest");
    if (s != null){
     form.writeString("areaTest", s);
    }
   }else{
    System.exit(0);
   }
  }

 }
}