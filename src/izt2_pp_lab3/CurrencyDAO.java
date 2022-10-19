/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package izt2_pp_lab3;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.util.List;

/**
 *
 * @author jasic
 */
public class CurrencyDAO implements ICurrencyDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Currency currency) { // ���������� ������� ����� ������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO CURRENCY (NAME, COUNTRY, COST) VALUES(?,?,?)",
                new Object[]{currency.getName(), currency.getCountry(), currency.getCost()});
    }

    @Override
    public void append(String name, String country, int cost) {  // ���������� ���������� ����� ������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO CURRENCY (NAME, COUNTRY, COST) VALUES(?,?,?)", 
                new Object[]{name, country, cost});
    }

    @Override
    public void deleteByCountry(String country) {  // ���������� �������� ������� �� �������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM CURRENCY WHERE COUNTRY LIKE ?", new Object[]{'%' + country + '%'});
    }

    @Override
    public void delete(final String name, final String country) {  // ���������� �������� ������� � ��������� ������ � ��������
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from CURRENCY where NAME= ? AND COUNTRY = ?", new Object[]{name, country});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // ���������� �������� ���� ������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE from CURRENCY");
    }

    @Override
    public void update(String oldCountry, String newCountry) {  // ��������� ������� � �������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE CURRENCY SET COUNTRY = ? WHERE COUNTRY = ?", new Object[]{newCountry, oldCountry});
    }

    @Override
    public Currency findByCost(int cost) { // ���������� ������ ������ � �������� ���������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Currency> currency = jt.query("SELECT * FROM CURRENCY WHERE COST = ?",
                new Object[]{cost}, new CurrencyRowMapper());
        return currency.size() > 0 ? currency.get(0) : null;
    }

    @Override
    public List<Currency> findByName(String name) {  // ���������� ������ ������� �� �����
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM CURRENCY WHERE NAME LIKE ?";
        List<Currency> currencies = jt.query(sql, new Object[]{'%' + name + '%'}, new CurrencyRowMapper());
        return currencies;
    }

    @Override
    public List<Currency> findByCountry(String country) {  // ���������� ������ ������� �� �����
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM CURRENCY WHERE COUNTRY LIKE ?";
        List<Currency> currencies1 = jt.query(sql, new Object[]{'%' + country + '%'}, new CurrencyRowMapper());
        return currencies1;
    }
    
    @Override
    public List<Currency> select(String name, String country) {  // ���������� ��������� ������� � �������� ������ � ��������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select  * from CURRENCY where NAME = ? AND COUNTRY= ?",
                new Object[]{name, country}, new CurrencyRowMapper());
    }
    
    @Override
    public List<Currency> selectCost(int ScannedCost) {  // ���������� ��������� ������� � �������� ������ � ��������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select  * from CURRENCY where COST = ?",
                new Object[]{ScannedCost}, new CurrencyRowMapper());
    }
    
    @Override
    public List<Currency> costSelect(int scannedPriceMax, int scannedPriceMax2) { // ���������� ������ ������ � ��������� ������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("SELECT * FROM CURRENCY WHERE COST > ? AND COST < ?", 
                new Object[]{scannedPriceMax, scannedPriceMax2}, new CurrencyRowMapper());
    }

    @Override
    public void deleteByName(String name) {  // ���������� �������� ������� �� �������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM CURRENCY WHERE NAME LIKE ?", new Object[]{'%' + name + '%'});
    }
    
    @Override
    public void deleteByCost(int cost) {  // ���������� �������� ������� �� �������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM CURRENCY WHERE COST = ?", new Object[]{cost});
    }
    
    @Override
    public void updateName(String oldName, String newName) {  // ��������� ������� � �������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE CURRENCY SET NAME = ? WHERE NAME = ?", new Object[]{newName, oldName});
    }
    
    @Override
    public List<Currency> selectAll() {  // ���������� ��������� ���� �������
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select * from CURRENCY", new CurrencyRowMapper());
    }
}
