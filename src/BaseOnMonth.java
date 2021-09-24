
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class BaseOnMonth extends javax.swing.JFrame {

    /**
     * Creates new form BaseOnMonth
     */
    int ID=0;
    int TotalCount=0;
    long Collection = 0;
    public BaseOnMonth() {
         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        setIcon();
        to_date_txt.setBackground(new java.awt.Color(0,0,0,1));
        from_date_txt.setBackground(new java.awt.Color(0,0,0,1));
        total_count_txt.setBackground(new java.awt.Color(0,0,0,1));
        total_collection_txt.setBackground(new java.awt.Color(0,0,0,1));
         ((DefaultTableCellRenderer)federal_table.getDefaultRenderer(Object.class)).setOpaque(false);
        //2
        ((DefaultTableCellRenderer)federal_table1.getDefaultRenderer(Object.class)).setOpaque(false);
        //scrollpanel
        table_scroll.setOpaque(false);
        table_scroll.getViewport().setOpaque(false);
        federal_table.setShowGrid(false);
        //2
        table_scroll1.setOpaque(false);
        table_scroll1.getViewport().setOpaque(false);
        federal_table1.setShowGrid(false);
        Onloadtable1();
        
    }
    //table2
    
     public void onloadTable2(int a){
        Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    conn=JavaConnect.ConnectDB();
    try{
        String sql = "select * from Table2 where ID = '"+a+"'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        federal_table.setModel(DbUtils.resultSetToTableModel(rs));
        /*
            String sql="select * from Table1 where CustomerName ='"+search_Box+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            federal_table1.setModel(DbUtils.resultSetToTableModel(rs));
        */
    }
     catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,"Exception 8 "+e);
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
     //table 1
     public void Onloadtable1(){
          Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    conn=JavaConnect.ConnectDB();
        try
        {
             String sql="select * from Table1";
            //String sql="select * from Billing order by Bill_No";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            federal_table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
      catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,"Something Went Wrong\n Restart The computer\n"+e);
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
      public void setIcon()
    {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_iOS_Photos_48px.png")));
    }
     
     public void totalCount(String from, String to){
         Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    conn=JavaConnect.ConnectDB();
    try{
        String sql = "select count(ID) as total_ID from Table1 where Date between'"+from+"'and'"+to+"'order by Date desc";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
            TotalCount= rs.getInt("total_ID");
            from_date_txt.setText(from);
            to_date_txt.setText(to);
           
            String tolalcount=String.valueOf(TotalCount);
            total_count_txt.setText(tolalcount);
             collectionCount(from,to);
        }
        /*
            int TotalCount=0;
    long Collection = 0;
        select sum(Price) as Price_total from Table2 where ID between 2 and 14;
          String sql="select MAX(ID) as maxLevel from Table1";
            // pst=conn.prepareStatement(sql);
            pst=conn.prepareStatement(sql);
                   //SELECT MAX(Price) AS LargestPrice FROM Products; 
            rs=pst.executeQuery();
            if(rs.next()){
                ID = rs.getInt("maxLevel");
                GreatestID = (ID + 1);
            }
        */
    }
    catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,"Exception 8ik "+e);
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
     
     public void collectionCount(String from, String to){
             Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    conn=JavaConnect.ConnectDB();
    try{
        String sql = "select sum(FinalAmount) as collection from Table1 where Date between'"+from+"'and'"+to+"'order by Date desc";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
            Collection= rs.getInt("collection");
              String collec=String.valueOf(Collection);
            total_collection_txt.setText(collec);
            /*
            
            */
                    }
    }
     catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,"Exception 8ik "+e);
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

     public void deleteTable1(){
           Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        conn=JavaConnect.ConnectDB();
        
        try{
             String sql = "delete from Table1";
        pst=conn.prepareStatement(sql);
         int p=JOptionPane.showConfirmDialog(null,"Do you want to Delete","Conform Delete",JOptionPane.YES_NO_OPTION);

              if(p==0)
                {
                    pst.execute();
                     deleteTable2();
                    JOptionPane.showMessageDialog(null, "Deleted");
                }
              
               /*
               
                        String sql="delete from Student_Entry where P_no='"+searchText+"'";
                        pst=conn.prepareStatement(sql);
                        int p=JOptionPane.showConfirmDialog(null,"Do you want to Delete","Conform Delete",JOptionPane.YES_NO_OPTION);
                        if(p==0)
                        {
                            pst.execute();
                            JOptionPane.showMessageDialog(null, "Deleted");
                        }
               */
           
        }
        catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,"Exception 8ik "+e);
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
     
     public void deleteTable2(){
           Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        conn=JavaConnect.ConnectDB();
     
        try{
            /*
              String sql="delete from Student_Entry";
             pst=conn.prepareStatement(sql);
                             int p=JOptionPane.showConfirmDialog(null,"Do you want to Delete","Conform Delete",JOptionPane.YES_NO_OPTION);

              if(p==0)
                {
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Deleted");
                }
            */
        String sql = "delete from Table2";
        pst=conn.prepareStatement(sql);
         int p=JOptionPane.showConfirmDialog(null,"Do you want to Delete","Conform Delete",JOptionPane.YES_NO_OPTION);

              if(p==0)
                {
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Deleted");
                }
           
        }
        catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null,"Exception 8ik "+e);
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

        table_scroll1 = new javax.swing.JScrollPane();
        federal_table1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        Delete_btn1 = new javax.swing.JButton();
        table_scroll = new javax.swing.JScrollPane();
        federal_table = new javax.swing.JTable();
        back_btn = new javax.swing.JButton();
        log_out = new javax.swing.JButton();
        search_btn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        to_date = new com.toedter.calendar.JDateChooser();
        from_date = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        to_date_txt = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        from_date_txt = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        total_collection_txt = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        total_count_txt = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        view_all_btn = new javax.swing.JButton();
        ChangeNume = new javax.swing.JButton();
        windows_lable = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        DeleteAll = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        login = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        Federal_data_view = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        main_window = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aadarsh Digital (Record On Month)");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_scroll1.setForeground(new java.awt.Color(255, 255, 255));

        federal_table1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 16)); // NOI18N
        federal_table1.setForeground(new java.awt.Color(255, 255, 255));
        federal_table1.setOpaque(false);
        federal_table1.setRowHeight(20);
        federal_table1.setSelectionForeground(new java.awt.Color(0, 255, 51));
        federal_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                federal_table1MouseClicked(evt);
            }
        });
        table_scroll1.setViewportView(federal_table1);

        getContentPane().add(table_scroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 900, 190));

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total Amount Collection");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 560, 180, 20));

        Delete_btn1.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        Delete_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_Delete_Ticket_38px.png"))); // NOI18N
        Delete_btn1.setText("Delete");
        Delete_btn1.setBorder(null);
        Delete_btn1.setOpaque(false);
        Delete_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_btn1ActionPerformed(evt);
            }
        });
        getContentPane().add(Delete_btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 150, 105, 35));

        table_scroll.setForeground(new java.awt.Color(255, 255, 255));

        federal_table.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        federal_table.setForeground(new java.awt.Color(255, 255, 255));
        federal_table.setOpaque(false);
        federal_table.setRowHeight(20);
        federal_table.setSelectionForeground(new java.awt.Color(0, 255, 51));
        table_scroll.setViewportView(federal_table);

        getContentPane().add(table_scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 900, 190));

        back_btn.setBackground(new java.awt.Color(0, 204, 51));
        back_btn.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        back_btn.setText("Back");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });
        getContentPane().add(back_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 10, 80, 30));

        log_out.setBackground(new java.awt.Color(255, 0, 0));
        log_out.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        log_out.setText("Log Out");
        log_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_outActionPerformed(evt);
            }
        });
        getContentPane().add(log_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, -1, -1));

        search_btn.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        search_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_Search_Folder_38px.png"))); // NOI18N
        search_btn.setText("Search");
        search_btn.setBorder(null);
        search_btn.setOpaque(false);
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });
        getContentPane().add(search_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 105, 35));

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Selection Based on Month Basis");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 280, -1));

        jLabel7.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("To");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 50, -1));

        to_date.setOpaque(false);
        getContentPane().add(to_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 150, -1));

        from_date.setOpaque(false);
        getContentPane().add(from_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 150, -1));

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("From");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 50, -1));

        jLabel9.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("From");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 50, -1));

        jLabel10.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("From");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 430, 100, 20));

        jLabel11.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("To");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 470, 100, 20));

        jLabel12.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Total Amount Count");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 520, 160, 20));

        to_date_txt.setEditable(false);
        to_date_txt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        to_date_txt.setForeground(new java.awt.Color(255, 255, 255));
        to_date_txt.setToolTipText("");
        to_date_txt.setBorder(null);
        to_date_txt.setOpaque(false);
        to_date_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                to_date_txtKeyTyped(evt);
            }
        });
        getContentPane().add(to_date_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 470, 130, 20));

        jSeparator16.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 490, 130, 10));

        from_date_txt.setEditable(false);
        from_date_txt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        from_date_txt.setForeground(new java.awt.Color(255, 255, 255));
        from_date_txt.setToolTipText("");
        from_date_txt.setBorder(null);
        from_date_txt.setOpaque(false);
        from_date_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                from_date_txtKeyTyped(evt);
            }
        });
        getContentPane().add(from_date_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 430, 130, 20));

        jSeparator17.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 450, 130, 10));

        total_collection_txt.setEditable(false);
        total_collection_txt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        total_collection_txt.setForeground(new java.awt.Color(255, 255, 255));
        total_collection_txt.setToolTipText("");
        total_collection_txt.setBorder(null);
        total_collection_txt.setOpaque(false);
        total_collection_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_collection_txtKeyTyped(evt);
            }
        });
        getContentPane().add(total_collection_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 560, 90, 20));

        jSeparator18.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 580, 90, 10));

        total_count_txt.setEditable(false);
        total_count_txt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        total_count_txt.setForeground(new java.awt.Color(255, 255, 255));
        total_count_txt.setToolTipText("");
        total_count_txt.setBorder(null);
        total_count_txt.setOpaque(false);
        total_count_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_count_txtKeyTyped(evt);
            }
        });
        getContentPane().add(total_count_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 520, 90, 20));

        jSeparator19.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 540, 90, 10));

        view_all_btn.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        view_all_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_Documents_Folder_32px.png"))); // NOI18N
        view_all_btn.setText("View All");
        view_all_btn.setBorder(null);
        view_all_btn.setOpaque(false);
        view_all_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_all_btnActionPerformed(evt);
            }
        });
        getContentPane().add(view_all_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 155, 120, 30));

        ChangeNume.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        ChangeNume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_Purchase_Order_35px_1.png"))); // NOI18N
        ChangeNume.setText("Reciept");
        ChangeNume.setBorder(null);
        ChangeNume.setOpaque(false);
        ChangeNume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeNumeActionPerformed(evt);
            }
        });
        getContentPane().add(ChangeNume, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 630, 110, 30));

        windows_lable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/906883.png"))); // NOI18N
        getContentPane().add(windows_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 880));

        DeleteAll.setText("File");
        DeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAllActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Delete All");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        DeleteAll.add(jMenuItem1);
        DeleteAll.add(jSeparator1);

        login.setText("Log In");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        DeleteAll.add(login);
        DeleteAll.add(jSeparator2);

        Federal_data_view.setText("Database");
        Federal_data_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Federal_data_viewActionPerformed(evt);
            }
        });
        DeleteAll.add(Federal_data_view);
        DeleteAll.add(jSeparator3);

        main_window.setText("Main Window");
        main_window.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_windowActionPerformed(evt);
            }
        });
        DeleteAll.add(main_window);

        jMenuBar1.add(DeleteAll);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Delete_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_btn1ActionPerformed
        // TODO add your handling code here:
         if(ID == 0){
              Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Please Select a Record to Delete !");
        }
        else{
            Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        conn=JavaConnect1.ConnectDB();
        try{
            String sql = "delete from Table1 where ID='"+ID+"'";
            pst=conn.prepareStatement(sql);
            
            int p=JOptionPane.showConfirmDialog(null,"Do you want to Delete","Conform Delete",JOptionPane.YES_NO_OPTION);
                        if(p==0)
                        {
                            pst.execute();
                            JOptionPane.showMessageDialog(null, "Deleted");
                            Onloadtable1();
                            ID=0;
                            onloadTable2(ID);
                            
                        }
            /*
            DELETE FROM buildings 
WHERE building_no = 2;
            -*************************-
             String sql="delete from Student_Entry where P_no='"+searchText+"'";
                        pst=conn.prepareStatement(sql);
                        int p=JOptionPane.showConfirmDialog(null,"Do you want to Delete","Conform Delete",JOptionPane.YES_NO_OPTION);
                        if(p==0)
                        {
                            pst.execute();
                            JOptionPane.showMessageDialog(null, "Deleted");
                        }
            */
          
        }
        catch( Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Sorry Error Occured\n" + ex);
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
    }//GEN-LAST:event_Delete_btn1ActionPerformed

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        // TODO add your handling code here
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        conn=JavaConnect.ConnectDB();
        String from=((JTextField)from_date.getDateEditor().getUiComponent()).getText();
        String to= ((JTextField)to_date.getDateEditor().getUiComponent()).getText();
        if( "".equals(from) && "".equals(to) )
        {
             JOptionPane.showMessageDialog(null, "Make Sure you have Selected the Date !","Info",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
               String sql = "select * from Table1 where Date between'"+from+"'and'"+to+"'"; 
               
               pst = conn.prepareStatement(sql);
               rs=pst.executeQuery();
                federal_table1.setModel(DbUtils.resultSetToTableModel(rs));
                totalCount(from,to);
                /*
             String sql="select * from Table1 where CustomerName ='"+search_Box+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            federal_table1.setModel(DbUtils.resultSetToTableModel(rs));
            */
             /*
          String sql="select * from Customer_detail where Date between'"+ ((JTextField)From_date_Customer.getDateEditor().getUiComponent()).getText()+"'and'"+((JTextField)to_date_Customer.getDateEditor().getUiComponent()).getText()+"'order by Date desc";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            Customer_table_Report.setModel(DbUtils.resultSetToTableModel(rs));
        */
               
            }
            catch( HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Sorry Error Occured"+ex);
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
        
       
    }//GEN-LAST:event_search_btnActionPerformed

    private void federal_table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_federal_table1MouseClicked
        // TODO add your handling code here:
         Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
     conn=JavaConnect.ConnectDB();
        try{
            int row = federal_table1.getSelectedRow();
         String Table_Click=(federal_table1.getModel().getValueAt(row,0).toString());
         String sql = "select * from table1 where ID ='"+Table_Click+"'";
         pst = conn.prepareStatement(sql);
         rs = pst.executeQuery();
         if(rs.next()){
             ID= rs.getInt("ID");
             onloadTable2(ID);
             
         }
        }
        catch( HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Sorry Error Occured");
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
        
    }//GEN-LAST:event_federal_table1MouseClicked

    private void to_date_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_to_date_txtKeyTyped
        // TODO add your handling code here:
        /* char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }*/
    }//GEN-LAST:event_to_date_txtKeyTyped

    private void from_date_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_from_date_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_from_date_txtKeyTyped

    private void total_collection_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_collection_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_total_collection_txtKeyTyped

    private void total_count_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_count_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_total_count_txtKeyTyped

    private void view_all_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_all_btnActionPerformed
        // TODO add your handling code here:
        Onloadtable1();
        
        onloadTable2(ID);
    }//GEN-LAST:event_view_all_btnActionPerformed

    private void DeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_DeleteAllActionPerformed

    private void Federal_data_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Federal_data_viewActionPerformed
        // TODO add your handling code here:
        new Federal_Data_view().setVisible(true);
        dispose();
    }//GEN-LAST:event_Federal_data_viewActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
         new Login().setVisible(true);
        dispose();
      
    }//GEN-LAST:event_loginActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
          deleteTable1();
         // new Main_Activity().insertByDefault();
         Onloadtable1();
        onloadTable2(ID);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void main_windowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_windowActionPerformed
        // TODO add your handling code here:
        new Main_Acti().setVisible(true);
        dispose();
        
    }//GEN-LAST:event_main_windowActionPerformed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        // TODO add your handling code here:
        new Main_Acti().setVisible(true);
        dispose();
    }//GEN-LAST:event_back_btnActionPerformed

    private void log_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_outActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_log_outActionPerformed

    private void ChangeNumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeNumeActionPerformed
        // TODO add your handling code here:
        String a,b,c,d;
        a=from_date_txt.getText();
        b=to_date_txt.getText();
        c=total_count_txt.getText();
        d=total_collection_txt.getText();
        new CatlogPrint(a,b,c,d).setVisible(true);
        
    }//GEN-LAST:event_ChangeNumeActionPerformed

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
            java.util.logging.Logger.getLogger(BaseOnMonth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaseOnMonth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaseOnMonth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaseOnMonth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaseOnMonth().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChangeNume;
    private javax.swing.JMenu DeleteAll;
    private javax.swing.JButton Delete_btn1;
    private javax.swing.JMenuItem Federal_data_view;
    private javax.swing.JButton back_btn;
    private javax.swing.JTable federal_table;
    private javax.swing.JTable federal_table1;
    private com.toedter.calendar.JDateChooser from_date;
    private javax.swing.JTextField from_date_txt;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JButton log_out;
    private javax.swing.JMenuItem login;
    private javax.swing.JMenuItem main_window;
    private javax.swing.JButton search_btn;
    private javax.swing.JScrollPane table_scroll;
    private javax.swing.JScrollPane table_scroll1;
    private com.toedter.calendar.JDateChooser to_date;
    private javax.swing.JTextField to_date_txt;
    private javax.swing.JTextField total_collection_txt;
    private javax.swing.JTextField total_count_txt;
    private javax.swing.JButton view_all_btn;
    private javax.swing.JLabel windows_lable;
    // End of variables declaration//GEN-END:variables
}
