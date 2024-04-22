package edu.neu.neuconnect;

import edu.neu.neuconnect.dao.UserDAO;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;

import edu.neu.neuconnect.model.RoleTypes;
import edu.neu.neuconnect.model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NEUConnectDatabaseMigrationApplication {

    private static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
        // Read data FROM csv
        String filePath = "src/main/resources/test-data.xlsx";
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
                Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            readUsers(workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readUsers(Workbook workbook) throws Exception {
        Sheet markets = workbook.getSheetAt(0);
        Iterator<Row> marketSheet = markets.iterator();
        while (marketSheet.hasNext()) {

            Row row = marketSheet.next();

            if (row.getRowNum() == 0) {
                continue;
            }

            String fname = row.getCell(0).getStringCellValue();
            String lname = row.getCell(1).getStringCellValue();
            String gender = row.getCell(2).getStringCellValue();
            Date dob = row.getCell(3).getDateCellValue();
            String username = row.getCell(4).getStringCellValue();
            String password = row.getCell(5).getStringCellValue();
            String nuid = row.getCell(6).getNumericCellValue() + "";
            boolean isVerified = row.getCell(7).getBooleanCellValue();
            RoleTypes role = RoleTypes.valueOf(row.getCell(8).getStringCellValue());
            String aboutMe = row.getCell(9).getStringCellValue();
            User user = new User(fname, lname, gender, dob, username, password, nuid, isVerified, aboutMe, role);
            byte[] bytes = user.getPassword().getBytes();
            user.setPassword(new String(Base64.getEncoder().encode(bytes)));
            userDAO.create(user);

        }
    }

}
