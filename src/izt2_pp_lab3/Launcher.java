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
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // �������� ����� � ������

            CurrencyDAO currencyDAO = (CurrencyDAO) context.getBean("customerDAO"); // �������� ���� ������� � ������� �������� 

            currencyDAO.deleteAll(); // �������� ���� �������
            
            Currency currency = new Currency("Usd", "USA", 467); // �������� ������ ������� ������� �������� 
            currencyDAO.insert(currency); // �������� ����� ������ (������) � ������� ��������

            currencyDAO.insert(new Currency("Usd", "UK", 470)); // �������� ����� ������ (������) � ������� ��������
            currencyDAO.insert(new Currency("Rub", "Russia", 7)); // �������� ����� ������ (������) � ������� ��������

            System.out.println("����� �� ����: ");
            Currency currency1 = currencyDAO.findByCost(7); // ����� ������ �� �������� �������
            System.out.println(currency1 != null ? currency1 : "��� ������"); // ����� �� ����� ��������� ������

            currencyDAO.deleteByCountry("ssi"); // �������� ������� �� ��������� �������
            currencyDAO.delete("Usd", "UK"); // �������������� �� ����� � �������

            System.out.println("����� �� ��������� �����: ");
            List<Currency> currencies = currencyDAO.findByName("us"); // ����� ������� �� ��������� �����
            System.out.println(currencies != null ? currencies : "��� ������");
            
            currencyDAO.append("JPY", "Japan", 3); // ��������� �������
            currencyDAO.append("BYN", "Belorus", 189);
            currencyDAO.append("HKD", "HongKong", 61);
            currencyDAO.append("EUR", "Italy", 466);

            currencyDAO.deleteByName("by");
            currencyDAO.deleteByCost(61);
            
            currencyDAO.update("Italy", "France"); // ��������� ������� � �������

            currencyDAO.updateName("JPY", "jpy");
            
            System.out.println("����� �� ��������� ������: ");
            List<Currency> currencies1 = currencyDAO.findByCountry("jap"); // ����� ������� �� ��������� �����
            System.out.println(currencies1 != null ? currencies1 : "��� ������");

            Scanner sc = new Scanner(System.in);
            System.out.print("Name - ");
            String scannedName = sc.nextLine();
            System.out.print("Country - ");
            String scannedCountry = sc.nextLine();
            System.out.print("Cost - ");
            int scannedCost1 = sc.nextInt();
            currencyDAO.append(scannedName, scannedCountry, scannedCost1);
            
            System.out.println("������ � ������� ��:");

            List<Currency> list = currencyDAO.selectAll();
            for (Currency myCurrency : list) {
                System.out.println(myCurrency.getName() + " " + myCurrency.getCountry() + " " + myCurrency.getCost());
            }

            System.out.println("����� ����� � ��������� Eur � ������� France:");

            list = currencyDAO.select("Eur", "France");
            for (Currency myCurrency : list) {
                System.out.println(myCurrency.getName() + " " + myCurrency.getCountry());
            }
            
            System.out.print("������� ����: ");
            int scannedCost = sc.nextInt();
            list = currencyDAO.selectCost(scannedCost);
                        for (Currency myCurrency : list) {
                System.out.println(myCurrency.getName() + " " + myCurrency.getCountry() + " " + myCurrency.getCost());
            }
            
            System.out.println("������� �������� ���");
            System.out.println("���� 1 - ");
            int scannedPriceMax = sc.nextInt();
            System.out.println("���� 2 - ");
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
