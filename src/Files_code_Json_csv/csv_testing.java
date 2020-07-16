package Files_code_Json_csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class csv_testing {
    public void get_data_from_csv( String path ,String table_name) throws IOException {
        BufferedReader csvReader = null;
        String[] data = new String[0];
        File csvFile = new File(path);
        csvReader = new BufferedReader(new FileReader(csvFile));
       if (csvFile.isFile()) {

           String row;

              while(((row = csvReader.readLine()) != null)) {

                    data = row.split(",");
                    employess eee = new employess();

                for (int i = 0; i < data.length; i++)
                {

                    employess e = new employess();
                    //if(data type was double )
                   // Double.parseDouble(data[i]);
                    //if(data type was boolean )
                  //   boolean t =Boolean.parseBoolean("true");
                    //else just stor the vale in the object
                }

            }
        }

       /* CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withHeader("id", "name","age","boolean").withIgnoreHeaderCase().withTrim());
        for (CSVRecord csvRecord: csvParser) {
          //  S System.out.println(csvReader.read());ystem.out.println("reading for the labrary"+csvRecord.get(0));
            System.out.println(csvRecord.get("id"));
            System.out.println(csvRecord.get("name"));
            System.out.println(csvRecord.get("age"));

        }*/
        }
        }



