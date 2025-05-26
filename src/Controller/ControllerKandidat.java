/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Kandidat.*;
import View.Kandidat.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lab Informatika
 */
public class ControllerKandidat {
    ViewData halamanTable;
    InterfaceDAOKandidat daoKandidat;
    
    List<ModelKandidat>daftarKandidat;
    public ControllerKandidat(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoKandidat = new DAOKandidat();
    }

    public void showAllKandidat(){
        daftarKandidat = daoKandidat.getAll();
        ModelTable table = new ModelTable(daftarKandidat);
        halamanTable.getTableKandidat().setModel(table);
    }
    
    public void InsertKandidat () {
        try {
            ModelKandidat kandidatBaru = new ModelKandidat();
            String nama = halamanTable.getInputNama();
            String path = halamanTable.getInputPath();
            String writing = halamanTable.getInputWriting();
            String coding = halamanTable.getInputCoding();
            String interview = halamanTable.getInputInterview();
            
            Integer score = (int) ((kandidatBaru.getWriting() + kandidatBaru.getCoding() + kandidatBaru.getInterview()) / 3.0);
            kandidatBaru.setScore(score);
                    
            String status = score > 85 ? "Diterima" : "Tidak Diterima";
            
            kandidatBaru.setStatus(status);
            
            if("".equals(nama) || "".equals(path)) {
                throw new Exception("Nama atau Path tidak boleh kosong!");
            }
            
            kandidatBaru.setNama(nama);
            kandidatBaru.setPath(path);
            
            daoKandidat.insert(kandidatBaru);
            JOptionPane.showMessageDialog(null, "Kandidat baru telah berhasil ditambahkan");
            halamanTable.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());
        }
    }
    
    public void EditKandidat () {
        try {
            ModelKandidat kandidatYangMauDiedit = new ModelKandidat();
            String nama = halamanTable.getInputNama();
            String path = halamanTable.getInputPath();
            String writing = halamanTable.getInputWriting();
            String coding = halamanTable.getInputCoding();
            String interview = halamanTable.getInputInterview();
            
            if("".equals(nama) || "".equals(path)) {
                throw new Exception("Nama atau Path tidak boleh kosong!");
            }
            
            kandidatYangMauDiedit.setId(id);
            kandidatYangMauDiedit.setNama(nama);
            kandidatYangMauDiedit.setPath(path);
            kandidatYangMauDiedit.setWriting(writing);
            kandidatYangMauDiedit.setCoding(coding);
            kandidatYangMauDiedit.setInterview(interview);
            
            daoKandidat.update(kandidatYangMauDiedit);
            JOptionPane.showMessageDialog(null, "Data Kandidat Berhasil Diubah");
            halamanTable.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());
        }
    }
    
    public void DeleteKandidat (Integer Baris) {
        Integer id = (int) halamanTable.getTableKandidat().getValueAt(baris, 0);
        String nama = halamanTable.getTableKandidat().getValueAt(Baris, 1).toString();
        int input = JOptionPane.showConfirmDialog(null, 
                "Hapus" + nama + "?",
                "Hapus Kandidat",
                JOptionPane.YES_NO_OPTION);
        if(input == 0) {
            daoKandidat.delete(id);
            JOptionPane.showMessageDialog(null, "Berhasil hapus data");
            showAllKandidat();
        }     
    }
}
