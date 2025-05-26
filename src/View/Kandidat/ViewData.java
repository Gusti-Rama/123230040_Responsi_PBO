/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Kandidat;

/**
 *
 * @author Lab Informatika
 */
import Controller.ControllerKandidat;
import Model.Kandidat.ModelKandidat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewData extends JFrame {

    Integer baris;

    ControllerKandidat controller;

    JLabel header = new JLabel("Data Kandidat");
    JLabel nameLabel = new JLabel("Name");
    JLabel pathLabel = new JLabel("Path");
    JLabel writingLabel = new JLabel("Writing");
    JLabel codingLabel = new JLabel("Coding");
    JLabel interviewLabel = new JLabel("Interview");
    JTextField nameForm = new JTextField("Name");
    JTextField writingForm = new JTextField("Writing");
    JTextField codingForm = new JTextField("Coding");
    JTextField interviewForm = new JTextField("Interview");
    String[] pilihan ={"Android Developer", "Web Developer"};
    JComboBox<String> pathDropDown = new JComboBox<>(pilihan);
    JButton tombolTambah = new JButton("Add");
    JButton tombolEdit = new JButton("Update");
    JButton tombolHapus = new JButton("Delete");
    JButton tombolKembali = new JButton("Clear");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"Name", "Path", "Writing", "Coding", "Interview", "Score", "Status"};

    public ViewData() {
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        setTitle("Daftar Kandidat");
        setVisible(true);
        setSize(1000, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(header);
        add(scrollPane);
        add(nameLabel);
        add(pathLabel);
        add(writingLabel);
        add(codingLabel);
        add(interviewLabel);
        add(pathDropDown);
        add(nameForm);
        add(writingForm);
        add(codingForm);
        add(interviewForm);
        add(tombolTambah);
        add(tombolEdit);
        add(tombolHapus);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        scrollPane.setBounds(20, 36, 512, 500);
        nameLabel.setBounds(560, 36, 200, 30);
        nameForm.setBounds(560, 70, 200, 30);
        pathLabel.setBounds(560, 100, 200, 30);
        pathDropDown.setBounds(560, 130, 200, 40);
        writingLabel.setBounds(560, 170, 200, 30);
        writingForm.setBounds(560, 200, 200, 30);
        codingLabel.setBounds(560, 230, 200, 30);
        codingForm.setBounds(560, 260, 200, 30);
        interviewLabel.setBounds(560, 290, 200, 30);
        interviewForm.setBounds(560, 320, 200, 30);
        tombolTambah.setBounds(560, 370, 400, 40);
        tombolEdit.setBounds(560, 414, 400, 40);
        tombolHapus.setBounds(560, 458, 400, 40);
        tombolKembali.setBounds(560, 502, 400, 40);

        controller = new ControllerKandidat(this);
        controller.showAllKandidat();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                baris = table.getSelectedRow();
            }
        });

        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.InsertKandidat();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {

                    ModelKandidat kandidatTerpilih = new ModelKandidat();
                    
                    Integer id = (int) table.getValueAt(baris, 0);
                    String nama = table.getValueAt(baris, 1).toString();
                    String path = table.getValueAt(baris, 2).toString();
                    Integer writing = (int) table.getValueAt(baris, 3);
                    Integer coding = (int) table.getValueAt(baris, 4);
                    Integer interview = (int) table.getValueAt(baris, 5);
                    
                    
                    kandidatTerpilih.setId(id);
                    kandidatTerpilih.setNama(nama);
                    kandidatTerpilih.setPath(path);
                    kandidatTerpilih.setWriting(writing);
                    kandidatTerpilih.setCoding(coding);
                    kandidatTerpilih.setInterview(interview);
                    controller.EditKandidat(kandidat.getId());
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });

        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    controller.deleteKandidat(baris);

                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });
        
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    public String getInputNama() {
        return nameForm.getText();
    }

    public String getInputPath() {
        return pathDropDown.getItemAt(0);
    }
    
    public String getInputCoding() {
        return codingForm.getText();
    }
    
    public String getInputWriting() {
        return writingForm.getText();
    }
    
    public String getInputInterview() {
        return interviewForm.getText();
    }
    
    public JTable getTableKandidat() {
        return table;
    }
}
