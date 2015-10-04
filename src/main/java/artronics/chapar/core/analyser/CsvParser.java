package artronics.chapar.core.analyser;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.splitByWholeSeparator;

public class CsvParser
{
    private FileInputStream in;
    private List<String> lines;
    private List<List<List<Integer>>> valuesTable = new ArrayList<>();

    public CsvParser(String csvFile)
    {
        try {
            this.lines = Files.readAllLines(Paths.get(csvFile), Charset.forName("UTF-8"));
        }catch (IOException e) {
            e.printStackTrace();
        }

        splitValues();
    }

    public CsvParser(List<String> lines)
    {
        this.lines = lines;

        splitValues();
    }

    private void splitValues()
    {
        for (String line : lines) {
            List<List<Integer>> records = new ArrayList<>();
            String[] sections = splitByWholeSeparator(line, ";;");
            for (String section : sections) {
                if (section.isEmpty()) {
                    valuesTable.add(records);
                    break;
                }
                String[] values = split(section, ";");
                List<Integer> secValues = new ArrayList<>();
                for (String value : values) {
                    secValues.add(Integer.parseInt(value.trim()));
                }
                records.add(secValues);
            }
        }
    }

    public List<List<List<Integer>>> getValuesTable()
    {
        return valuesTable;
    }
}
