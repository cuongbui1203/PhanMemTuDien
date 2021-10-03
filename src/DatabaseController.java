import java.sql.*;
import java.util.ArrayList;

/**
 * class thuc hien viec giao tiep va cac ham can thiet de giao tiep voi database.<br/>
 * database dc su dung la: SQLite.
 */
public class DatabaseController {
    private final String dbname = "THUVIEN";
    private final String c1 = "EN";
    private final String c2 = "VN";
    private String querty;
    private Connection dbconect;
    private Statement stmt;
    private PreparedStatement pstmt;

    public DatabaseController() {
        try {
            String url = "jdbc:sqlite:.\\database\\sanbox";
            dbconect = DriverManager.getConnection(url);
            System.out.println("Connected to Database");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();
        databaseController.close();
    }

    /**
     * <p>
     *     close connect to database.
     * </p>
     */
    public void close() {
        try {
            dbconect.close();
            System.out.println("Disconnected to Database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * Load thu vien tu database ve.
     * @return mang cac Word.
     */
    public ArrayList<Word> getDictionariesFromDatabase() {
        querty = "SELECT " + c1 + "," + c2 + " FROM " + dbname + ";";
        try {
            stmt = dbconect.createStatement();
            ResultSet res = stmt.executeQuery(querty);
            ArrayList<Word> out = new ArrayList<>();
            while (res.next()) {
                Word tem = new Word();
                tem.word_target = res.getString(c1);
                tem.word_explain = res.getString(c2);
                out.add(tem);
            }
            return out;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * Them 1 tu moi vao database.
     * @param w tu can them.
     * @return <p> true neu thuc hien thanh cong.<br/>False neu that bai.</p>
     */
    public boolean addNewWordToDatabase(Word w) {
        querty = "INSERT INTO " + dbname + "(EN,VN) VALUES('" + w.word_target + "','" + w.word_explain + "');";
        try {
            pstmt = dbconect.prepareStatement(querty);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * them mang nhieu tu vao dataBase.
     * @param arr mang chua cac tu can them.
     * @return <p> true neu thuc hien thanh cong.<br/>False neu that bai.</p>
     */
    public boolean addWordsToDatabase(ArrayList<Word> arr) {
        for (Word w : arr) {
            if (!addNewWordToDatabase(w)) return false;
        }
        return true;
    }

    /**
     * xoa 1 tu khoi database.
     * @param s tu can xoa (EN).
     * @return <p> true neu thuc hien thanh cong.<br/>False neu that bai.</p>
     */
    public boolean removeWord(String s) {
        querty = "DELETE FROM " + dbname + " WHERE " + c1 + " = '" + s + "';";
        try {
            pstmt = dbconect.prepareStatement(querty);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * chinh sua 1 tu da co tu truoc.
     * @param w tu dc sua.
     * @param id id cua tu can sua.
     * @return <p> true neu thuc hien thanh cong.<br/>False neu that bai.</p>
     */
    public boolean repairWord(Word w, int id) {
        querty = "UPDATE " + dbname
                + "SET " + c1 + " = " + w.word_target
                + ", " + c2 + " = " + w.word_explain
                + "WHERE ID = " + id + ";";
        try {

            pstmt = dbconect.prepareStatement(querty);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * lay Word tu dataBase.
     */
    public void getWord() {
        querty = "select " + c1 + "," + c2 + " from " + dbname + ";";
        try {
            stmt = dbconect.createStatement();
            ResultSet res = stmt.executeQuery(querty);
            while (res.next())
                System.out.println(res.getString(c1) + "  " + res.getString(c2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
