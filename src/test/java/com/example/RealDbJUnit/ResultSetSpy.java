package com.example.RealDbJUnit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetSpy {

        private ResultSet resultSet;

        public ResultSetSpy(ResultSet resultSet) {
            this.resultSet = resultSet;
        }

        public int getCount() throws SQLException {
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            return count;
        }

        public ResultSet getRow(int rowNum) throws SQLException {
            resultSet.absolute(rowNum);
            return resultSet;
        }
    }

