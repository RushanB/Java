import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class A{ //main class

 public static void main(String[] args) {
  ATM atm = new ATM(); //create the ATM
  atm.setSize(700,400); //set size 
  atm.setLocation(100,100); //location
  atm.setVisible(true);  //visible
 }
}

class ATM extends JFrame{ //ATM class
 public static int English = 0;
 private Messages messages; //messages class
 private JPanel languagePanel;
 private JPanel passwordPanel; //prompt for pin
 private JPasswordField password; //pin or password
 private JPanel mainPanel; //choosing account
 private JPanel accountPanel; //savings or chequing Panel
 private JPanel depositPanel;
 private JTextField amountField; //bank amount
 private JPanel nextPanel; //transaction prompt
 private JPanel balancePanel; //account balance
 private JPanel finalPanel; //quit prompt
 private int language = English;
 private int currentOperation; //current operation
 private int currentAccount; //current account
 private BankAccount bankAccount; //bank account

 public ATM(){
  super("ATM");
  messages = new Messages();
  bankAccount = new BankAccount();
  showMenu(null);
  this.addWindowListener(new WindowAdapter(){
   public void windowClosing(WindowEvent e) {
    System.exit(0);
   }
  });
 }

 private void showMenu(JPanel P){ //ATM title 
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  JLabel title = new JLabel();
  title.setText("Bank of St. Kitts");

 // create the label
     JLabel label = new JLabel(messages.getMessage("SELECT_LANG",language)); //label
     JButton eng = new JButton("English"); //english language
     ActionListener list = new ActionListener(){
         public void actionPerformed(ActionEvent e) {
           if(e.getActionCommand().equals("English")){
             ATM.this.language = ATM.English;
           }else if(e.getActionCommand().equals("Cancel")){
               showFinalPanel(languagePanel);
               return;
             }
           pinMenu(languagePanel,"");
         }
       };
           eng.addActionListener(list);
           languagePanel = new JPanel(); //creates the panel
           label.setForeground(Color.BLACK);
           languagePanel.setSize(new Dimension(150,150));
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0; //adds buttons and labels to the panel
      c.gridy = 0;
      c.insets = new Insets(10,10,10,10);
      languagePanel.add(title,c);
      c.gridy = 1;
      c.gridx = 0;
      languagePanel.add(label,c);
      c.gridy = 2;
      languagePanel.add(eng,c);
      c.gridy = 3;
      JButton cancel = new JButton("Cancel");
      cancel.addActionListener(list);
      c.gridy = 5;
      languagePanel.add(cancel,c);
  // add the panel to the main frame
      this.getContentPane().setLayout(new GridBagLayout());
      c.gridx = 1;
      c.gridy = 1;
      c.fill = GridBagConstraints.BOTH;
      c.weightx = 150;
      c.weighty = 150;
      this.getContentPane().add(languagePanel,c);
      this.setVisible(true);
    }

