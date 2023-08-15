package parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class CsvParser {

	public <T> List<T> parse(String input, Class<T> type) {

		validate(input);
		String[] rows = input.split("\n");
		Map<Integer, Field> labelMap = mapLabels(rows[0], type);
		List<T> result = new ArrayList<T>();
		for (int i = 1; i < rows.length; i++) {

			T t = getObject(type, rows[i], labelMap);

			result.add(t);
		}

		return result;

	}

	private static <T> T getObject(Class<T> type, String rowString, Map<Integer, Field> map) {

		try {
			T newInstance = (T) type.newInstance();

			String[] data = rowString.split(",");
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

	private static <T> void setvalue(T newInstance, String data, Field f) throws IllegalAccessException {
		Class c = f.getType();
		data= getSanitizeField(data);
		if (!c.isAssignableFrom(String.class) && data == null) {

			throw new RuntimeException("Other than String Type Null value not allowed");

		}

		if (c.isAssignableFrom(int.class) || c.isAssignableFrom(Integer.class)) {
			f.setInt(newInstance, Integer.valueOf(data));
			return;
		}
		if (c.isAssignableFrom(double.class) || c.isAssignableFrom(Double.class)) {
			f.setDouble(newInstance, Double.valueOf(data));
			return;
		}
		if (c.isAssignableFrom(float.class) || c.isAssignableFrom(Float.class)) {
			f.setFloat(newInstance, Float.valueOf(data));
			return;
		}
		if (c.isAssignableFrom(boolean.class) || c.isAssignableFrom(Boolean.class)) {
			f.setBoolean(newInstance, Boolean.valueOf(data));
			return;
		}

		f.set(newInstance,data );

	}

	private <T> Map<Integer, Field> mapLabels(String labelsString, Class<T> type) {
		// TODO Auto-generated method stub
		Map<Integer, Field> map = new HashMap<>();
		Field[] fields = type.getDeclaredFields();
		String[] labels = labelsString.split(",");
		for (int i = 0; i < labels.length; i++) {
			String field = getSanitizeField(labels[i]);
			
			Optional<Field> f = Stream.of(fields).filter(x -> map(field, x)).findFirst();

			if (f.isPresent()) {

				map.put(i, f.get());
			}

		}
		return map;
	}

	private static String getSanitizeField(String string) {
		if(string==null) {
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
		String[] split = input.split("\n");
		if (split.length <= 1) {
			throw new RuntimeException("Empty Ojbect");
		}

	}

}
