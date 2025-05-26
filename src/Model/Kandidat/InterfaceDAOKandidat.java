/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandidat;

import java.util.List;

/**
 *
 * @author Lab Informatika
 */

public interface InterfaceDAOKandidat {
    public void insert(ModelKandidat kandidat);
    public void update(ModelKandidat kandidat);
    public void delete(int id);
    public List<ModelKandidat> getAll();
}
