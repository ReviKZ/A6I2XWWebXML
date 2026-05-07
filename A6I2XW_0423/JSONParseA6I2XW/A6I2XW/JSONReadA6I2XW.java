package A6I2XW;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadA6I2XW {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("orarendA6I2XW.json", StandardCharsets.UTF_8)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject orarend = (JSONObject) jsonObject.get("A6I2XW_orarend");
            JSONArray orak = (JSONArray) orarend.get("ora");

            System.out.println("A6I2XW órarend JSON dokumentum beolvasása");
            System.out.println("-----------------------------------------");

            for (Object obj : orak) {
                JSONObject ora = (JSONObject) obj;
                JSONObject idopont = (JSONObject) ora.get("idopont");

                System.out.println("Óra:");
                System.out.println("  id: " + ora.get("@id"));
                System.out.println("  típus: " + ora.get("@tipus"));
                System.out.println("  kurzus: " + ora.get("kurzus"));
                System.out.println("  nap: " + idopont.get("nap"));
                System.out.println("  kezdés: " + idopont.get("tol"));
                System.out.println("  befejezés: " + idopont.get("ig"));
                System.out.println("  helyszín: " + ora.get("helyszin"));
                System.out.println("  oktató: " + ora.get("oktato"));
                System.out.println("  szak: " + ora.get("szak"));
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Hiba történt a JSON fájl beolvasása közben.");
            e.printStackTrace();
        }
    }
}
