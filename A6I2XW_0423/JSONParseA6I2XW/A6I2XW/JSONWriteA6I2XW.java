package A6I2XW;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONWriteA6I2XW {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("orarendA6I2XW.json", StandardCharsets.UTF_8)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject orarend = (JSONObject) jsonObject.get("A6I2XW_orarend");
            JSONArray orak = (JSONArray) orarend.get("ora");

            JSONObject ujOrarend = new JSONObject();
            JSONArray ujOrak = new JSONArray();

            System.out.println("A6I2XW órarend blokk formátumban");
            System.out.println("--------------------------------");

            for (Object obj : orak) {
                JSONObject ora = (JSONObject) obj;
                JSONObject idopont = (JSONObject) ora.get("idopont");

                JSONObject ujOra = new JSONObject();
                ujOra.put("id", ora.get("@id"));
                ujOra.put("tipus", ora.get("@tipus"));
                ujOra.put("kurzus", ora.get("kurzus"));
                ujOra.put("nap", idopont.get("nap"));
                ujOra.put("tol", idopont.get("tol"));
                ujOra.put("ig", idopont.get("ig"));
                ujOra.put("helyszin", ora.get("helyszin"));
                ujOra.put("oktato", ora.get("oktato"));
                ujOra.put("szak", ora.get("szak"));

                ujOrak.add(ujOra);

                System.out.println("Óra blokk:");
                System.out.println("  id: " + ujOra.get("id"));
                System.out.println("  típus: " + ujOra.get("tipus"));
                System.out.println("  kurzus: " + ujOra.get("kurzus"));
                System.out.println("  időpont: " + ujOra.get("nap") + " " + ujOra.get("tol") + " - " + ujOra.get("ig"));
                System.out.println("  helyszín: " + ujOra.get("helyszin"));
                System.out.println("  oktató: " + ujOra.get("oktato"));
                System.out.println("  szak: " + ujOra.get("szak"));
                System.out.println();
            }

            ujOrarend.put("orarend", ujOrak);

            try (FileWriter writer = new FileWriter("orarendA6I2XW1.json", StandardCharsets.UTF_8)) {
                writer.write(ujOrarend.toJSONString());
            }

            System.out.println("A fájl elkészült: orarendA6I2XW1.json");

        } catch (Exception e) {
            System.out.println("Hiba történt a JSON fájl feldolgozása közben.");
            e.printStackTrace();
        }
    }
}
