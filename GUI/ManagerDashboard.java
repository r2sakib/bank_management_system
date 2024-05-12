package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import entity.person.*;
import entity.account.*;
import entityList.*;
import GUI.*;
import file.*;

public class ManagerDashboard extends JFrame implements ActionListener {
    JLabel pageL;
	JButton createCustomerBtn, removeCustomerBtn, createAccountBtn, removeAccountBtn, logoutBtn;
    JButton createBankerBtn, removeBankerBtn, editCustomerInfoBtn, editBankerInfoBtn;

    Font font20 = new Font("Inter", Font.PLAIN, 20);
    Font font20b = new Font("Inter", Font.BOLD, 20);
    Font font24b = new Font("Inter", Font.BOLD, 24);
    Font font16 = new Font("Inter", Font.PLAIN, 16);
    Font font16b = new Font("Inter", Font.BOLD, 16);

    CustomerList customerList;
    BankerList bankerList;
    LoginPage login;

	public ManagerDashboard (CustomerList customerList, BankerList bankerList){
        super("Manager Dashboard");
        this.setSize(1200, 800);
        this.setLocation(200, 10);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // SET ICON
        ImageIcon img = new ImageIcon("./resources/logo.png");
        this.setIconImage(img.getImage());

        // LOAD DATA
        FileIO.loadCustomerList(customerList);
		FileIO.loadAccounts(customerList);
		FileIO.loadBankerList(bankerList);

        this.customerList = customerList;
        this.bankerList = bankerList;
        this.login = login;
        
        // Label for page title 
        pageL = new JLabel("Manager Dashboard");
        pageL.setBounds(470, 30, 300, 36);
        pageL.setFont(font24b);
        this.add(pageL);
        
		// Create Customer button 
		createCustomerBtn = new JButton("Create Customer");
        createCustomerBtn.setBounds(100, 120, 210, 50);
        createCustomerBtn.setFont(font20);
        createCustomerBtn.setBackground(Color.BLACK);
        createCustomerBtn.setForeground(Color.WHITE);
        createCustomerBtn.addActionListener(this);
        this.add(createCustomerBtn);

        // Remove Customer button
		removeCustomerBtn = new JButton("Remove Customer");
        removeCustomerBtn.setBounds(355, 120, 210, 50);
        removeCustomerBtn.setFont(font20);
        removeCustomerBtn.setBackground(Color.BLACK);
        removeCustomerBtn.setForeground(Color.WHITE);
        removeCustomerBtn.addActionListener(this);
        this.add(removeCustomerBtn);
		
		
		// Create Account button 
		createAccountBtn = new JButton("Create Account");
        createAccountBtn.setBounds(610, 120, 210, 50);
        createAccountBtn.setFont(font20);
        createAccountBtn.setBackground(Color.BLACK);
        createAccountBtn.setForeground(Color.WHITE);
        createAccountBtn.addActionListener(this);
        this.add(createAccountBtn);
		
		
		// Remove Account button
		removeAccountBtn = new JButton("Remove Account");
        removeAccountBtn.setBounds(870, 120, 210, 50);
        removeAccountBtn.setFont(font20);
        removeAccountBtn.setBackground(Color.BLACK);
        removeAccountBtn.setForeground(Color.WHITE);
        removeAccountBtn.addActionListener(this);
        this.add(removeAccountBtn);
		

        // Create Banker button 
		createBankerBtn = new JButton("Create Banker");
        createBankerBtn.setBounds(100, 210, 210, 50);
        createBankerBtn.setFont(font20);
        createBankerBtn.setBackground(Color.BLACK);
        createBankerBtn.setForeground(Color.WHITE);
        createBankerBtn.addActionListener(this);
        this.add(createBankerBtn);

        // Remove Banker button
		removeBankerBtn = new JButton("Remove Banker");
        removeBankerBtn.setBounds(355, 210, 210, 50);
        removeBankerBtn.setFont(font20);
        removeBankerBtn.setBackground(Color.BLACK);
        removeBankerBtn.setForeground(Color.WHITE);
        removeBankerBtn.addActionListener(this);
        this.add(removeBankerBtn);
		
		
		// Edit Customer Info button 
		editCustomerInfoBtn = new JButton("Edit Customer Info");
        editCustomerInfoBtn.setBounds(610, 210, 210, 50);
        editCustomerInfoBtn.setFont(font20);
        editCustomerInfoBtn.setBackground(Color.BLACK);
        editCustomerInfoBtn.setForeground(Color.WHITE);
        editCustomerInfoBtn.addActionListener(this);
        this.add(editCustomerInfoBtn);
		
		
		// Edit Banker Info button
		editBankerInfoBtn = new JButton("Edit Banker Info");
        editBankerInfoBtn.setBounds(870, 210, 210, 50);
        editBankerInfoBtn.setFont(font20);
        editBankerInfoBtn.setBackground(Color.BLACK);
        editBankerInfoBtn.setForeground(Color.WHITE);
        editBankerInfoBtn.addActionListener(this);
        this.add(editBankerInfoBtn);

	
		// logout button
		logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(30, 30, 110, 40);
        logoutBtn.setFont(font20);
        logoutBtn.setBackground(Color.RED);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.addActionListener(this);
        this.add(logoutBtn);
		
		this.setVisible(true);	
	}


