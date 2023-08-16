package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser {

    private String separator=",";

    public CsvParser(String separator) {
        this.separator = separator;
    }
    public CsvParser() {
    }

    public <T> List<T> parse(String path, Class<T> type) throws IOException {

        Path p = Paths.get(path);
        String data = readcsv(p);
        return parseInternal(data, type);

    }

    public <T> List<T> parse(URI uri, Class<T> type) throws IOException {

        String data = readFromURI(uri);
        return parseInternal(data, type);

    }

    private <T> List<T> parseInternal(String data, Class<T> type) throws IOException {


        validate(data);
        String[] rows = data.split("\n");
        List<T> result = new ArrayList<T>();
        if (rows.length <= 1) {
            return result;
        }

        Map<Integer, Field> labelMap = mapLabels(rows[0], type);

        for (int i = 1; i < rows.length; i++) {

            T t = getObject(type, rows[i], labelMap);

            result.add(t);
        }

        return result;
    }

    private  String readcsv(Path filePath) throws IOException {


        return Files.readAllLines(filePath).stream().collect(Collectors.joining("\n"));
    }

    private  String readFromURI(URI u) throws IOException {
        String s = u.toString();
        URL url = new URL(s);
        URLConnection yc = url.openConnection();
        StringBuilder sf = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sf.append(inputLine);
                sf.append("\n");

            }
        }
        return sf.toString();
    }

    private  <T> T getObject(Class<T> type, String rowString, Map<Integer, Field> map) {

        try {
            T newInstance = (T) type.newInstance();

            String[] data = rowString.split(separator);
            for (int i = 0; i < data.length; i++) {
                Field f = map.get(i);
                if (f == null) {
                    continue;
                }
                f.setAccessible(true);
                String fieldValue = data[i];
                if (fieldValue.equalsIgnoreCase("null")) {
                    setvalue(newInstance, null, f);
                    continue;
                }
                setvalue(newInstance, fieldValue.trim(), f);
            }
            return newInstance;

        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return null;

    }

    private  <T> void setvalue(T newInstance, String data, Field f) throws IllegalAccessException {
        Class c = f.getType();
        data = getSanitizeField(data);
        if (!c.isAssignableFrom(String.class) && data == null) {

            throw new RuntimeException("Other than String Type Null value not allowed");

        }

        if (c.isAssignableFrom(int.class) || c.isAssignableFrom(Integer.class)) {
            f.setInt(newInstance, Integer.parseInt(data));
            return;
        }
        if (c.isAssignableFrom(double.class) || c.isAssignableFrom(Double.class)) {
            f.setDouble(newInstance, Double.parseDouble(data));
            return;
        }
        if (c.isAssignableFrom(float.class) || c.isAssignableFrom(Float.class)) {
            f.setFloat(newInstance, Float.parseFloat(data));
            return;
        }
        if (c.isAssignableFrom(boolean.class) || c.isAssignableFrom(Boolean.class)) {
            f.setBoolean(newInstance, Boolean.parseBoolean(data));
            return;
        }

        f.set(newInstance, data);

    }

    private <T> Map<Integer, Field> mapLabels(String labelsString, Class<T> type) {
        // TODO Auto-generated method stub
        Map<Integer, Field> map = new HashMap<>();
        Field[] fields = type.getDeclaredFields();
        String[] labels = labelsString.split(separator);

        for (int i = 0; i < labels.length; i++) {
            String field = getSanitizeField(labels[i]);

            Optional<Field> f = Stream.of(fields).filter(x -> map(field, x)).findFirst();

            if (f.isPresent()) {

                map.put(i, f.get());
            }

        }
        return map;
    }

    private  String getSanitizeField(String string) {
        if (string == null) {
            return null;
        }
        return string.trim().replaceAll("\"", "");

    }

    private boolean map(String field, Field x) {
        if (x.isAnnotationPresent(FieldName.class)) {

            return x.getAnnotation(FieldName.class).name().equalsIgnoreCase(field);
        }
        return x.getName().equalsIgnoreCase(field);
    }

    private void validate(String input) {
        // TODO Auto-generated method stub
        if (input == null || input.equalsIgnoreCase("")) {
            throw new RuntimeException("invalid String");
        }

    }

}
