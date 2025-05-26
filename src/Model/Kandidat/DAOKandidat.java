/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandidat;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOKandidat implements InterfaceDAOKandidat {

    @Override
    public void insert(ModelKandidat kandidat) {
       try {  
            String query = "INSERT INTO kandidat (nama, path) VALUES (?, ?);";

            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, kandidat.getNama());
            statement.setString(2, kandidat.getPath());
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }

    @Override
    public void update(ModelKandidat kandidat) {
        try {
            String query = "UPDATE kandidat SET nama=?, path=?, writing=?, coding=?, interview=? WHERE id=?;";

            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, kandidat.getNama());
            statement.setString(2, kandidat.getPath());
            statement.setInt(3, kandidat.getWriting());
            statement.setInt(4, kandidat.getCoding());
            statement.setInt(5, kandidat.getInterview());
            statement.setInt(6, kandidat.getId());
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM kandidat WHERE id=?;";

            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelKandidat> getAll() {
        List<ModelKandidat> listKandidat = null;

        try {

            listKandidat = new ArrayList<>();
            
            Statement statement = Connector.Connect().createStatement();

            String query = "SELECT * FROM kandidat;";
            
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ModelKandidat knd = new ModelKandidat();
                
                knd.setId(resultSet.getInt("id"));
                knd.setNama(resultSet.getString("nama"));
                knd.setPath(resultSet.getString("path"));

                listKandidat.add(knd);
            }
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listKandidat;
    }
}
