package vragen_formulier;

import java.sql.*;

public class Student {

    public static String COMMA = "; ";

    String id;
    String vooropleiding;
    Date gebdatum;
    String medestudent;
    String overig;

    void update(Connection conn) throws Exception {
        String query = "UPDATE klassenindeling.studenten SET Medestudent = ?, Overig = ?, Vooropleiding = ?, Geboortedatum = ? WHERE Studenten_Nummer = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, medestudent);
        preparedStmt.setString(3, vooropleiding);
        preparedStmt.setDate(4, gebdatum);
        preparedStmt.setString(5, id);
        preparedStmt.setString(2, overig);
        

        preparedStmt.executeUpdate();
    }
}
