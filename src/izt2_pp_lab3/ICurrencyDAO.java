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
    void setDataSource(DataSource ds); // Установка связи с данныими 1
    void insert(Currency currency); // Вставка новой записи 2
    void append(String name, String country, int cost); // Добавление новой записи 3
    void deleteByCountry(String country); // Удаление записи по фамилии 4
    void deleteByName(String name); //13
    void deleteByCost(int cost); //14
    void delete(String name, String country); // Удаление записи с указанным именем и фамилией 5
    void deleteAll(); // Удаление всех запией 6
    void update(String oldCountry, String newCountry); // Изменение записей в таблице 7
    void updateName(String oldName, String newName);
    Currency findByCost(int cost); // Получение записи с заданным возрастом 8
    List<Currency> findByName(String name); // Получение записей с заданным именем 9 
    List<Currency> findByCountry(String country); //12
    List<Currency> select(String name, String country); // Получение записей с заданным именем и фамилией 10
    List<Currency> selectCost(int ScannedCost); //13
    List<Currency> costSelect (int scannedCostMax, int scannedCostMax2);
    List<Currency> selectAll(); // Получение всех записей 11
    
}
