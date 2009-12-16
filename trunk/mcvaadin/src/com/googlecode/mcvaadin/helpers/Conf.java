package com.googlecode.mcvaadin.helpers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

/**
 * Configuration file. This extends the java.util.Properties by adding type to
 * properties.
 * */
public class Conf extends Properties {

    private static final long serialVersionUID = 2182760123424116435L;
    private boolean loaded;
    private List<String> available = new ArrayList<String>();

    /**
     * New configuration file.
     *
     * Read available keys from given class. This uses reflect to get all static
     * String fields from given objects class to list the available properties.
     *
     * @param obj
     * @param fieldPrefix
     * @param confName
     */
    public Conf(Object obj, String fieldPrefix) {
        Class<?> cls = obj.getClass();
        if (obj instanceof Class<?>) {
            cls = (Class<?>) obj;
        }
        available = listStaticStrings(cls, fieldPrefix);
    }

    /**
     * List static fields in given class.
     *
     * @param clazz
     *            Class to process.
     * @param prefix
     *            Prefix of field name name. May be null.
     * @param fieldType
     *            type of the fields that are considered. May be null.
     */
    public static List<String> listStaticStrings(Class<?> clazz, String prefix) {

        List<String> list = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            int m = fields[i].getModifiers();
            if (Modifier.isStatic(m)) {
                if (prefix == null || fields[i].getName().startsWith(prefix)) {
                    try {
                        list.add((String) fields[i].get(null));
                    } catch (IllegalArgumentException ignored) {
                        ignored.printStackTrace();
                    } catch (IllegalAccessException ignored) {
                        ignored.printStackTrace();
                    }
                }
            }
        }
        return list;
    }

    public void loadFromSystemPropertyFile(String propertyName)
            throws IOException {
        String value = System.getProperty(propertyName);
        if (value != null) {
            loadFromFile(new File(value));
        }
    }

    public void loadFromEnvironmentFile(String environmentVariableName)
            throws IOException {
        String value = System.getenv(environmentVariableName);
        if (value != null) {
            loadFromFile(new File(value));
        }
    }

    public void loadFromFile(File file) throws IOException {
        if (file != null && file.exists() && file.canRead()) {
            load(new FileInputStream(file));
        }
    }

    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        loaded = false;
        super.load(inStream);
        loaded = true;
    }

    @Override
    public synchronized void loadFromXML(InputStream in) throws IOException,
            InvalidPropertiesFormatException {
        loaded = false;
        super.loadFromXML(in);
        loaded = true;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String v = getProperty(key);
        if (v != null && Utils.containsOnly(v, Utils.VALID_CHARACTERS)) {
            return Boolean.parseBoolean(v);
        }
        return defaultValue;
    }

    public void setBoolean(String key, boolean value) {
        setProperty(key, Boolean.toString(value));
    }

    public int getInteger(String key, int defaultValue) {
        String v = getProperty(key);
        if (v != null && Utils.containsOnly(v, Utils.VALID_NUMBERS)) {
            return Integer.parseInt(v);
        }
        return defaultValue;
    }

    public void setInteger(String key, int value) {
        setProperty(key, Integer.toString(value));
    }

    public String getString(String key, String defaultValue) {
        String v = getProperty(key);
        if (v != null) {
            return v;
        }
        return defaultValue;
    }

    public void setString(String key, String value) {
        setProperty(key, value);
    }

    public String get(String key, String defaultValue) {
        return getString(key, defaultValue);
    }

    public int get(String key, int defaultValue) {
        return getInteger(key, defaultValue);
    }

    public boolean get(String key, boolean defaultValue) {
        return getBoolean(key, defaultValue);
    }

    public String listAllProperties() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        OutputStreamWriter out = new OutputStreamWriter(b);
        try {
            for (String name : available) {
                out.write(name + "=\n");
            }
            out.close();
            return new String(b.toByteArray(), Utils.DEFAULT_STRING_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
