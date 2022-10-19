/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package izt2_pp_lab3;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author jasic
 */
public class CurrencyRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PersonResultSetExtractor extractor = new PersonResultSetExtractor();
        return extractor.extractData(rs);
    }

    /**
     * Класс загрузки данных в объект данных из считанной записи таблицы
     *
     */
    class PersonResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Currency currency = new Currency();
            currency.setId(rs.getInt("id"));
            currency.setName(rs.getString("name"));
            currency.setCountry(rs.getString("country"));
            currency.setCost(rs.getInt("cost"));
            return currency;
        }
    }
}
