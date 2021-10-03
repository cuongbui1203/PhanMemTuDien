import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * thao tac voi tu dien bang commandline.
 */
public class DictionaryCommandline {
    DictionaryManagement dictionaryManagement;

    DictionaryCommandline() {
        this.dictionaryManagement = new DictionaryManagement();
    }

    /**<h1>
     * tu dien don gian.
     *</h1>
     * @param sc Scanner truyen vao.
     * @throws FileNotFoundException xay ra khi loi doc file.
     */
    public void dictionaryBasic(Scanner sc) throws FileNotFoundException {
        dictionaryManagement.addDictionaryCommandline(sc);
        dictionaryManagement.insertFromFile();
        dictionaryManagement.showAllWord();
    }

    /**
     * tu dien nang cao.
     *
     * @param sc Scanner dc truyen vao.
     * @throws IOException xay ra khi loi doc file.
     */
    public void dictionaryAdvanced(Scanner sc) throws IOException {
        dictionaryManagement.insertFromFile();
        dictionaryManagement.showAllWord();
        System.out.println(dictionaryManagement.dictionaryLockup(sc.nextLine()));
        dictionaryManagement.repairDictionaryCommandline(sc);
        dictionaryManagement.showAllWord();
        dictionaryManagement.dictionaryExportToFile();
    }

    /**
     * kiem tra xem co bao nhieu tu co chua tu nhap vao
     *
     * @param sc Scanner dc truyen vao
     */
    public void dictionarySearcher(Scanner sc) {
        System.out.println("nhap tu bn muon tra: ");
        String w = sc.nextLine();
        System.out.print("cac tu tim dc:");
        for (int i = 0; i < dictionaryManagement.dictionary.numOfWord(); i++) {
            if (dictionaryManagement.dictionary.getWord(i).word_target.contains(w)) {
                System.out.print(dictionaryManagement.dictionary.getWord(i).word_target + ", ");
            }
        }
    }

}