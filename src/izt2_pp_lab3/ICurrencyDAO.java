/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package izt2_pp_lab3;

import javax.sql.DataSource;
import java.util.List;
/**
 *
 * @author jasic
 */
public interface ICurrencyDAO {
    void setDataSource(DataSource ds); // ��������� ����� � �������� 1
    void insert(Currency currency); // ������� ����� ������ 2
    void append(String name, String country, int cost); // ���������� ����� ������ 3
    void deleteByCountry(String country); // �������� ������ �� ������� 4
    void deleteByName(String name); //13
    void deleteByCost(int cost); //14
    void delete(String name, String country); // �������� ������ � ��������� ������ � �������� 5
    void deleteAll(); // �������� ���� ������ 6
    void update(String oldCountry, String newCountry); // ��������� ������� � ������� 7
    void updateName(String oldName, String newName);
    Currency findByCost(int cost); // ��������� ������ � �������� ��������� 8
    List<Currency> findByName(String name); // ��������� ������� � �������� ������ 9 
    List<Currency> findByCountry(String country); //12
    List<Currency> select(String name, String country); // ��������� ������� � �������� ������ � �������� 10
    List<Currency> selectCost(int ScannedCost); //13
    List<Currency> costSelect (int scannedCostMax, int scannedCostMax2);
    List<Currency> selectAll(); // ��������� ���� ������� 11
    
}
