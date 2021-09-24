
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
public class Federal_Data_view extends javax.swing.JFrame {

    /**
     * Creates new form Federal_Data_view
     */
    int ID=0;
    public Federal_Data_view() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        setIcon();
        //federal_table.setOpaque(false);
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
        //search box
        search_name.setBackground(new java.awt.Color(0,0,0,1));
        Onloadtable1();
        
        
    }
     public void setIcon()
    {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_iOS_Photos_48px.png")));
    }
    //table 2
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
    //sqlite Foreign key compromised
    
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
             JOptionPane.showMessageDialog(null,"Exception 7ik "+e);
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
             JOptionPane.showMessageDialog(null,"Exception 9ik "+e);
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

        table_scroll = new javax.swing.JScrollPane();
        federal_table = new javax.swing.JTable();
        search_name = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        search_btn = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        view_all_btn = new javax.swing.JButton();
        table_scroll1 = new javax.swing.JScrollPane();
        federal_table1 = new javax.swing.JTable();
        delete_btn = new javax.swing.JButton();
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
        setTitle("Aadarsh Digital (Customer Data View)");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_scroll.setForeground(new java.awt.Color(255, 255, 255));

        federal_table.setFont(new java.awt.Font("Tempus Sans ITC", 1, 16)); // NOI18N
        federal_table.setForeground(new java.awt.Color(255, 255, 255));
        federal_table.setOpaque(false);
        federal_table.setSelectionForeground(new java.awt.Color(0, 255, 51));
        table_scroll.setViewportView(federal_table);

        getContentPane().add(table_scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 900, 190));

        search_name.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        search_name.setForeground(new java.awt.Color(255, 255, 255));
        search_name.setToolTipText("Enter Name or Date Here");
        search_name.setBorder(null);
        search_name.setOpaque(false);
        search_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                search_nameKeyTyped(evt);
            }
        });
        getContentPane().add(search_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 290, 20));

        jSeparator16.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 290, 10));

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Search");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 50, -1));

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
        getContentPane().add(search_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 105, 35));

        jButton4.setBackground(new java.awt.Color(0, 204, 51));
        jButton4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 10, 80, 30));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton3.setText("Log Out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, -1, -1));

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

        table_scroll1.setForeground(new java.awt.Color(255, 255, 255));

        federal_table1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 16)); // NOI18N
        federal_table1.setForeground(new java.awt.Color(255, 255, 255));
        federal_table1.setOpaque(false);
        federal_table1.setSelectionForeground(new java.awt.Color(0, 255, 51));
        federal_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                federal_table1MouseClicked(evt);
            }
        });
        table_scroll1.setViewportView(federal_table1);

        getContentPane().add(table_scroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 900, 190));

        delete_btn.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_Delete_Ticket_38px.png"))); // NOI18N
        delete_btn.setText("Delete");
        delete_btn.setBorder(null);
        delete_btn.setOpaque(false);
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });
        getContentPane().add(delete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 150, 105, 35));

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

    private void search_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_nameKeyTyped
        // TODO add your handling code here:
        /* char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)||(c==KeyEvent.VK_DELETE))){
            evt.consume();
        }*/
    }//GEN-LAST:event_search_nameKeyTyped

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        // TODO add your handling code here:
        if(search_name.getText().equals("")){
              Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Please Fill Search Field !");
        }
        else{
            Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        conn=JavaConnect.ConnectDB();
        String search_Box=search_name.getText();
        try{
            String sql="select * from Table1 where CustomerName ='"+search_Box+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            federal_table1.setModel(DbUtils.resultSetToTableModel(rs));
            /*
               String sql="select * from Customer_Bill where Name='"+search_txt.getText()+"'";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            */
            
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
            
        }
         
        
       
    }//GEN-LAST:event_search_btnActionPerformed

    private void view_all_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_all_btnActionPerformed
        // TODO add your handling code here:
        Onloadtable1();
    }//GEN-LAST:event_view_all_btnActionPerformed

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
         /*
           int row=appo_table.getSelectedRow();
            String Table_click=(appo_table.getModel().getValueAt(row,0).toString());
            String sql="select * from Appointment_table where Patient_ID='"+Table_click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next())
            {
                String a=rs.getString("Patient_ID");
                pi_patient_id.setText(a);
         */
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

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
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
        
    }//GEN-LAST:event_delete_btnActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        deleteTable1();
        // new Main_Activity().insertByDefault();
        Onloadtable1();
        onloadTable2(ID);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        dispose();

    }//GEN-LAST:event_loginActionPerformed

    private void Federal_data_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Federal_data_viewActionPerformed
        // TODO add your handling code here:
        new BaseOnMonth().setVisible(true);
        dispose();
    }//GEN-LAST:event_Federal_data_viewActionPerformed

    private void main_windowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_windowActionPerformed
        // TODO add your handling code here:
        new Main_Acti().setVisible(true);
        dispose();

    }//GEN-LAST:event_main_windowActionPerformed

    private void DeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_DeleteAllActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new Main_Acti().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Federal_Data_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Federal_Data_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Federal_Data_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Federal_Data_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Federal_Data_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu DeleteAll;
    private javax.swing.JMenuItem Federal_data_view;
    private javax.swing.JButton delete_btn;
    private javax.swing.JTable federal_table;
    private javax.swing.JTable federal_table1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem login;
    private javax.swing.JMenuItem main_window;
    private javax.swing.JButton search_btn;
    private javax.swing.JTextField search_name;
    private javax.swing.JScrollPane table_scroll;
    private javax.swing.JScrollPane table_scroll1;
    private javax.swing.JButton view_all_btn;
    private javax.swing.JLabel windows_lable;
    // End of variables declaration//GEN-END:variables
}
