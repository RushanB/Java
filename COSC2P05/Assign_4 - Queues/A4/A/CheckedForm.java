package A;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import java.util.*;
import A.Type;

public class CheckedForm extends JFrame implements ActionListener, FocusListener{
 JButton accept = new JButton("Accept"); //instance variables
 JButton quit = new JButton("Quit");
 JPanel unchecked;
 JPanel checked;
 Boolean input = false;
 Boolean ok = false;
 Boolean exit = false; 
 Node type;
 TextNode textField;
 Thread thread;

 public CheckedForm(String n, Color c, int x, int y){ //constructor method
  super(n);
  type = null;
  this.setLocation(x, y);
  this.getContentPane().setBackground(c);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(400,300);
  this.setResizable(false);
  setLayout(new BorderLayout());

  JPanel keys = new JPanel(); //accept and quit keys
  keys.setLayout(new FlowLayout());
  accept.addActionListener(new validate());
  quit.addActionListener(new validate());
  keys.add(accept); //adds accept button
  keys.add(quit); //adds quit button
  this.add(keys,BorderLayout.SOUTH);
  this.setVisible(true);

  GridLayout g = new GridLayout(0,4); 
  checked = new JPanel(); //checked
  checked.setLayout(g);
  this.add(checked,BorderLayout.NORTH);

  FlowLayout f = new FlowLayout();
  unchecked = new JPanel(); //unchecked
  unchecked.setLayout(f);
  this.add(unchecked,BorderLayout.CENTER);
 }

 public class Node{ //stores data for each JTextField within CheckedForm
  JTextField field; //instance variables
  String name;
  A.Type kind;
  Node next;
  int size;

  public Node(JTextField field, A.Type kind, String name, Node next){
   this.field = field; //constructors
   this.kind = kind;
   this.name = name;
   this.next = next;
   size = 1;
  }
  public void setSize(int size){ //sets the size of the node
   this.size = size;
  }
  public int getSize(){ //returns the size of the node
   return size;
  }
 }

 public class TextNode{  //stores information for each JTextArea within CheckedForm
  JTextArea area; //instance variables
  String name;
  TextNode next;
  int size;

  TextNode(JTextArea area, String name, TextNode next){
   this.area = area; //constructor method
   this.name = name;
   this.next = next;
   size = 1;
  }
  public void setSize(int size){ //sets size of the text node
   this.size = size;
  }
  public int getSize(int size){ //returns the size of the text node
   return size; 
  }
 }
 
 public JTextField addField(String label,A.Type kind){ //adds a new JTextField to the form
  JTextField field = new JTextField();
  if(kind == A.Type.DATE){
   checked.add(new JLabel(label));
   checked.add(field);
  }
  if(kind == A.Type.TIME){
   checked.add(new JLabel(label));
   checked.add(field);
  }
  if(kind == A.Type.CURRENCY){
   checked.add(new JLabel(label));
   checked.add(field);
  }
  if(kind == A.Type.DECIMAL){
   checked.add(new JLabel(label));
   checked.add(field);
  }
  if(kind == A.Type.INTEGER){
   checked.add(new JLabel(label));
   checked.add(field);
  }
  if(kind == A.Type.PERCENT){
   checked.add(new JLabel(label));
   checked.add(field);
  }
  field.addActionListener(this);
  field.addFocusListener(this);
  fieldNode(field,label,kind);
  this.setVisible(true);

  return field; //references object
 }

 private void fieldNode(JTextField field, String name, A.Type kind){ //adds a field based on JTextField
  if(type == null){
   type = new Node(field,kind,name,null);
  }else{
   Node a = type;
   Node b = null;
   while(a != null){
    b = a;
    a = a.next;
   }
   b.next = new Node(field,kind,name,null);
   type.size++;   
  }
 }
 
 private JTextField readField(String string){ //referencing JTextField
  Node a = type;
  while((a != null) && (a.name.compareTo(string) !=0)){
   a = a.next;
  }
  if(a == null){
   throw new NoSuchElementException();
  }else{
   return a.field;
  }
 }

 private void areaNode(JTextArea area, String name){ //method for referencing JTextArea
  if(textField == null){
   textField = new TextNode(area,name,null);
  }else{
   TextNode a = textField;
   TextNode b = null;
   while(a != null){
    b = a;
    a = a.next;
   }
   b.next = new TextNode(area,name,null);
   textField.size++;
  }
 }

 public JTextArea addArea(String label){ //adds new JTextArea to the form
  JTextArea area = new JTextArea(5,30);
  unchecked.add(new JLabel(label));
  unchecked.add(area);
  this.setVisible(true);
  areaNode(area,label);
  return area;
 }

