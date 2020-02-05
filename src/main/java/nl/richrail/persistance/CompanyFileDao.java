package nl.richrail.persistance;

import nl.richrail.domain.singleton.Company;
import nl.richrail.domain.singleton.LoggerInterface;

import java.io.*;

public class CompanyFileDao implements CompanyDao {
    private LoggerInterface logger = Company.getInstance().getLogger();
    private final CompanySerializer serializer;
    private String textfile;


    public CompanyFileDao(String textfile, CompanySerializer serializer) {
        this.textfile = textfile;
        this.serializer = serializer;
    }

    @Override
    public void save(Company company) {

        try {
            String data = this.serializer.serialize(company);

            FileWriter writer = new FileWriter("src/main/java/nl/richrail/persistance/savefiles/" + this.textfile);
            writer.write(data);
            writer.flush();
            writer.close();

            logger.log("Company successfully saved\n");
        } catch (Exception e) {
            logger.log("Company failed to save\n");
        }
    }

    @Override
    public Company load() {
        try {

            File targetFile = this.fileFinder("src/main/java/nl/richrail/persistance/savefiles/");
            String jsonLine = this.readFileLines(targetFile);

            Company loadedCompany = serializer.deserialize(jsonLine);

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }

        return null;
    }

    private File fileFinder(String path) {

        File[] listOfFiles = new File(path).listFiles();

        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (!textfile.contains(".json")) {
                    logger.log(textfile + " not a .json file");
                    return null;
                }
                if (listOfFile.getName().equals(textfile)) {
                    logger.log(this.textfile + " found\n");
                    System.out.println(listOfFile.getName());
                    return listOfFile;
                } else {
                    logger.log("No file named " + this.textfile + " found");
                }
            }
        } else {
            logger.log("Nothing found in savefiles folder");
            return null;
        }

        return null;
    }

    private String readFileLines(File file) throws FileNotFoundException {
        StringBuilder contentBuilder = new StringBuilder();
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }


}