 protected void pinMenu(JPanel P, String errorMessage){ //creates and displays the pin menu
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  JLabel label = new JLabel(messages.getMessage("ENTER_PIN",language));
  password = new JPasswordField(5);
  password.setFocusable(true);
  password.requestFocusInWindow();
  JButton ok = new JButton(messages.getMessage("OK",language));
  ActionListener list = new ActionListener(){
   public void actionPerformed(ActionEvent e){
    String pin = new String(ATM.this.password.getPassword());
    if(pin.equals("1234")){
     mainMenu(passwordPanel);
    }else{
     pinMenu(passwordPanel,"Error");
    }
   }
  };
  ok.addActionListener(list); //cancel
  JButton cancel = new JButton(messages.getMessage("CANCEL",language));
  cancel.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e){
    showMenu(passwordPanel);
   }
  });
  passwordPanel = new JPanel(); //password panel
  label.setForeground(Color.BLACK);
  passwordPanel.setSize(new Dimension(150,150));
  passwordPanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  c.gridx = 1;
  c.gridy = 0;
  c.insets = new Insets(10,10,10,10); //adds labels and buttons
  if(!errorMessage.equals("")){
   JLabel error = new JLabel(messages.getMessage("INCORRECT_PIN",language));
   error.setForeground(Color.red);
   passwordPanel.add(error,c);
  }
  c.gridy = 1;
  passwordPanel.add(label,c); //password
  c.gridy = 2;
  passwordPanel.add(password,c);
  c.gridx = 2;
  c.gridy = 3;
  passwordPanel.add(ok,c); //ok button
  c.gridx = 0;
  passwordPanel.add(cancel,c); //cancel button
  this.getContentPane().setLayout(new GridBagLayout());
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(passwordPanel,c); //gets password panel
  password.setFocusable(true);
  password.requestFocusInWindow();
  getRootPane().setDefaultButton(ok);
  this.setVisible(true);
 }

 protected void mainMenu(JPanel P){
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  ActionListener list = new ActionListener(){ //action listener gets button values
   public void actionPerformed(ActionEvent e){
    String command = e.getActionCommand();
    if(command.equals(messages.getMessage("DEPOSIT",language))){ 
     showAccount(mainPanel,0);
    }else if(command.equals(messages.getMessage("WITHDRAWAL",language))){ 
     showAccount(mainPanel,1);
    }else if(command.equals(messages.getMessage("BALANCE",language))){ 
     showAccount(mainPanel,2);
    }else if(command.equals(messages.getMessage("TRANSFER",language))){ 
     showAccount(mainPanel,3);
    }else if(command.equals(messages.getMessage("CANCEL",language))){ 
     showFinalPanel(mainPanel);
    }
   }
  };
  JLabel label = new JLabel(messages.getMessage("TRANSACTION",language)); //transaction button
  JButton deposit = new JButton(messages.getMessage("MAKE_DEPOSIT",language)); //deposit button
  deposit.addActionListener(list);
  JButton withdrawal = new JButton(messages.getMessage("WITHDRAWAL",language)); //withdrawal button
  withdrawal.addActionListener(list);
  JButton balance = new JButton(messages.getMessage("GET_BALANCE",language)); //balance button
  balance.addActionListener(list);
  JButton transfer = new JButton(messages.getMessage("TRANSFER",language)); //transfer button
  transfer.addActionListener(list);
  JButton cancel = new JButton(messages.getMessage("CANCEL",language)); //cancel button
  cancel.addActionListener(list);
  mainPanel = new JPanel(); //main panel
  label.setForeground(Color.BLACK);
  mainPanel.setSize(new Dimension(150,150));
  mainPanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints(); //add label/ buttons to panel
  c.gridx = 1;
  c.gridy = 0;
  c.insets = new Insets(10,10,10,10);
  mainPanel.add(label,c); 
  c.gridy = 2;
  mainPanel.add(deposit,c); //deposit 
  c.gridy = 3;
  mainPanel.add(withdrawal,c); //withdrawal
  c.gridy = 4;
  mainPanel.add(balance,c); //balance
  c.gridy = 5;
  mainPanel.add(transfer,c); //transfer
  c.gridy = 6;
  mainPanel.add(cancel,c); //cancel
  this.getContentPane().setLayout(new GridBagLayout()); //add panel to main frame
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(mainPanel,c);
  this.setVisible(true);
 }

 public void showAccount(JPanel P, int operation){ //display frame where user selects the type of account
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  currentOperation = operation;
  ActionListener list = new ActionListener(){ //action listener gets button values
   public void actionPerformed(ActionEvent e){
    String command = e.getActionCommand();
    int account = 0;
    if(command.equals(messages.getMessage("CHEQUING",language))){
     account = 0;
    }else if(command.equals(messages.getMessage("SAVINGS",language))){
     account = 1;
    }else if(command.equals(messages.getMessage("FROM_CHEQUING",language))){
     account = 0;
    }else if(command.equals(messages.getMessage("FROM_SAVINGS",language))){
     account = 1;
    }else if(command.equals(messages.getMessage("CANCEL",language))){
     mainMenu(accountPanel);
     return;
    }
    if(ATM.this.currentOperation == 0)
     showDeposit(accountPanel,account);
    else if(ATM.this.currentOperation == 1)
     showWithdrawal(accountPanel,account);
    else if(ATM.this.currentOperation == 2)
     showBalance(accountPanel,account);
    else if(ATM.this.currentOperation == 3)
     showTransfer(accountPanel,account);
   }
  };
  JLabel label= new JLabel(messages.getMessage("SELECT_ACCOUNT",language));
  JButton chequing = new JButton(messages.getMessage("CHEQUING",language));
  if(operation == 3){
   chequing.setText(messages.getMessage("FROM_CHEQUING",language));
  }
  chequing.addActionListener(list);
  JButton savings = new JButton(messages.getMessage("SAVINGS",language));
  if(operation == 3){
   savings.setText(messages.getMessage("FROM_SAVINGS",language));
  }
  savings.addActionListener(list);
  JButton cancel = new JButton(messages.getMessage("CANCEL",language));
  cancel.addActionListener(list);
  accountPanel = new JPanel(); //the panel
  label.setForeground(Color.BLACK);
  accountPanel.setSize(new Dimension(150,150));
  accountPanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints(); 
  c.gridx = 1;
  c.gridy = 0;
  c.insets = new Insets(10,10,10,10);
  accountPanel.add(label,c);
  c.gridy = 2;
  accountPanel.add(chequing,c);
  c.gridy = 3;
  accountPanel.add(savings,c);
  c.gridy = 4;
  accountPanel.add(cancel,c);
  this.getContentPane().setLayout(new GridBagLayout()); //panel to the frame
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(accountPanel,c);
  this.setVisible(true);
 }

 protected void showTransfer(JPanel P, int account){
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  currentAccount = account;
  ActionListener list = new ActionListener(){
   public void actionPerformed(ActionEvent e){
    String command = e.getActionCommand();
    int amount = 0;
    if(command.equals("$100")){
     amount = 100;
    }else if(command.equals("$200")){
     amount = 200;
    }else if(command.equals("$300")){
     amount = 300;
    }else if(command.equals("$20")){
     amount = 20;
    }else if(command.equals("$40")){
     amount = 40;
    }else if(command.equals("$80")){
     amount = 80;
    }else if(command.equals(messages.getMessage("OK",language))){
     try{
      amount = Integer.parseInt(ATM.this.amountField.getText());
     }catch(NumberFormatException nf){
      return;
     }
    }else if(command.equals(messages.getMessage("CANCEL",language))){
     mainMenu(depositPanel);
     return;
    }
    if(currentAccount==0){
     bankAccount.setChequingBalance(bankAccount.getChequingBalance()-amount);
     bankAccount.setSavingsBalance(bankAccount.getSavingsBalance()+amount);
    }else if(currentAccount==1){
     bankAccount.setChequingBalance(bankAccount.getChequingBalance()+amount);
     bankAccount.setSavingsBalance(bankAccount.getSavingsBalance()-amount);
    }
    anotherTransaction(depositPanel);
   }
  };
  // create the labels
  JLabel label = new JLabel(messages.getMessage("TRANSFER",language));
  JLabel label1 = new JLabel(messages.getMessage("SELECT_AMOUNT",language));
  // create the buttons
  JButton amount100 = new JButton("$100");
  amount100.addActionListener(list);
  JButton amount200 = new JButton("$200");
  amount200.addActionListener(list);
  JButton amount300 = new JButton("$300");
  amount300.addActionListener(list);
  JButton amount20 = new JButton("$20");
  amount20.addActionListener(list);
  JButton amount40 = new JButton("$40");
  amount40.addActionListener(list);
  JButton amount80 = new JButton("$80");
  amount80.addActionListener(list);
  JButton ok = new JButton(messages.getMessage("OK",language));
  ok.addActionListener(list);
  JButton cancel = new JButton(messages.getMessage("CANCEL",language));
  cancel.addActionListener(list);
  amountField = new JTextField(10);  //user prompt for amount
  depositPanel = new JPanel(); //the panel
  label.setForeground(Color.BLACK);
  depositPanel.setSize(new Dimension(150,150));
  depositPanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints(); //labels + buttons to panel
  c.gridx = 1;
  c.gridy = 0;
  c.insets = new Insets(10,10,10,10);
  depositPanel.add(label,c);
  c.gridx = 1;
  c.gridy = 1;
  depositPanel.add(label,c);
  c.gridx = 0;
  c.gridy = 2;
  depositPanel.add(amount100,c);
  c.gridy = 3;
  depositPanel.add(amount200,c);
  c.gridy = 4;
  depositPanel.add(amount300,c);
  c.gridy = 5;
  depositPanel.add(cancel,c);
  c.gridx = 1;
  c.gridy = 2;
  depositPanel.add(amountField,c);
  c.gridx = 2;
  c.gridy = 2;
  depositPanel.add(amount20,c);
  c.gridy = 3;
  depositPanel.add(amount40,c);
  c.gridy = 4;
  depositPanel.add(amount80,c);
  c.gridy = 5;
  depositPanel.add(ok,c);
  this.getContentPane().setLayout(new GridBagLayout()); //panel to main frame
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(depositPanel,c);
  amountField.setFocusable(true);
  amountField.requestFocusInWindow();
  this.setVisible(true);
 }

 protected void showBalance(JPanel P, int account){
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  JLabel label = new JLabel(messages.getMessage("ACCOUNT_BALANCE",language)
    +((currentAccount==0)?messages.getMessage("CHECQ",language):
     messages.getMessage("SAVIN",language)));
  JButton ok = new JButton(messages.getMessage("OK",language));
  ok.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e) {
    anotherTransaction(balancePanel);
   }
  });
  JTextField amountFieldText = new JTextField(10); //text field displayed in account balance
  if(currentAccount == 0){
   amountFieldText.setText("$ "+bankAccount.getChequingBalance()+" ");
  }else if(currentAccount == 1){
   amountFieldText.setText("$ "+bankAccount.getSavingsBalance()+" ");
  }
  amountFieldText.setEditable(false);
  balancePanel = new JPanel(); //the panel
  label.setForeground(Color.BLACK);
  balancePanel.setSize(new Dimension(150,150));
  balancePanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints(); //label/ buttons
  c.gridx = 1;
  c.gridy = 1;
  c.insets = new Insets(10,10,10,10);
  balancePanel.add(label,c);
  c.gridx = 1;
  c.gridy = 2;
  balancePanel.add(amountFieldText,c);
  c.gridx = 2;
  c.gridy = 3;
  balancePanel.add(ok,c);
  this.getContentPane().setLayout(new GridBagLayout()); //panel to main frame
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(balancePanel,c);
  this.setVisible(true);
 }

 protected void showWithdrawal(JPanel P, int account){
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  ActionListener list = new ActionListener(){ //action listener holds buttons
   public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    int amount = 0;
    if(command.equals("$100")){
     amount = 100;
    }else if(command.equals("$200")){
     amount = 200;
    }else if(command.equals("$300")){
     amount = 300;
    }else if(command.equals("$20")){
     amount = 20;
    }else if(command.equals("$40")){
     amount = 40;
    }else if(command.equals("$80")){
     amount = 80;
    }else if(command.equals(messages.getMessage("OK",language))){
     try{
      amount = Integer.parseInt(ATM.this.amountField.getText());
     }catch(NumberFormatException nf){
      return;
     }
    }else if(command.equals(messages.getMessage("CANCEL",language))){
     mainMenu(depositPanel);
     return;
    }
    if(currentAccount==0){
     bankAccount.setChequingBalance(bankAccount.getChequingBalance()-amount);
    }else if(currentAccount==1){
     bankAccount.setSavingsBalance(bankAccount.getSavingsBalance()-amount);
    }
    anotherTransaction(depositPanel);
   }
  };
  JLabel label = new JLabel(messages.getMessage("WITHDRAWAL",language)); //labels
  JLabel label1 = new JLabel(messages.getMessage("SELECT_AMOUNT",language));
  JButton amount100 = new JButton("$100"); //buttons
  amount100.addActionListener(list);
  JButton amount200 = new JButton("$200");
  amount200.addActionListener(list);
  JButton amount300 = new JButton("$300");
  amount300.addActionListener(list);
  JButton amount20 = new JButton("$20");
  amount20.addActionListener(list);
  JButton amount40 = new JButton("$40");
  amount40.addActionListener(list);
  JButton amount80 = new JButton("$80");
  amount80.addActionListener(list);
  JButton ok = new JButton(messages.getMessage("OK",language));
  ok.addActionListener(list);
  JButton cancel = new JButton(messages.getMessage("CANCEL",language));
  cancel.addActionListener(list);
  amountField = new JTextField(10); //text field for user input
  depositPanel = new JPanel(); //panel
  label.setForeground(Color.BLACK);
  label1.setForeground(Color.BLACK);
  depositPanel.setSize(new Dimension(150,150));
  depositPanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints(); //labels + buttons to panel
  c.gridx = 1; 
  c.gridy = 0;
  c.insets = new Insets(10,10,10,10);
  depositPanel.add(label,c);
  c.gridx = 1;
  c.gridy = 1;
  depositPanel.add(label1,c);
  c.gridx = 0;
  c.gridy = 2;
  depositPanel.add(amount100,c);
  c.gridy = 3;
  depositPanel.add(amount200,c);
  c.gridy = 4;
  depositPanel.add(amount300,c);
  c.gridy = 5;
  depositPanel.add(cancel,c);
  c.gridx = 1;
  c.gridy = 2;
  depositPanel.add(amountField,c);
  c.gridx = 2;
  c.gridy = 2;
  depositPanel.add(amount20,c);
  c.gridy = 3;
  depositPanel.add(amount40,c);
  c.gridy = 4;
  depositPanel.add(amount80,c);
  c.gridy = 5;
  depositPanel.add(ok,c);
  this.getContentPane().setLayout(new GridBagLayout()); //panel to main frame
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(depositPanel,c);
  amountField.requestFocusInWindow();
  this.setVisible(true);
 }

 protected void showDeposit(JPanel P, int account){
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  currentAccount = account;
  ActionListener list = new ActionListener(){
   public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    int amount = 0;
    if(command.equals("$100")){
     amount = 100;
    }else if(command.equals("$200")){
     amount = 200;
    }else if(command.equals("$300")){
     amount = 300;
    }else if(command.equals("$20")){
     amount = 20;
    }else if(command.equals("$40")){
     amount = 40;
    }else if(command.equals("$80")){
     amount = 80;
    }else if(command.equals(messages.getMessage("OK",language))){
     try{
      amount = Integer.parseInt(ATM.this.amountField.getText());
     }catch(NumberFormatException nf){
      return;
     }
    }else if(command.equals(messages.getMessage("CANCEL",language))){
     mainMenu(depositPanel);
     return;
    }
    if(currentAccount==0){
     bankAccount.setChequingBalance(bankAccount.getChequingBalance()+amount);
    }else if(currentAccount==1){
     bankAccount.setSavingsBalance(bankAccount.getSavingsBalance()+amount);
    }
    anotherTransaction(depositPanel);
   }
  };
  JLabel label = new JLabel(messages.getMessage("MAKE_DEPOSIT",language));
  JLabel label1 = new JLabel(messages.getMessage("SELECT_AMOUNT",language)); //labels
  JButton amount100 = new JButton("$100"); //buttons
  amount100.addActionListener(list);
  JButton amount200 = new JButton("$200");
  amount200.addActionListener(list);
  JButton amount300 = new JButton("$300");
  amount300.addActionListener(list);
  JButton amount20 = new JButton("$20");
  amount20.addActionListener(list);
  JButton amount40 = new JButton("$40");
  amount40.addActionListener(list);
  JButton amount80 = new JButton("$80");
  amount80.addActionListener(list);
  JButton ok = new JButton(messages.getMessage("OK",language));
  ok.addActionListener(list);
  JButton cancel = new JButton(messages.getMessage("CANCEL",language));
  cancel.addActionListener(list);
  amountField = new JTextField(10); //text field for user input
  depositPanel = new JPanel(); //panel
  label.setForeground(Color.BLACK);
  label1.setForeground(Color.BLACK);
  depositPanel.setSize(new Dimension(150,150));
  depositPanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints(); //buttons + labels to the panel
  c.gridx = 1;
  c.gridy = 0;
  c.insets = new Insets(10,10,10,10);
  depositPanel.add(label,c);
  c.gridx = 1;
  c.gridy = 1;
  depositPanel.add(label1,c);
  c.gridx = 0;
  c.gridy = 2;
  depositPanel.add(amount100,c);
  c.gridy = 3;
  depositPanel.add(amount200,c);
  c.gridy = 4;
  depositPanel.add(amount300,c);
  c.gridy = 5;
  depositPanel.add(cancel,c);
  c.gridx = 1;
  c.gridy = 2;
  depositPanel.add(amountField,c);
  c.gridx = 2;
  c.gridy = 2;
  depositPanel.add(amount20,c);
  c.gridy = 3;
  depositPanel.add(amount40,c);
  c.gridy = 4;
  depositPanel.add(amount80,c);
  c.gridy = 5;
  depositPanel.add(ok,c);
  this.getContentPane().setLayout(new GridBagLayout()); //panel to main frame
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(depositPanel,c);
  amountField.requestFocusInWindow();
  this.setVisible(true);
 }

 protected void anotherTransaction(JPanel P){
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  ActionListener list = new ActionListener(){ //responds to actions of buttons
   public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    if(command.equals(messages.getMessage("OK",language))){
     pinMenu(nextPanel,"");
    }else if(command.equals(messages.getMessage("CANCEL",language))){
     showFinalPanel(nextPanel);
    }
   }
  };
  JLabel label = new JLabel(messages.getMessage("MAKE_NEXT",language)); //labels
  JLabel label1 = new JLabel(messages.getMessage("MAKE_NEXT1",language));
  JButton ok = new JButton(messages.getMessage("OK",language));
  ok.addActionListener(list); //buttons
  JButton cancel = new JButton(messages.getMessage("CANCEL",language));
  cancel.addActionListener(list);
  nextPanel = new JPanel(); //panel
  label.setForeground(Color.BLACK);
  label1.setForeground(Color.BLACK);
  nextPanel.setSize(new Dimension(150,150));
  nextPanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  c.gridx = 1; //add labels + buttons to the panel
  c.gridy = 0;
  c.insets = new Insets(10,10,10,10);
  nextPanel.add(label,c);
  c.gridx = 1;
  c.gridy = 1;
  nextPanel.add(label1,c);
  c.gridx = 0;
  c.gridy = 2;
  nextPanel.add(cancel,c);
  c.gridx = 2;
  c.gridy = 2;
  nextPanel.add(ok,c);
  this.getContentPane().setLayout(new GridBagLayout()); //panel to main frame
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(nextPanel,c);
  this.setVisible(true);
 }

 public void showFinalPanel(JPanel P){
  if(P!=null){
   this.remove(P); //remove previous panel
  }
  JLabel label = new JLabel(messages.getMessage("THANKS,",language));
  finalPanel = new JPanel(); //panel
  label.setForeground(Color.BLACK);
  finalPanel.setSize(new Dimension(150,150));
  finalPanel.setLayout(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  c.gridx = 1; //labels added to the panel
  c.gridy = 0;
  c.insets = new Insets(10,10,10,10);
  finalPanel.add(label,c);
  this.getContentPane().setLayout(new GridBagLayout()); //set panel to the main frame
  c.gridx = 1;
  c.gridy = 1;
  c.fill = GridBagConstraints.BOTH;
  c.weightx = 150;
  c.weighty = 150;
  this.getContentPane().add(finalPanel,c);
  this.setVisible(true);
 } 
}

