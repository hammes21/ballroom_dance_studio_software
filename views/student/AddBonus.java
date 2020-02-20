/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.student;

import commons.helpers.GenericHelper;
import commons.helpers.ComboBoxHelper;
import static commons.helpers.ServerHelper.connection;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import commons.helpers.CustomFocusTraversalPolicy;
import static commons.helpers.NavHelper.studentID;
import static commons.helpers.NavHelper.studentType;
import java.awt.HeadlessException;

/**
 *
 * @author Akureyri
 */
public class AddBonus extends javax.swing.JDialog {

    private GenericHelper genericHelper = new GenericHelper();
    java.awt.Frame parent;

    /**
     * Adds new Bonus Record
     *
     * @param parent
     * @param modal
     * @param studentID
     * @param studentType
     */
    public AddBonus(java.awt.Frame parent) {
        super(parent, true);

        this.parent = parent;

        try {
            BufferedImage favicon = ImageIO.read(getClass().getResource("/resources/icon16.png"));
            setIconImage(favicon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle("Add Bonus");
        initComponents();
        
        // Set submit button
        getRootPane().setDefaultButton(addBonusBtn);

    }

    // Set Tab Traversal Policy Order
    private void setCustomTraversalPolicy() {

        // Create ordered list of components
        ArrayList<Component> componentArrayList = new ArrayList<Component>();
        componentArrayList.add(amountInput);
        componentArrayList.add(bonusTypeSelect);
        componentArrayList.add(lessonTypeSelect);
        componentArrayList.add(addBonusBtn);
        componentArrayList.add(cancelBtn);

        // Create new custom traversal policy and apply to panel
        CustomFocusTraversalPolicy policy = new CustomFocusTraversalPolicy(componentArrayList);
        mainPanel.setFocusTraversalPolicyProvider(true);
        mainPanel.setFocusTraversalPolicy(policy);

    }

    // Connects to DB and adds student
    private void insertNewBonus() throws ClassNotFoundException, SQLException {

        // Verify input is filled in
        if (amountInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Bonus Amount.",
                    "Fill required Fields", JOptionPane.WARNING_MESSAGE);
        } else {

            // Get variables from input
            String amountText = amountInput.getText();
            double amount = 0.0;
            if (genericHelper.isNumericString(amountText)) {
                amount = Double.parseDouble(amountText);
            }

            String bonusType = (String) bonusTypeSelect.getSelectedItem();
            String lessonType = (String) lessonTypeSelect.getSelectedItem();
            
            try {
                // Create new student
                String bonusTransactionData = String.format("Insert into BonusTransaction(StudentID,UnitsUsed,TransactionType,BonusType,LessonType) "
                        + "Values('%s',%.2f,'ManualUpdate','%s','%s');",
                        studentID, amount, bonusType, lessonType);
                connection.prepareStatement(bonusTransactionData).execute();

                // Alert success and close
                JOptionPane.showMessageDialog(null, "Saved new Bonus", "Saved New Bonus", JOptionPane.INFORMATION_MESSAGE);

                // Close this and reopen student manager
                StudentDetails studentDetails = new StudentDetails();
                studentDetails.setVisible(true);
                parent.dispose();
                this.dispose();

            } catch (SQLException | HeadlessException e) {

                JOptionPane.showMessageDialog(null, "Error adding new Bonus.", "Insert Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
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

        AddReferralTypeDialog = new javax.swing.JDialog();
        mainPanel1 = new javax.swing.JPanel();
        addReferralTypeLabel = new javax.swing.JLabel();
        addReferralTypeBtn = new javax.swing.JButton();
        addReferralTypeCancelBtn = new javax.swing.JButton();
        addReferralTypeInput = new javax.swing.JTextField();
        mainPanel = new javax.swing.JPanel();
        addBonusBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        amountInput = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        bonusTypeSelect = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        lessonTypeSelect = new javax.swing.JComboBox();

        AddReferralTypeDialog.setSize(new java.awt.Dimension(400, 170));
        AddReferralTypeDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Add Referral Type"));
        mainPanel1.setSize(new java.awt.Dimension(370, 140));
        mainPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addReferralTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addReferralTypeLabel.setText("New Referral Type:");
        mainPanel1.add(addReferralTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 10));

        addReferralTypeBtn.setText("Add Referral Type");
        addReferralTypeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReferralTypeBtnActionPerformed(evt);
            }
        });
        mainPanel1.add(addReferralTypeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 170, 40));

        addReferralTypeCancelBtn.setText("Cancel");
        addReferralTypeCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReferralTypeCancelBtnActionPerformed(evt);
            }
        });
        mainPanel1.add(addReferralTypeCancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 100, 40));
        mainPanel1.add(addReferralTypeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 220, 30));

        AddReferralTypeDialog.getContentPane().add(mainPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 170));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addBonusBtn.setText("Add Bonus");
        addBonusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBonusBtnActionPerformed(evt);
            }
        });
        mainPanel.add(addBonusBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 150, 40));

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        mainPanel.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 100, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Bonus Amount:");
        mainPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 10));

        amountInput.setText("0.0");
        mainPanel.add(amountInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 190, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Bonus Type:");
        mainPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 10));

        bonusTypeSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Real", "Pending" }));
        mainPanel.add(bonusTypeSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 190, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Lesson Type:");
        mainPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 10));

        lessonTypeSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Private", "Group", "Party" }));
        mainPanel.add(lessonTypeSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 190, 30));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 400, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBonusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBonusBtnActionPerformed
        // Add new student to the database
        try {
            insertNewBonus();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_addBonusBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // Close this
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void addReferralTypeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReferralTypeBtnActionPerformed

    }//GEN-LAST:event_addReferralTypeBtnActionPerformed

    private void addReferralTypeCancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReferralTypeCancelBtnActionPerformed
        // Close dialog
        AddReferralTypeDialog.dispose();
    }//GEN-LAST:event_addReferralTypeCancelBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AddBonus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBonus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBonus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBonus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddBonus dialog = new AddBonus(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AddReferralTypeDialog;
    private javax.swing.JButton addBonusBtn;
    private javax.swing.JButton addReferralTypeBtn;
    private javax.swing.JButton addReferralTypeCancelBtn;
    private javax.swing.JTextField addReferralTypeInput;
    private javax.swing.JLabel addReferralTypeLabel;
    private javax.swing.JTextField amountInput;
    private javax.swing.JComboBox bonusTypeSelect;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JComboBox lessonTypeSelect;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel mainPanel1;
    // End of variables declaration//GEN-END:variables
}