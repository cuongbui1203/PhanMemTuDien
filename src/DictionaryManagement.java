import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class quan ly tu dien
 */
public class DictionaryManagement {
    private final String url;
    Dictionary dictionary;
    private final CreateFile createFile;

    /**
     * default constructor
     */
    DictionaryManagement() {
        dictionary = new Dictionary();
        createFile = new CreateFile();
//        CreateFile.createFile1(CreateFile.getWordFromFile());
        url = "E:\\!!!hoc_tap\\lap_Trinh\\java\\PMtudien\\src\\dictionaries.txt";
    }

    /**
     * tim tu
     * @param word tu can tim
     * @return vi tri neu tim dc k thi -1
     */
    private int lookup(String word) {
        for (int i = 0; i < dictionary.numOfWord(); i++) {
            if (word.equals(dictionary.getWord(i).word_target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Hien thi toan bo tu dien theo dang
     *
     * No | English   |Vietnamese
     */
    public void showAllWord() {
        int n = dictionary.numOfWord();
        String format = "%-3s| %-10s| %-10s%n";
        System.out.printf(format, "No", "English", "Vietnamese");
        for (int i = 0; i < n; i++) {
            System.out.printf(format, i + 1, dictionary.getWord(i).word_target, dictionary.getWord(i).word_explain);
        }
    }

    /**
     * them tu vao tu dien tu commandline
     * @param sc Scaner truyen vao
     */
    public void insertFromCommandline(Scanner sc) {
        int n;
        System.out.print("nhap so luong tu them vao: ");
        n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            Word w = new Word();
            w.word_target = sc.nextLine();
            w.word_explain = sc.nextLine();
            dictionary.addWord(w);
        }
    }

    /**
     * them tu vao tu dien tu file
     * @throws FileNotFoundException k doc dc file
     */
    public void insertFromFile() throws FileNotFoundException {
        // Đọc dữ liệu từ File với Scanner
        FileInputStream fileInputStream = new FileInputStream(url);
        Scanner scanner = new Scanner(fileInputStream);
        Word w;
        try {
            while (scanner.hasNextLine()) {
                w = new Word();

                String s = scanner.nextLine();
                String[] a = s.split("\t");

                w.word_target = a[0];
                w.word_explain = a[1];

                dictionary.addWord(w);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @param word tuw can tim
     * @return position or k tim thay
     */
    public String dictionaryLockup(String word) {
        int tg = lookup(word);
        return tg == -1 ? "k tim thay" : dictionary.getWord(tg).word_explain;
    }

    /**
     * xoa word khoi tu dien
     * @param word tu can xoa
     */
    public void deleteWord(String word) {
        int i = lookup(word);
        if (i == -1) {
            System.out.println("k ton tai");
            return;
        }
        dictionary.removeWord(i);
    }

    /**
     * them tu vao tu dien tu commandline
     * @param sc Scanner truyen vao
     */
    public void addDictionaryCommandline(Scanner sc) {
        Word w = new Word();
        w.word_target = sc.nextLine();
        w.word_explain = sc.nextLine();
        dictionary.addWord(w);
    }

    /**
     * Repair word form commandline
     * @param sc
     */
    public void repairDictionaryCommandline(Scanner sc) {
        System.out.println("tu ban muon sua:");
        String w = sc.nextLine();
        int i = lookup(w);
        if (i == -1) {
            System.out.println("k tim thay");
            return;
        }
        Word w2 = new Word();
        w2.word_target = w;
        w2.word_explain = sc.nextLine();
        dictionary.removeWord(i);
        dictionary.addWord(i, w2);
    }

    /**
     * Export dictionary to file
     * @throws IOException loi file
     */
    public void dictionaryExportToFile() throws IOException {
        CreateFile.createFile(dictionary.getWords());
    }

    /**
     *  class
     *  create
     *  read
     *  write file
     */
    private class CreateFile {
        private static String urlFileInput;
        private static String urlFileOutput;

        /**
         * default constructor
         */
        public CreateFile() {
            urlFileInput = "src\\in.txt";
            urlFileOutput = "src\\dictionaries.txt";
        }

        /**
         * lay word tu file
         * @return mang chuwa cac tu va nghia
         * @throws FileNotFoundException k tim dc file
         */
        public static ArrayList<String> getWordFromFile() throws FileNotFoundException {
            String url = "src\\in.txt";
            // Đọc dữ liệu từ File với Scanner
            FileInputStream fileInputStream = new FileInputStream(urlFileInput);
            Scanner scanner = new Scanner(fileInputStream).useDelimiter("\\s*\t\\s*");
            ArrayList<String> a = new ArrayList<>();
            while (scanner.hasNextLine()) {
                a.add(scanner.nextLine());
            }
            scanner.close();
            return a;
        }

        /**
         * tao file voi mang dc truyen vao
         * @param a mang luu cac word
         * @throws IOException loi doc ghi
         */
        public static void createFile1(ArrayList<String> a) throws IOException {
            FileWriter fileWriter;
            fileWriter = new FileWriter(urlFileOutput);

            for (int i = 0; i < a.size() - 1; i += 2) {

                fileWriter.write(a.get(i) + "\t" + a.get(i + 1) + "\n");
            }
            fileWriter.close();
        }

        /**
         * ghi ra file
         * @param a mang truyen vao
         * @throws IOException loi ghi file
         */
        public static void createFile(ArrayList<Word> a) throws IOException {
            FileWriter fileWriter;
            fileWriter = new FileWriter(urlFileOutput);
            for (Word word : a) {
                fileWriter.write(word.word_target + "\t" + word.word_explain + "\n");
            }
            fileWriter.close();
        }

    }
}
