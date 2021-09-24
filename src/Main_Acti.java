
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.sun.glass.events.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.Toolkit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Main_Acti extends javax.swing.JFrame {

    /**
     * Creates new form Main_Acti
     */
       int i=1;
    int GreatestID=0;
    boolean Editable=false;
    boolean AddStatus=true;
    String textAreaWork="";
    String textAreaHeader="-#---------------------------------------------------------------#-\n"
            + "\t\t     AADARSH DIGITAL, Jamner City\n"
              
                + "\t\t     Jagannath Market, Jamner\n" 
            + "\t\tProp : Sagar Mali   mobile: 8390348154 (WhatsApp)\n"
                + "-#---------------------------------------------------------------#-";
    //other info
    int Price=0,Expenses=0,Extra_exps=0,Other_exps=0;
    int Total=0,Final_amount=0;
    public Main_Acti() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
          setIcon();
        dbGetMaxFromTable1();
        textArea_txt.append(textAreaHeader);
        cust_name.setBackground(new java.awt.Color(0,0,0,1));
        cust_date.setBackground(new java.awt.Color(0,0,0,1));
        mobile_txt.setBackground(new java.awt.Color(0,0,0,1));
        profession_txt.setBackground(new java.awt.Color(0,0,0,1));
        work_txt.setBackground(new java.awt.Color(0,0,0,1));
        price_txt.setBackground(new java.awt.Color(0,0,0,1));
        total_txt.setBackground(new java.awt.Color(0,0,0,1));
        expen_txt.setBackground(new java.awt.Color(0,0,0,1));
        final_am_txt.setBackground(new java.awt.Color(0,0,0,1));
        quantity_txt.setBackground(new java.awt.Color(0,0,0,1));
        ext_ex_txt.setBackground(new java.awt.Color(0,0,0,1));
        other_ex_txt.setBackground(new java.awt.Color(0,0,0,1));
        textArea_txt.setBackground(new java.awt.Color(0, 0, 0, 1));
        jScrollPane1.getViewport().setOpaque(false);
        
         //editable 
          editable_txt.setForeground(Color.red);
            editable_txt.setText("Edit Disable");
            textArea_txt.setEditable(false);
             Editable=true;
    }
    
     public void setIcon()
    {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_iOS_Photos_48px.png")));
    }
    
     public void resetAll(){
             ((JTextField)cust_date.getDateEditor().getUiComponent()).setText("");
             cust_name.setText("");
             mobile_txt.setText("");
             profession_txt.setText("");
             textArea_txt.setText(textAreaHeader);
             textAreaWork="";
             total_txt.setText("");
             final_am_txt.setText("");
             Price=Expenses=Extra_exps=Other_exps=Total=Final_amount=0;
             i=1;
             addReset();
             
     }
    public void addReset(){
        work_txt.setText("");
        quantity_txt.setText("");
        price_txt.setText("");
        expen_txt.setText("");
        ext_ex_txt.setText("");
        other_ex_txt.setText("");
        total_txt.setText("");
    }
    
    public void dbGetMaxFromTable1(){
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        conn=JavaConnect.ConnectDB();
        String table="Table1";
        int ID=0;
        try{
            //String sql="select MAX(ID) as maxLevel from Table1";
            String sql="select seq from SQLITE_SEQUENCE where name='"+table+"'";
            /*
            String sql ="select seq from SQLITE_SEQUENCE where name= 'Table1'"
            */
            // pst=conn.prepareStatement(sql);
            pst=conn.prepareStatement(sql);
                   //SELECT MAX(Price) AS LargestPrice FROM Products; 
            rs=pst.executeQuery();
            if(rs.next()){
               ID = rs.getInt("seq");
            
                   GreatestID = (ID + 1);
         
                
            }
            System.out.println("your gestest ID is this\t"+ID+"\nGID\t"+GreatestID);
        }
        catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Sorry Error Occured1\n"+ex);
        }
        finally
        {
            try
            {
              if(rs!=null)
              {
                  rs.close();
                  
              }
              if(pst!=null)
              {
                  pst.close();
              }
              if(conn!=null)
              {
                  conn.close();
              }
            }
            catch(SQLException ex)
            {
               JOptionPane.showMessageDialog(null, ex);
            }
        }
        
    }
    
     public void insertIntoTable2(String work,String quantity,String price,String expenses,
            String extraexps,String otherexps,String total){
         Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        conn=JavaConnect.ConnectDB();
        try{
            String sql="insert into Table2(ID, Work, Quantity, Price, Expenses, ExtraExps, OtherExps, Total) values (?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, GreatestID);
            pst.setString(2, work);
            pst.setString(3, quantity);
            pst.setString(4, price);
            if(expenses.equals("")){
                 pst.setString(5, "0");
            }else{
                pst.setString(5, expenses);
            }
            if(extraexps.equals("")){
                pst.setString(6, "0");
            }else{
                pst.setString(6, extraexps);
            }
            if(otherexps.equals("")){
                pst.setString(7, "0");
            }else{
                pst.setString(7, otherexps);
            }
            pst.setString(8, total);
            
            int p=JOptionPane.showConfirmDialog(null,"Do you want to save","Conform Save",JOptionPane.YES_NO_OPTION);
             if(p==0)
             { 
              pst.execute();
             JOptionPane.showMessageDialog(null, "Saved"); 
             }
            
            /*
             String sql="insert into Student_Entry values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1, prn_no.getText());
            pst.setBytes(17, person_image);
             int p=JOptionPane.showConfirmDialog(null,"Do you want to save","Conform Save",JOptionPane.YES_NO_OPTION);
             if(p==0)
             { 
              pst.execute();
             JOptionPane.showMessageDialog(null, "Saved");  
            clearAll();
              
             }
            */
        }
        catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Sorry Error Occured2\n"+ex);
        }
        finally
        {
            try
            {
              if(rs!=null)
              {
                  rs.close();
                  
              }
              if(pst!=null)
              {
                  pst.close();
              }
              if(conn!=null)
              {
                  conn.close();
              }
            }
            catch(SQLException ex)
            {
               JOptionPane.showMessageDialog(null, ex);
            }
        }
       
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        new_btn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cust_name = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        mobile_txt = new javax.swing.JTextField();
        profession_txt = new javax.swing.JTextField();
        work_txt = new javax.swing.JTextField();
        price_txt = new javax.swing.JTextField();
        total_txt = new javax.swing.JTextField();
        expen_txt = new javax.swing.JTextField();
        quantity_txt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ext_ex_txt = new javax.swing.JTextField();
        other_ex_txt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        final_am_txt = new javax.swing.JTextField();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        add_btn = new javax.swing.JButton();
        Save_btn = new javax.swing.JButton();
        cust_date = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        back_btn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea_txt = new javax.swing.JTextArea();
        print_button = new javax.swing.JButton();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator27 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        editable_txt = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Federal_data_view = new javax.swing.JMenuItem();
        main_window = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        changePassword = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aadarsh Digital(Main Activity Window)");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        new_btn.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        new_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/New Topic_48px.png"))); // NOI18N
        new_btn.setText("New");
        new_btn.setBorder(null);
        new_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_btnActionPerformed(evt);
            }
        });
        getContentPane().add(new_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, 30));

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Customer Name");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 120, -1));

        cust_name.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cust_name.setForeground(new java.awt.Color(255, 255, 255));
        cust_name.setBorder(null);
        cust_name.setOpaque(false);
        cust_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cust_nameKeyTyped(evt);
            }
        });
        getContentPane().add(cust_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 290, 20));

        jSeparator16.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 290, 10));

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Mobile");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 70, -1));

        jLabel7.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Profession");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 80, -1));

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Work");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 60, -1));

        jLabel13.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Price");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 70, -1));

        jLabel14.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Total");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 70, -1));

        mobile_txt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        mobile_txt.setForeground(new java.awt.Color(255, 255, 255));
        mobile_txt.setBorder(null);
        mobile_txt.setOpaque(false);
        mobile_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mobile_txtKeyTyped(evt);
            }
        });
        getContentPane().add(mobile_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 290, 20));

        profession_txt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        profession_txt.setForeground(new java.awt.Color(255, 255, 255));
        profession_txt.setBorder(null);
        profession_txt.setOpaque(false);
        profession_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                profession_txtKeyTyped(evt);
            }
        });
        getContentPane().add(profession_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 290, 20));

        work_txt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        work_txt.setForeground(new java.awt.Color(255, 255, 255));
        work_txt.setBorder(null);
        work_txt.setOpaque(false);
        work_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                work_txtKeyTyped(evt);
            }
        });
        getContentPane().add(work_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 290, 20));

        price_txt.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        price_txt.setForeground(new java.awt.Color(255, 255, 255));
        price_txt.setBorder(null);
        price_txt.setOpaque(false);
        price_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                price_txtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                price_txtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                price_txtKeyTyped(evt);
            }
        });
        getContentPane().add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 130, 20));

        total_txt.setEditable(false);
        total_txt.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        total_txt.setForeground(new java.awt.Color(51, 255, 0));
        total_txt.setToolTipText("");
        total_txt.setBorder(null);
        total_txt.setOpaque(false);
        total_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_txtKeyTyped(evt);
            }
        });
        getContentPane().add(total_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 130, 20));

        expen_txt.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        expen_txt.setForeground(new java.awt.Color(255, 255, 255));
        expen_txt.setBorder(null);
        expen_txt.setOpaque(false);
        expen_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                expen_txtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                expen_txtKeyTyped(evt);
            }
        });
        getContentPane().add(expen_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 80, 20));

        quantity_txt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        quantity_txt.setForeground(new java.awt.Color(255, 255, 255));
        quantity_txt.setBorder(null);
        quantity_txt.setOpaque(false);
        quantity_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantity_txtKeyTyped(evt);
            }
        });
        getContentPane().add(quantity_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 140, 20));

        jLabel9.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Quantity");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 110, -1));

        ext_ex_txt.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ext_ex_txt.setForeground(new java.awt.Color(255, 255, 255));
        ext_ex_txt.setBorder(null);
        ext_ex_txt.setOpaque(false);
        ext_ex_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ext_ex_txtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ext_ex_txtKeyTyped(evt);
            }
        });
        getContentPane().add(ext_ex_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 70, 20));

        other_ex_txt.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        other_ex_txt.setForeground(new java.awt.Color(255, 255, 255));
        other_ex_txt.setBorder(null);
        other_ex_txt.setOpaque(false);
        other_ex_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                other_ex_txtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                other_ex_txtKeyTyped(evt);
            }
        });
        getContentPane().add(other_ex_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 250, 70, 20));

        jLabel12.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Extra Exps");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 100, -1));

        jLabel11.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Expenses");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 120, -1));

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Other Exps");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 120, -1));

        final_am_txt.setEditable(false);
        final_am_txt.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        final_am_txt.setForeground(new java.awt.Color(255, 0, 0));
        final_am_txt.setBorder(null);
        final_am_txt.setOpaque(false);
        final_am_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                final_am_txtKeyTyped(evt);
            }
        });
        getContentPane().add(final_am_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 130, 20));

        jSeparator26.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 130, 10));

        jLabel15.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Final Amount");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 100, -1));

        add_btn.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_Input_48px.png"))); // NOI18N
        add_btn.setText("Add");
        add_btn.setBorder(null);
        add_btn.setBorderPainted(false);
        add_btn.setOpaque(false);
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });
        getContentPane().add(add_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 430, 100, 32));

        Save_btn.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        Save_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Opened Folder_48px.png"))); // NOI18N
        Save_btn.setText("Save");
        Save_btn.setBorder(null);
        Save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_btnActionPerformed(evt);
            }
        });
        getContentPane().add(Save_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 360, 100, 32));

        cust_date.setOpaque(false);
        getContentPane().add(cust_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 150, -1));

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Date");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 80, -1));

        back_btn.setBackground(new java.awt.Color(0, 204, 51));
        back_btn.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        back_btn.setText("Back");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });
        getContentPane().add(back_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 10, 80, 30));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton3.setText("Log Out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, -1, -1));

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setAlignmentX(1.0F);
        jScrollPane1.setAlignmentY(1.0F);
        jScrollPane1.setOpaque(false);

        textArea_txt.setColumns(20);
        textArea_txt.setFont(new java.awt.Font("Monospaced", 1, 10)); // NOI18N
        textArea_txt.setRows(5);
        textArea_txt.setAlignmentX(1.0F);
        textArea_txt.setAlignmentY(1.0F);
        textArea_txt.setOpaque(false);
        jScrollPane1.setViewportView(textArea_txt);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 630, 300));

        print_button.setFont(new java.awt.Font("Book Antiqua", 0, 16)); // NOI18N
        print_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_Printer_48px.png"))); // NOI18N
        print_button.setText("Print");
        print_button.setBorder(null);
        print_button.setBorderPainted(false);
        print_button.setOpaque(false);
        print_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(print_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 490, 100, 32));

        jSeparator17.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 290, 10));

        jSeparator18.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 290, 10));

        jSeparator19.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 150, 10));

        jSeparator21.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 130, 10));

        jSeparator22.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 80, 10));

        jSeparator23.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 70, 10));

        jSeparator24.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, 70, 10));

        jSeparator27.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 130, 10));

        jSeparator25.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 130, 10));

        editable_txt.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        editable_txt.setForeground(new java.awt.Color(255, 255, 255));
        editable_txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editable_txt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(editable_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 110, -1));

        jSeparator20.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 290, 10));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setOpaque(false);
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, 130, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/906883.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 880));

        jMenu1.setText("File");
        jMenu1.add(jSeparator1);

        Federal_data_view.setText("Search Database via Dates");
        Federal_data_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Federal_data_viewActionPerformed(evt);
            }
        });
        jMenu1.add(Federal_data_view);

        main_window.setText("Search Database");
        main_window.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_windowActionPerformed(evt);
            }
        });
        jMenu1.add(main_window);
        jMenu1.add(jSeparator2);

        changePassword.setText("Change Password");
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });
        jMenu1.add(changePassword);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem1.setText("PrintBox Toggle");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Federal_data_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Federal_data_viewActionPerformed
        // TODO add your handling code here:
        new BaseOnMonth().setVisible(true);
        dispose();
    }//GEN-LAST:event_Federal_data_viewActionPerformed

    private void main_windowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_windowActionPerformed
        // TODO add your handling code here:
        new Federal_Data_view().setVisible(true);
        dispose();
    }//GEN-LAST:event_main_windowActionPerformed

    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
        // TODO add your handling code here:
        new ChangePassword().setVisible(true);
        dispose();
    }//GEN-LAST:event_changePasswordActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //finish
        if(Editable){
            editable_txt.setForeground(Color.green);
            editable_txt.setText("Editable");
            textArea_txt.setEditable(true);
            Editable=false;

        }
        else{
            editable_txt.setForeground(Color.red);
            editable_txt.setText("Edit Disable");
            textArea_txt.setEditable(false);

            Editable=true;
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void new_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_btnActionPerformed
        // TODO add your handling code here:
        resetAll();
    }//GEN-LAST:event_new_btnActionPerformed

    private void cust_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cust_nameKeyTyped
        // TODO add your handling code here:
        /* char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }*/
    }//GEN-LAST:event_cust_nameKeyTyped

    private void mobile_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mobile_txtKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_mobile_txtKeyTyped

    private void profession_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_profession_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_profession_txtKeyTyped

    private void work_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_work_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_work_txtKeyTyped

    private void price_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_price_txtKeyPressed
        // TODO add your handling code here:

        /*
        Price =Integer.parseInt(number);
        //int i=Integer.parseInt("200");
        System.out.println("your Price is\t"+Price);*/
    }//GEN-LAST:event_price_txtKeyPressed

    private void price_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_price_txtKeyReleased
        // TODO add your handling code here:
        if(price_txt.getText().equals("")){
            Price=0;
            System.out.println("Price is\t"+Price);
            String s=String.valueOf(Price);
            total_txt.setText(s);

        }
        else{
            String number=price_txt.getText();
            System.out.println(number);

            Price = Integer.parseInt(number);
            System.out.println("your price is\t"+Price);
            String s=String.valueOf(Price);
            total_txt.setText(s);
            Total=Integer.parseInt(s);
            System.out.println("Total is"+Total);

        }

    }//GEN-LAST:event_price_txtKeyReleased

    private void price_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_price_txtKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_price_txtKeyTyped

    private void total_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_total_txtKeyTyped

    private void expen_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expen_txtKeyReleased
        // TODO add your handling code here:
        if(price_txt.getText().equals("")){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Invalid Operation !\nPlease Enter Price Value !");
        }
        else{
            if(expen_txt.getText().equals("")){
                Expenses=0;
                System.out.println("Expenses is\t"+Expenses);
                String s=String.valueOf(Price);
                total_txt.setText(s);
                if(Extra_exps != 0 && Expenses == 0 && Other_exps ==0 && Price != 0){
                    Total = Price - Extra_exps;
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Other_exps != 0 && Expenses == 0 && Extra_exps == 0 && Price != 0){
                    Total = Price - Other_exps;
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Extra_exps != 0 && Other_exps != 0 && Expenses == 0 && Price != 0){
                    Total = Price - (Extra_exps + Other_exps);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }

            }
            else{
                String number=expen_txt.getText();
                System.out.println("Expenses\t"+number);
                Expenses = Integer.parseInt(number);
                System.out.println("your Expenses is\t"+Expenses);
                Total= Price - Expenses;
                String s=String.valueOf(Total);
                total_txt.setText(s);
                if( Extra_exps != 0 && Price != 0 && Other_exps == 0){
                    Total = Price - (Expenses + Extra_exps);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Other_exps != 0 && Price != 0 && Extra_exps == 0){
                    Total = Price - (Other_exps + Expenses);
                    String s1= String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Price != 0 && Other_exps != 0 && Extra_exps != 0){
                    Total = Price - (Expenses + Other_exps + Extra_exps);
                    String s1 = String.valueOf(Total);
                    total_txt.setText(s1);
                }
            }
        }

    }//GEN-LAST:event_expen_txtKeyReleased

    private void expen_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expen_txtKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_expen_txtKeyTyped

    private void quantity_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantity_txtKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_quantity_txtKeyTyped

    private void ext_ex_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ext_ex_txtKeyReleased
        // TODO add your handling code here:
        if(price_txt.getText().equals("")){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Invalid Operation !\nPlease Enter Price Value !");
        }
        else{
            if(ext_ex_txt.getText().equals("")){
                Extra_exps=0;
                System.out.println(" Extra Expenses is\t"+Expenses);
                Total= Price - Expenses;
                String s=String.valueOf(Total);
                total_txt.setText(s);
                if(Other_exps != 0 && Extra_exps ==0 && Expenses == 0 && Price != 0){
                    Total = Price - Other_exps;
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if( Extra_exps == 0 && Other_exps == 0 && Expenses != 0 && Price != 0){
                    Total = Price - Expenses;
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Extra_exps == 0 && Expenses != 0 && Other_exps != 0 && Price != 0){
                    Total = Price - (Other_exps + Expenses);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
            }
            else{

                String number=ext_ex_txt.getText();
                System.out.println("EXTRA Expenses\t"+number);
                Extra_exps = Integer.parseInt(number);
                System.out.println("your EXTRA Expenses is\t"+Extra_exps);
                Total= Price - Extra_exps;
                String s=String.valueOf(Total);
                total_txt.setText(s);
                if(Other_exps != 0 && Price != 0 && Expenses == 0){
                    Total = Price - ( Extra_exps + Other_exps);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Price != 0 && Expenses != 0 && Other_exps == 0){
                    Total = Price - (Extra_exps + Expenses);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Price != 0 && Other_exps != 0 && Expenses != 0){
                    Total = Price - (Other_exps + Expenses + Extra_exps);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
            }
        }

    }//GEN-LAST:event_ext_ex_txtKeyReleased

    private void ext_ex_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ext_ex_txtKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_ext_ex_txtKeyTyped

    private void other_ex_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_other_ex_txtKeyReleased
        // TODO add your handling code here:
        if(price_txt.getText().equals("")){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Invalid Operation !\nPlease Enter Price Value !");
        }
        else{
            if(other_ex_txt.getText().equals("")){
                Other_exps=0;
                Total = Price - Extra_exps;
                String s=String.valueOf(Total);
                total_txt.setText(s);
                if(Other_exps == 0 && Extra_exps == 0 && Expenses != 0 && Price != 0){
                    Total = Price - Expenses;
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Other_exps == 0 && Expenses == 0 && Extra_exps != 0 && Price != 0){
                    Total = Price - Extra_exps;
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Other_exps == 0 && Extra_exps != 0 && Expenses != 0 && Price != 0){
                    Total = Price - (Extra_exps + Expenses);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }

                System.out.println("Other Expenses is\t"+Expenses);
                //String s=String.valueOf(Other_exps);
                //total_txt.setText(s);
            }
            else{
                String number=other_ex_txt.getText();
                System.out.println("OTHER Expenses\t"+number);
                Other_exps = Integer.parseInt(number);
                System.out.println("your OTHER Expenses is\t"+Other_exps);
                Total = Price -  Other_exps;
                String s=String.valueOf(Total);
                total_txt.setText(s);
                if(Price != 0 && Expenses != 0 && Extra_exps == 0){
                    Total = Price - (Other_exps + Expenses);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Price != 0 && Extra_exps !=0 && Expenses == 0){
                    Total = Price - (Other_exps + Extra_exps);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
                else if(Price != 0 && Expenses != 0 && Extra_exps != 0){
                    Total = Price - (Other_exps + Extra_exps + Expenses);
                    String s1=String.valueOf(Total);
                    total_txt.setText(s1);
                }
            }
        }

    }//GEN-LAST:event_other_ex_txtKeyReleased

    private void other_ex_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_other_ex_txtKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_other_ex_txtKeyTyped

    private void final_am_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_final_am_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_final_am_txtKeyTyped

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
       

        if("".equals(cust_name.getText()) && "".equals(((JTextField)cust_date.getDateEditor().getUiComponent()).getText()) && "".equals(mobile_txt.getText()) && "".equals(profession_txt.getText())){
            Toolkit.getDefaultToolkit().beep();

            JOptionPane.showMessageDialog(null, "Every Field Should be Filled","information",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String work,quantity,price,expenses,extraexps,otherexps,total;

            work=work_txt.getText();
            quantity=quantity_txt.getText();
            price=price_txt.getText();
            expenses=expen_txt.getText();
            extraexps=ext_ex_txt.getText();
            otherexps=other_ex_txt.getText();
            total=total_txt.getText();
            if ("".equals(work) && "".equals(quantity) && "".equals(price) && "".equals(expenses) && "".equals(extraexps) && "".equals(otherexps) && "".equals(total)){
                JOptionPane.showMessageDialog(null, "Every Field should be filled !","information",JOptionPane.INFORMATION_MESSAGE);
                //            AddStatus=false;
            }
            else{
                dbGetMaxFromTable1();
                insertIntoTable2(work,quantity,price,expenses,extraexps,otherexps,total);
                Final_amount += Total;

                String name=cust_name.getText();//custome

                String mobile=mobile_txt.getText();//mobile numbermount // global int
                String finalAmount=String.valueOf(Final_amount);//Final_ar name
                //String s=String.valueOf(Price);
                final_am_txt.setText(finalAmount);
                String billDate = ((JTextField)cust_date.getDateEditor().getUiComponent()).getText();
                String custDetail= "\nCustName:\t"+name
                + "\t\tDate : "+billDate
                + "\n  Mobile:\t"+mobile;
                String addingvalues=" Work("+work_txt.getText()+"),Quantity("+quantity_txt.getText()
                +"),Price("+Price+"),Expenses("+Expenses+"),ExtraExps("+Extra_exps+
                "),OtherExps("+Other_exps+"),Total("+Total+")";

                String srNo = String.valueOf(i);

                textAreaWork = textAreaWork + "\n" + srNo +")" + addingvalues;

                textArea_txt.setText(textAreaHeader + custDetail
                    + "\n-#---------------------------------------------------------------#-" + textAreaWork +
                    "\n-#---------------------------------------------------------------#-\nTotal Work("+i+")\t\t\t\t     Final Amounnt: "
                    + finalAmount);
                i++;
                addReset();
                Price=Expenses=Extra_exps=Other_exps=Total=0;

            }
        }

      

    }//GEN-LAST:event_add_btnActionPerformed

    private void Save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_btnActionPerformed
        // TODO add your handling code here:
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        conn=JavaConnect.ConnectDB();
        String name,date,mobile,profession,finalamount;
        name=cust_name.getText();
        date=((JTextField)cust_date.getDateEditor().getUiComponent()).getText();
        mobile=mobile_txt.getText();
        profession=profession_txt.getText();
        finalamount=final_am_txt.getText();
        if("".equals(name) && "".equals(date) && "".equals(mobile) && "".equals(profession) && "".equals(finalamount)){
            Toolkit.getDefaultToolkit().beep();

            JOptionPane.showMessageDialog(null, "Every Field Should be Filled","information",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try{
                String sql= "insert into Table1 (Date, CustomerName, Mobile, Profession, FinalAmount) values (?,?,?,?,?)";
                pst=conn.prepareStatement(sql);
                pst.setString(1, date);
                pst.setString(2, name);
                pst.setString(3, mobile);
                pst.setString(4, profession);
                pst.setString(5, finalamount);
                int p=JOptionPane.showConfirmDialog(null,"Do you want to save","Conform Save",JOptionPane.YES_NO_OPTION);
                if(p==0)
                {
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Saved");
                }
                /*
                String sql="insert into Student_Entry values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst=conn.prepareStatement(sql);
                pst.setString(1, prn_no.getText());
                pst.setBytes(17, person_image);
                int p=JOptionPane.showConfirmDialog(null,"Do you want to save","Conform Save",JOptionPane.YES_NO_OPTION);
                if(p==0)
                {
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Saved");
                    clearAll();
                    */
                }
                catch(HeadlessException | SQLException ex){
                    JOptionPane.showMessageDialog(null, "Sorry Error Occured\n"+ex);
                }
                finally
                {
                    try
                    {
                        if(rs!=null)
                        {
                            rs.close();

                        }
                        if(pst!=null)
                        {
                            pst.close();
                        }
                        if(conn!=null)
                        {
                            conn.close();
                        }
                    }
                    catch(SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }

            }

    }//GEN-LAST:event_Save_btnActionPerformed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_back_btnActionPerformed

    private void print_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_buttonActionPerformed
        /* try {
            /*        // TODO add your handling code here:

            print_txt_bill.append("GST No: 27AXPPC6230Q1ZY"
                + "\n\t\t   \tSHREE ENTERPRISES"
                + "\n---------------------------------------------------------------------"
                + "\n\t  SHASHERI NAGAR, JAMNER, Jalgoan, MAHARASTRA, 424206"
                + "\n\t  Mob:(+91) 7507725791, email: amolchavan772@gmail.com"
                + "\n");*/
            /*
            + "\n---------------------------------------------------------------------"
            + "\n\t  SHASHERI NAGAR, JAMNER, Jalgoan, MAHARASTRA, 424206"
            + "\n\t  Mob:(+91) 7507725791, email: amolchavan772@gmail.com"
            + "\n---------------------------------------------------------------------"
            */

            /*textArea_txt.append("\t\tFEDERAL XEROX, Jamner City"
                + "\t\tProp : Sagar Mali"
                + "\t\tJagannath Market"
                + "\t\t----------------------------------");

            textArea_txt.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Main_Activity.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        try
        {
            boolean complete = textArea_txt.print();
            if(complete)
            {
                Toolkit.getDefaultToolkit().beep();

                JOptionPane.showMessageDialog(null, "Printing Done !","information",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Printing Cancel !","Printer",JOptionPane.ERROR_MESSAGE);
            }

        }
        //new Main_Acti().setVisiable(true);
        catch(HeadlessException | PrinterException e)
        {
            //JOptionPane.showMessageDialog(null, e);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Printing Cancel !","Printer",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_print_buttonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Acti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Acti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Acti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Acti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Acti().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Federal_data_view;
    private javax.swing.JButton Save_btn;
    private javax.swing.JButton add_btn;
    private javax.swing.JButton back_btn;
    private javax.swing.JMenuItem changePassword;
    private com.toedter.calendar.JDateChooser cust_date;
    private javax.swing.JTextField cust_name;
    private javax.swing.JLabel editable_txt;
    private javax.swing.JTextField expen_txt;
    private javax.swing.JTextField ext_ex_txt;
    private javax.swing.JTextField final_am_txt;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JMenuItem main_window;
    private javax.swing.JTextField mobile_txt;
    private javax.swing.JButton new_btn;
    private javax.swing.JTextField other_ex_txt;
    private javax.swing.JTextField price_txt;
    private javax.swing.JButton print_button;
    private javax.swing.JTextField profession_txt;
    private javax.swing.JTextField quantity_txt;
    private javax.swing.JTextArea textArea_txt;
    private javax.swing.JTextField total_txt;
    private javax.swing.JTextField work_txt;
    // End of variables declaration//GEN-END:variables

   
}