    JLabel secL, nameL, nidL, birthYearL, addressL, mobileNumberL, emailL, jobTitleL;
    JTextField nameT, nidT, birthYearT, addressT, mobileNumberT, emailT, jobTitleT;
    JButton CCBconfirmBtn, cancelBtn, removeConfirmBtn, RCBinitiateBtn;
    JComboBox jobTitleCB;

    String pressedBtn;
    boolean isByNid = true;
	
	public void actionPerformed(ActionEvent evt) {

        if(evt.getSource() == logoutBtn){
            this.dispose();
            login.setVisible(true);
        }

        else if (evt.getSource() == createCustomerBtn || evt.getSource() == createBankerBtn) {
            try {
                createCustomerBtn.removeActionListener(this);
                
                if (evt.getSource() == createCustomerBtn) {
                    createCustomerBtn.setBackground(Color.GRAY);

                    // Section title
                    secL = new JLabel("CREATE NEW CUSTOMER");
                    secL.setBounds(450, 290, 300, 36);
                    secL.setFont(font20b);
                    this.add(secL);

                    pressedBtn = "createCustomer";
                }
                else if (evt.getSource() == createBankerBtn) {
                    createBankerBtn.setBackground(Color.GRAY);

                    // Section title
                    secL = new JLabel("CREATE NEW BANKER");
                    secL.setBounds(470, 290, 300, 36);
                    secL.setFont(font20b);
                    this.add(secL);

                    pressedBtn = "createBanker";
                }
                int vgap = 350;
                int hgapL = 200;
                int hgapR = 650;
                int width = 350;

                // NAME 
                nameL = new JLabel("Name");
                nameL.setBounds(hgapL, vgap, width, 25);
                nameL.setFont(font16b);
                this.add(nameL);

                nameT = new JTextField();
                nameT.setBounds(hgapL, vgap+25, width, 40);
                nameT.setFont(font20);
                this.add(nameT);

                // NID 
                nidL = new JLabel("NID Number");
                nidL.setBounds(hgapR, vgap, width, 25);
                nidL.setFont(font16b);
                this.add(nidL);

                nidT = new JTextField();
                nidT.setBounds(hgapR, vgap+25, width, 40);
                nidT.setFont(font20);
                this.add(nidT);

                vgap += 80;

                // BIRTH YEAR 
                birthYearL = new JLabel("Birth Year");
                birthYearL.setBounds(hgapL, vgap, width, 25);
                birthYearL.setFont(font16b);
                this.add(birthYearL);

                birthYearT = new JTextField();
                birthYearT.setBounds(hgapL, vgap+25, width, 40);
                birthYearT.setFont(font20);
                this.add(birthYearT);

                // MOBILE NUMBER 
                mobileNumberL = new JLabel("Mobile Number");
                mobileNumberL.setBounds(hgapR, vgap, width, 25);
                mobileNumberL.setFont(font16b);
                this.add(mobileNumberL);

                mobileNumberT = new JTextField();
                mobileNumberT.setBounds(hgapR, vgap+25, width, 40);
                mobileNumberT.setFont(font20);
                this.add(mobileNumberT);

                vgap += 80;

                // ADDRESS
                addressL = new JLabel("Address");
                addressL.setBounds(hgapL, vgap, width, 25);
                addressL.setFont(font16b);
                this.add(addressL);

                addressT = new JTextField();
                addressT.setBounds(hgapL, vgap+25, width, 40);
                addressT.setFont(font20);
                this.add(addressT);

                // EMAIL
                emailL = new JLabel("Email");
                emailL.setBounds(hgapR, vgap, width, 25);
                emailL.setFont(font16b);
                this.add(emailL);

                emailT = new JTextField();
                emailT.setBounds(hgapR, vgap+25, width, 40);
                emailT.setFont(font20);
                this.add(emailT);

                if (evt.getSource() == createBankerBtn) {
                    vgap += 80;

                    // JOB TITLE 
                    jobTitleL = new JLabel("Job Title");
                    jobTitleL.setBounds(hgapL, vgap, width, 25);
                    jobTitleL.setFont(font16b);
                    this.add(jobTitleL);

                    // JOB TITLE COMBO BOX
                    jobTitleCB = new JComboBox(new String[]{"Cashier", "Manager"});
                    jobTitleCB.setBounds(hgapL, vgap+25, width, 40);
                    jobTitleCB.setFont(font20);
                    jobTitleCB.setBackground(Color.WHITE);
                    this.add(jobTitleCB);
                }

                vgap += 80;
                CCBconfirmBtn = new JButton("Confirm");
                CCBconfirmBtn.setBounds(hgapL, vgap, width, 40);
                CCBconfirmBtn.setFont(font20);
                CCBconfirmBtn.setBackground(Color.BLUE);
                CCBconfirmBtn.setForeground(Color.WHITE);
                CCBconfirmBtn.addActionListener(this);
                this.add(CCBconfirmBtn);

                cancelBtn = new JButton("Cancel");
                cancelBtn.setBounds(hgapR, vgap, width, 40);
                cancelBtn.setFont(font20);
                cancelBtn.setBackground(Color.RED);
                cancelBtn.setForeground(Color.WHITE);
                cancelBtn.addActionListener(this);
                this.add(cancelBtn);
                
                this.update(getGraphics());
            }
            catch (Exception expt) {
                JOptionPane.showMessageDialog(this, "Error.");
            }
        }

        else if (evt.getSource() == CCBconfirmBtn) {
            try {
                if (pressedBtn == "createCustomer") {
                    String name = nameT.getText();
                    String nid = nidT.getText();
                    int birthYear = Integer.parseInt(birthYearT.getText());
                    String address = addressT.getText();
                    String mobileNumber = mobileNumberT.getText();
                    String email = emailT.getText();

                    Customer customer = new Customer(name, nid, birthYear, address, mobileNumber, email, "1234");
                    customerList.addCustomer(customer);
                    FileIO.writeCustomerList(customerList);

                    nameT.setText("");
                    nidT.setText("");
                    birthYearT.setText("");
                    addressT.setText("");
                    mobileNumberT.setText("");
                    emailT.setText("");
                }
                else if (pressedBtn == "createBanker") {
                    String name = nameT.getText();
                    String nid = nidT.getText();
                    int birthYear = Integer.parseInt(birthYearT.getText());
                    String address = addressT.getText();
                    String mobileNumber = mobileNumberT.getText();
                    String email = emailT.getText();
                    String jobTitle = jobTitleCB.getSelectedItem().toString();

                    Banker banker = new Banker(name, nid, birthYear, address, mobileNumber, email, "1234", jobTitle);
                    bankerList.addBanker(banker);
                    FileIO.writeBankerList(bankerList);
                    
                    jobTitleT.setText("");
                }

                JOptionPane.showMessageDialog(this, "Success.");
                
                
                // this.dispose();
                // ManagerDashboard managerDashboard = new ManagerDashboard(customerList, bankerList);
            }
            catch (Exception expt) {
                JOptionPane.showMessageDialog(this, "Error. Invalid Input.");
                System.out.println("Error [ManagerDashboard: CCBconfirmBtn] " + "\n\n" + expt + "\n\n");
            }
        }

        else if (evt.getSource() == removeCustomerBtn || evt.getSource() == removeBankerBtn) {
            removeCustomerBtn.removeActionListener(this);
            
            if (evt.getSource() == removeCustomerBtn) {
                removeCustomerBtn.setBackground(Color.GRAY);

                // Section title
                secL = new JLabel("REMOVE CUSTOMER");
                secL.setBounds(480, 290, 300, 36);
                secL.setFont(font20b);
                this.add(secL);

                pressedBtn = "removeCustomer";
            }
            else if (evt.getSource() == removeBankerBtn) {
                removeBankerBtn.setBackground(Color.GRAY);

                // Section title
                secL = new JLabel("REMOVE BANKER");
                secL.setBounds(490, 290, 300, 36);
                secL.setFont(font20b);
                this.add(secL);

                pressedBtn = "removeBanker";
            }
            int vgap = 350;
            int hgapL = 230;
            int hgapR = 680;
            int width = 300;

            // NID 
            nidL = new JLabel("NID Number or Email");
            nidL.setBounds(hgapL, vgap, width, 25);
            nidL.setFont(font16b);
            this.add(nidL);

            nidT = new JTextField();
            nidT.setBounds(hgapL, vgap+25, width, 40);
            nidT.setFont(font20);
            this.add(nidT);

            // NAME 
            nameL = new JLabel("Name");
            nameL.setBounds(hgapR, vgap, width, 25);
            nameL.setFont(font16b);
            this.add(nameL);

            nameT = new JTextField();
            nameT.setBounds(hgapR, vgap+25, width, 40);
            nameT.setFont(font20);
            nameT.setEditable(false);
            this.add(nameT);

            // Initiate nameT
            vgap += 100;
            RCBinitiateBtn = new JButton("Initiate");
            RCBinitiateBtn.setBounds(hgapL, vgap, width, 40);
            RCBinitiateBtn.setFont(font20);
            RCBinitiateBtn.setBackground(Color.BLUE);
            RCBinitiateBtn.setForeground(Color.WHITE);
            RCBinitiateBtn.addActionListener(this);
            this.add(RCBinitiateBtn);

            cancelBtn = new JButton("Cancel");
            cancelBtn.setBounds(hgapR, vgap, width, 40);
            cancelBtn.setFont(font20);
            cancelBtn.setBackground(Color.RED);
            cancelBtn.setForeground(Color.WHITE);
            cancelBtn.addActionListener(this);
            this.add(cancelBtn);

            this.update(getGraphics());

        }

        else if (evt.getSource() == RCBinitiateBtn) {
            int vgap = 350;
            int hgapL = 230;
            int hgapR = 680;
            int width = 300;

            String nidEmailT = nidT.getText();
            if (pressedBtn == "removeCustomer") {
                Customer customerByNid = customerList.getCustomerByNid(nidEmailT);
                Customer customerByEmail = customerList.getCustomerByEmail(nidEmailT);

                if (customerByNid == null && customerByEmail == null) {
                    JOptionPane.showMessageDialog(this, "Customer not found");
                    nidT.setText("");
                    return;
                }
                else if (customerByNid != null) {
                    nameT.setText(customerByNid.getName());
                }
                else if (customerByEmail != null) {
                    nameT.setText(customerByEmail.getName());
                    isByNid = false;
                }
            }

            else if (pressedBtn == "removeBanker") {
                Banker bankerByNid = bankerList.getBankerByNid(nidEmailT);
                Banker bankerByEmail = bankerList.getBankerByEmail(nidEmailT);

                if (bankerByNid == null && bankerByEmail == null) {
                    JOptionPane.showMessageDialog(this, "Banker not found");
                    nidT.setText("");
                    return;
                }
                else if (bankerByNid != null) {
                    nameT.setText(bankerByNid.getName());
                }
                else if (bankerByEmail != null) {
                    nameT.setText(bankerByEmail.getName());
                    isByNid = false;
                }
            }

            this.remove(RCBinitiateBtn);
            vgap += 100;

            // Confirm BUTTON
            removeConfirmBtn = new JButton("Confirm");
            removeConfirmBtn.setBounds(hgapL, vgap, width, 40);
            removeConfirmBtn.setFont(font20);
            removeConfirmBtn.setBackground(Color.BLUE);
            removeConfirmBtn.setForeground(Color.WHITE);
            removeConfirmBtn.addActionListener(this);
            this.add(removeConfirmBtn);

            this.update(getGraphics());
        }

        else if (evt.getSource() == removeConfirmBtn) {
            try {
                if (pressedBtn == "removeCustomer") {
                    String nidEmail = nidT.getText();

                    boolean success = false;
                    if (isByNid) {
                        success = customerList.removeCustomerByNid(nidEmail);
                    }
                    else {
                        success = customerList.removeCustomerByEmail(nidEmail);
                    }

                    if (success) {
                        FileIO.writeCustomerList(customerList);
                        FileIO.writeAccounts(customerList);

                        JOptionPane.showMessageDialog(this, "Customer removed successfully");
                        nidT.setText("");
                        nameT.setText("");
                        return;
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Error.");
                    }
                }

                else if (pressedBtn == "removeBanker") {
                    String nidEmail = nidT.getText();

                    boolean success = false;
                    if (isByNid) {
                        success = bankerList.removeBankerByNid(nidEmail);
                    }
                    else {
                        success = bankerList.removeBankerByEmail(nidEmail);
                    }

                    if (success) {
                        FileIO.writeBankerList(bankerList);

                        JOptionPane.showMessageDialog(this, "Banker removed successfully");
                        nidT.setText("");
                        nameT.setText("");
                        return;
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Banker not found");
                    }
                }
            } catch (Exception expt) {
                JOptionPane.showMessageDialog(this, "Error.");
            }


        }

        else if (evt.getSource() == cancelBtn) {
            this.dispose();
            ManagerDashboard managerDashboard = new ManagerDashboard(customerList, bankerList);
        }
    
    }

}