class BankAccount{
 private int chequingBalance; //chequing account
 private int savingsBalance; //savings account

 public BankAccount(){ //initalize the balance
  chequingBalance = 0;
  savingsBalance = 1000;
 }

 public int getChequingBalance(){ //returns balance
  return chequingBalance;
 }

 public void setChequingBalance(int chequingBalance){ //updater method
  this.chequingBalance = chequingBalance;
 }

 public int getSavingsBalance(){ //returns balance
  return savingsBalance;
 }

 public void setSavingsBalance(int savingsBalance){ //updater method
  this.savingsBalance = savingsBalance;
 }
}

class Messages{
 private ArrayList messages; //list of objects

 public Messages(){
  messages = new ArrayList(); //add to array
  addMessages(); 
 }
 
  private String[] ids = {"SELECT_LANG","ENTER_PIN","OK","CANCEL","INCORRECT_PIN",
       "SELECT_TRANSACT","MAKE_DEPOSIT","WITHDRAWAL","GET_BALANCE",
       "TRANSFER","CHEQUING","SAVINGS","SELECT_ACCOUNT",
       "SELECT_AMOUNT","MAKE_NEXT","MAKE_NEXT1",
       "ACCOUNT_BALANCE",
       "CHECQ","SAVIN",
       "FROM_CHEQUING","FROM_SAVINGS",
       "THANKS"};
   // keeps the messages in english
     private String[] englishLanguage = {"Select language","Enter PIN and press Ok","Ok","Cancel","Wrong Pin!",
       "Select transaction","Make deposit","Withdrawal","Get balance",
       "Transfer funds","From Chequing","From Savings","Select an account",
       "Select an amount","Would you like to","do another Transaction?",
       "Your account balance \n for account ",
       "Chequing","Savings",
       "From chequing to savings","From savings to chequing",
       "Thank you for using this ATM"};
 
  private void addMessages() {
      for(int i=0;i<ids.length;i++){
        messages.add(new Message(ids[i],englishLanguage[i],ATM.English));
      }
    }

 public String getMessage(String id, int language){
  for(int i=0; i<messages.size(); i++){
   Message m = (Message)messages.get(i);
       if(m.getId().equals(id) && m.getLanguage() == language){
           return m.getMessage();
       }else if(m.getId().equals(id) && m.getLanguage() == language)
           return m.getMessage();
       }
  return "";
 }
}

class Message{
 private String id; //id of message
 private String message; //message 
 private int language;

 public Message(String id, String message, int language){  //updater method
  this.id = id;
  this.message = message;
  this.language = language;
 }

 public String getId(){ //returns id
  return id;
 }

 public void setId(String id){ //updates id
  this.id = id;
 }
 
 public int getLanguage(){
  return language;
 }
 
 public void setLanguage(int language){
  this.language = language;
 }

 public String getMessage(){ //returns message
  return message;
 }

 public void setMessage(String message){ //updates message
  this.message = message;
 }
}