 public Boolean ifEmpty(){ //Check if fields are empty, true or false if occupied
  Node a = type;
  while(a != null){
   if(a.field.getText().compareTo("") == 0){
    return true;
   }
   a = a.next;
  }
  return false;
 }

 private boolean checkForm(String name, A.Type kind){ //method checks each field, red for incorrect and black for correct
  DateFormat dateCheck = DateFormat.getDateInstance(DateFormat.SHORT);
  DateFormat timeCheck = DateFormat.getTimeInstance(DateFormat.SHORT);
  NumberFormat currencyCheck = NumberFormat.getNumberInstance();
  NumberFormat integerCheck = NumberFormat.getIntegerInstance();
  NumberFormat percentCheck = NumberFormat.getPercentInstance();
  DecimalFormat decimalCheck = new DecimalFormat("###,###,###,###.00");
  Boolean formatCheck = true;
  if(kind == A.Type.DATE){
   try{
    dateCheck.parse(readField(name).getText());
    readField(name).setForeground(Color.BLACK);
   }catch(ParseException e){
    readField(name).setForeground(Color.RED);
    formatCheck = false;
   }
  }
  if(kind == A.Type.TIME){
   try{
    timeCheck.parse(readField(name).getText());
    readField(name).setForeground(Color.BLACK);
   }catch(ParseException e){
    readField(name).setForeground(Color.RED);
    formatCheck = false;
   }
  }
  if(kind == A.Type.CURRENCY){
   try{
    currencyCheck.parse(readField(name).getText());
    readField(name).setForeground(Color.BLACK);
   }catch(ParseException e){
    readField(name).setForeground(Color.RED);
    formatCheck = false;
   }
  }
  if(kind == A.Type.DECIMAL){
   try{
    decimalCheck.parse(readField(name).getText());
    readField(name).setForeground(Color.BLACK);
   }catch(ParseException e){
    readField(name).setForeground(Color.RED);
    formatCheck = false;
   }
  }
  if(kind == A.Type.INTEGER){
   try{
    integerCheck.parse(readField(name).getText());
    readField(name).setForeground(Color.BLACK);
   }catch(ParseException e){
    readField(name).setForeground(Color.RED);
    formatCheck = false;
   }
  }
  if(kind == A.Type.PERCENT){
   try{
    percentCheck.parse(readField(name).getText());
    readField(name).setForeground(Color.BLACK);
   }catch(ParseException e){
    readField(name).setForeground(Color.RED);
    formatCheck = false;
   }
  }
  if((formatCheck) && (!ifEmpty())){
   System.out.println("Form analyzed, no errors found");
   return true;
  }else{
   System.out.println("Form analyzed, errors are highlighted in red");
   return false;
  }  
 }
 @Override
 public void focusGained(FocusEvent e) { //FocusListener interface method used to show where user is on the form
  //Nothing 
 }

 @Override
 public void focusLost(FocusEvent e) { //FocusListener interface method used to show when user is not on the form
  Node a = type;
  while(e.getSource() != a.field){
   a = a.next;
  }
  input = checkForm(a.name,a.kind);
 }
 
 @Override
 public void actionPerformed(ActionEvent e){ //when enter is pressed checkForm for errors, from ActionListener interface
  Node a = type;
  while(e.getSource() != a.field){
   a = a.next;
  }
  input = checkForm(a.name,a.kind);
 }
 
 public boolean ok(){ //accept form
  return ok;
 }
 
 public boolean exit(){ //quit form
  return exit;
 }
 
 public void setInput(boolean truth){  //sets input value as truth to override form input as optional
  input = truth;
 }
 
 public void setThread(Thread t){ //thread support
  thread = t;
 }
 
 public void reset(){ //resets all data in the forms for multiple data entries after OK is pressed
  Node a = type;
  while(a != null){
   a.field.setText("");
   a = a.next;
  }
  TextNode b = textField;
  while(b != null){
   b.area.setText("");
   b = b.next;
  }
 }
 
 public void resetForm(){ //clear form
  input = false;
  ok = false; 
 }
 
 class validate implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e){
   //System.out.println(thread.getName());
   if(e.getSource().equals(accept)){
    if(input){
     ok = true;
     input = false;
    }
   }else if(e.getSource().equals(quit)){
    System.out.println("Exiting Form"); //exit
    exit = true;
    if(thread.getName().compareTo("Thread-5") == 0){
     thread.interrupt();
     System.out.println("Form is now closed");  //print exit statement to console
    }
   }
  }
 }
}

