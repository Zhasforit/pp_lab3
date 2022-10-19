/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package izt2_pp_lab3;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jasic
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с бинами

            CurrencyDAO currencyDAO = (CurrencyDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице клиентов 

            currencyDAO.deleteAll(); // Удаление всех записей
            
            Currency currency = new Currency("Usd", "USA", 467); // Создание нового объекта таблицы клиентов 
            currencyDAO.insert(currency); // Вставить новый объект (запись) в таблицу клиентов

            currencyDAO.insert(new Currency("Usd", "UK", 470)); // Вставить новый объект (запись) в таблицу клиентов
            currencyDAO.insert(new Currency("Rub", "Russia", 7)); // Вставить новый объект (запись) в таблицу клиентов

            System.out.println("Поиск по цене: ");
            Currency currency1 = currencyDAO.findByCost(7); // Поиск записи по возрасту клиента
            System.out.println(currency1 != null ? currency1 : "Нет данных"); // Вывод на экран найденной записи

            currencyDAO.deleteByCountry("ssi"); // Удаление записей по фрагменту фамилии
            currencyDAO.delete("Usd", "UK"); // Удалениезаписи пл имени и фамилии

            System.out.println("Поиск по фрагменту имени: ");
            List<Currency> currencies = currencyDAO.findByName("us"); // Поиск записей по фрагменту имени
            System.out.println(currencies != null ? currencies : "Нет данных");
            
            currencyDAO.append("JPY", "Japan", 3); // Добавлние записей
            currencyDAO.append("BYN", "Belorus", 189);
            currencyDAO.append("HKD", "HongKong", 61);
            currencyDAO.append("EUR", "Italy", 466);

            currencyDAO.deleteByName("by");
            currencyDAO.deleteByCost(61);
            
            currencyDAO.update("Italy", "France"); // Изменение записей в таблице

            currencyDAO.updateName("JPY", "jpy");
            
            System.out.println("Поиск по фрагменту страны: ");
            List<Currency> currencies1 = currencyDAO.findByCountry("jap"); // Поиск записей по фрагменту имени
            System.out.println(currencies1 != null ? currencies1 : "Нет данных");

            Scanner sc = new Scanner(System.in);
            System.out.print("Name - ");
            String scannedName = sc.nextLine();
            System.out.print("Country - ");
            String scannedCountry = sc.nextLine();
            System.out.print("Cost - ");
            int scannedCost1 = sc.nextInt();
            currencyDAO.append(scannedName, scannedCountry, scannedCost1);
            
            System.out.println("Данные в таблице БД:");

            List<Currency> list = currencyDAO.selectAll();
            for (Currency myCurrency : list) {
                System.out.println(myCurrency.getName() + " " + myCurrency.getCountry() + " " + myCurrency.getCost());
            }

            System.out.println("Вывод валют с названием Eur и страной France:");

            list = currencyDAO.select("Eur", "France");
            for (Currency myCurrency : list) {
                System.out.println(myCurrency.getName() + " " + myCurrency.getCountry());
            }
            
            System.out.print("Введите цену: ");
            int scannedCost = sc.nextInt();
            list = currencyDAO.selectCost(scannedCost);
                        for (Currency myCurrency : list) {
                System.out.println(myCurrency.getName() + " " + myCurrency.getCountry() + " " + myCurrency.getCost());
            }
            
            System.out.println("Введите диапазон цен");
            System.out.println("Цена 1 - ");
            int scannedPriceMax = sc.nextInt();
            System.out.println("Цена 2 - ");
            int scannedPriceMax2 = sc.nextInt();
            list = currencyDAO.costSelect(scannedPriceMax, scannedPriceMax2);
                        for (Currency myCurrency : list) {
                System.out.println(myCurrency.getName() + " " + myCurrency.getCountry() + " " + myCurrency.getCost());
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
    
}
