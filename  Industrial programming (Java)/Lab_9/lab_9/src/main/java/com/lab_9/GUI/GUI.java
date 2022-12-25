package com.lab_9.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI {
    private static GUI instance = null;

    private final static String[] ColumnNames = { "Email for varification", "Is email correct"};
    private static final String[][] table = new String[0][2];

    private static final JFrame jFrame = new JFrame("Email varificator");

    private static final JButton exitButton = new JButton(new ExitAction());
    private static final JButton addButton = new JButton(new AddAction());
    private static final JButton showTableButton = new JButton(new ShowTableAction());
    private static final JButton hideTableButton = new JButton(new HideTableAction());

    private static final JLabel welcomeLabel = new JLabel("Welcome to my Email varificator!", SwingConstants.CENTER);
    private static final JLabel tableLabel = new JLabel("Here are the Emails you checked", SwingConstants.CENTER);

    private static final DefaultTableModel tableModel = new DefaultTableModel(table, ColumnNames);
    private static final JTable jTable = new JTable(tableModel) {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false;
        }
    };
    private static final JScrollPane jScrollPane= new JScrollPane(jTable);

    private static final MyJPanel jPanel = new MyJPanel();

    private static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private GUI() {}

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }

    private static class MyJPanel extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            this.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());

            jScrollPane.setBounds(250, 125, this.getWidth() - 500, this.getHeight() - 250);

            exitButton.setBounds(this.getWidth() - 150, this.getHeight() - 100, 80, 40);
            addButton.setBounds(this.getX() + 50, this.getHeight() - 100, 120, 40);
            showTableButton.setBounds(this.getWidth() / 2 - 60, this.getHeight() - 100, 120, 40);
            hideTableButton.setBounds(this.getWidth() / 2 - 60, this.getHeight() - 100, 120, 40);

            welcomeLabel.setBounds(this.getWidth() / 2 - 150, 20, 320, 40);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            tableLabel.setBounds(250, 80, this.getWidth() - 500, 40);
            tableLabel.setFont(new Font("Arial", Font.BOLD, 20));

            setLayout(null);
        }
    }

    public void createGUI() {
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBounds(dimension.width / 2 - 600, dimension.height / 2 - 300, 1200, 600);
        jFrame.setMinimumSize(new Dimension(1200, 600));

        addButton.setVisible(false);
        hideTableButton.setVisible(false);
        exitButton.setText("Exit");
        addButton.setText("Add email");
        showTableButton.setText("Show table");
        hideTableButton.setText("Hide table");

        tableLabel.setVisible(false);

        jScrollPane.setVisible(false);

        // ArrayList<String> emails = ;
        // for (String truck : emails) {
        //     tableModel.addRow(new String[] {
        //             truck.getModel(),
        //             Double.toString(truck.getCapacity()),
        //             truck.getColor(),
        //             Double.toString(truck.getWeight()),
        //             Double.toString(truck.getCost())
        //     });
        // }

        jFrame.add(exitButton);
        jFrame.add(addButton);
        jFrame.add(showTableButton);
        jFrame.add(hideTableButton);
        jFrame.add(welcomeLabel);
        jFrame.add(tableLabel);
        jFrame.add(jScrollPane);
        jFrame.add(jPanel);

        jFrame.setVisible(true);
    }

    private static class ExitAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private static class ShowTableAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            jScrollPane.setVisible(true);
            tableLabel.setVisible(true);
            showTableButton.setVisible(false);
            hideTableButton.setVisible(true);
            addButton.setVisible(true);
        }
    }

    private static class HideTableAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            jScrollPane.setVisible(false);
            tableLabel.setVisible(false);
            hideTableButton.setVisible(false);
            addButton.setVisible(false);
            showTableButton.setVisible(true);
        }
    }

    public static String isTrue(String text) {
        Pattern pattern = Pattern.compile("^[^z0-9\\.-]+\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){ return "True";} 
        else{ return "False";}
    }

    private static class AddAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog jDialog = new JDialog(jFrame, "Adding email for check", true);
            JPanel jPanel1 = new JPanel();
            jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jDialog.setResizable(false);
            jDialog.setBounds(dimension.width / 2 - 300, dimension.height / 2 - 200, 600, 200);

            jPanel1.setLayout(null);
            jPanel1.setBounds(0, 0, 600, 200);

            JButton jDialogExitButton = new JButton();
            jDialogExitButton.setBounds(430, 100, 120, 40);
            jDialogExitButton.setText("Cancel");

            JTextField emailField = new JTextField();
            emailField.setBounds(100, 38, 300, 20);

            JLabel emailLabel = new JLabel("Enter email: ");
            emailLabel.setBounds(20, 20, 80, 50);

            JButton jDialogAddButton = new JButton();
            jDialogAddButton.addActionListener(e1 -> {
                tableModel.addRow(new String[] {
                    emailField.getText(),
                    isTrue(emailField.getText())
                });

                jDialog.dispatchEvent(new WindowEvent(jDialog, WindowEvent.WINDOW_CLOSING));
            });
            jDialogAddButton.setBounds(70, 100, 120, 40);
            jDialogAddButton.setText("Add email");

            jPanel1.add(emailLabel);
            jPanel1.add(emailField);
            jPanel1.add(jDialogExitButton);
            jPanel1.add(jDialogAddButton);

            jDialog.add(jPanel1);
            jDialog.setLayout(null);
            jDialog.setVisible(true);
        }
    }
}