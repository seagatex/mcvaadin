package com.googlecode.mcvaadin.helpers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.googlecode.mcvaadin.external.apache.Base64;
import com.googlecode.mcvaadin.external.apache.ByteChunk;
import com.googlecode.mcvaadin.external.apache.CharChunk;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;

/**
 * A set of generic utility functions.
 * 
 * These functions are used internally by various McVaadin components, but feel
 * free to use them elsewhere also.
 * 
 */
public class Utils implements Serializable {

    private static final long serialVersionUID = -6535011681711775216L;
    public static final Charset CSV_CHARSET = Charset.forName("ISO-8859-1");
    public static final char CSV_DELIMITER = ';';
    public static final String VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String VALID_NUMBERS = "1234567890";
    public static final String VALID_EMAIL_CHARACTERS = "@.-"
            + VALID_CHARACTERS + VALID_NUMBERS;
    public static final String VALID_URI_CHARACTERS = ".-_" + VALID_CHARACTERS
            + VALID_NUMBERS;
    public static String DEFAULT_STRING_ENCODING = "iso-8859-1";
    public static DateFormat DEFAULT_DATEFORMAT = DateFormat
            .getDateInstance(DateFormat.DEFAULT);

    public static String escapeSQL(String str) {
        return str == null ? null : str.replaceAll("'", "\\'");
    }

    public static String escapeXML(String str) {
        return str == null ? null
                : com.vaadin.terminal.gwt.server.JsonPaintTarget.escapeXML(str);
    }

    public static String escapeJSON(String str) {
        return str == null ? null
                : com.vaadin.terminal.gwt.server.JsonPaintTarget
                        .escapeJSON(str);
    }

    public static String escapeURL(String str) {
        return str == null ? null : URLEncode(str);
    }

    public static String format(String message, Object... arguments) {
        if (message == null) {
            return null;
        }
        MessageFormat mf = new MessageFormat(message);
        Format[] fmts = mf.getFormats();
        for (int i = 0; i < fmts.length; i++) {
            if (fmts[i] instanceof DateFormat) {
                mf.setFormat(i, DEFAULT_DATEFORMAT);
            }
        }
        return mf.format(arguments);
    }

    /** Check if a String is empty or null */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static Component findParent(Component c, Class<?> parentType) {
        if (c == null || parentType.isAssignableFrom(c.getClass())) {
            return c;
        }
        return findParent(c.getParent(), parentType);
    }

    public static String toString(InputStream stream, String enc,
            String defaultValue) {
        if (stream == null) {
            return defaultValue;
        }
        byte[] b = new byte[1024];
        int l = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            while ((l = stream.read(b)) > 0) {
                out.write(b, 0, l);
            }
            return out.toString(enc);
        } catch (IOException ignored) {
        }
        return defaultValue;

    }

    public static String toString(InputStream stream, String defaultValue) {
        return toString(stream, DEFAULT_STRING_ENCODING, defaultValue);
    }

    public String loadResource(ClassLoader loader, String name) {
        return Utils.toString(getClass().getClassLoader().getResourceAsStream(
                getClass().getName() + ".script"), null);
    }

    // public static InputStream toCsvStream(Container container) {
    // ByteArrayOutputStream out = new ByteArrayOutputStream();
    // String[][] res = toCsv(container);
    // if (res != null) {
    // CsvWriter w = new CsvWriter(out, CSV_DELIMITER, CSV_CHARSET);
    // w.setUseTextQualifier(true);
    // for (String[] strings : res) {
    // try {
    // w.writeRecord(strings);
    // } catch (IOException e) {
    // e.printStackTrace();
    // break;
    // }
    // }
    // w.close();
    // }
    // return new ByteArrayInputStream(out.toByteArray());
    // }

    public static String[][] toStringArray(Container container) {
        Collection<?> ids = container.getItemIds();
        Collection<?> cols = container.getContainerPropertyIds();
        String[][] res = new String[ids.size()][cols.size()];
        int row = 0;
        for (Iterator<?> i = ids.iterator(); i.hasNext(); row++) {
            Object rid = i.next();
            int col = 0;
            for (Iterator<?> j = cols.iterator(); j.hasNext(); col++) {
                Object cid = j.next();
                Property p = container.getContainerProperty(rid, cid);
                if (p != null) {
                    res[row][col] = getFormattedValue(p);
                }
            }
        }
        return res;
    }

    private static String getFormattedValue(Property p) {
        // TODO: date+other formatting
        if (p != null) {
            Object v = p.getValue();
            if (v != null) {
                if (v instanceof Date) {
                    return DEFAULT_DATEFORMAT.format((Date) v);
                } else {
                    return v.toString();
                }
            }
        }
        return null;
    }

    public static String getURLToString(String s) {
        return getURLToString(s, DEFAULT_STRING_ENCODING);
    }

    public static String getURLToString(String s, String enc) {
        if (!isURL(s)) {
            return "";
        }
        try {
            URL url = new URL(s);
            return toString(url.openStream(), enc, null);
        } catch (MalformedURLException e) {
            // Should not happen. Really.
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isURL(String s) {
        try {
            new URL(s);
            return true;
        } catch (MalformedURLException ignored) {
        }
        return false;
    }

    public static String readURL(String url) {
        return readURL(url, DEFAULT_STRING_ENCODING);
    }

    public static String readURL(String url, String encoding) {
        if (!isURL(url)) {
            return null;
        }
        BufferedReader in = null;
        StringBuffer res = new StringBuffer();
        String inputLine = null;
        try {
            URL target = new URL(url);
            in = new BufferedReader(new InputStreamReader(target.openStream(),
                    encoding));
            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine + "\n");
            }
            return res.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Set<Class<?>> getClassesForPackage(String pckgname,
            boolean includeInnerClasses) throws ClassNotFoundException {
        return getClassesForPackage(pckgname, null, includeInnerClasses);
    }

    public static Set<Class<?>> getClassesForPackage(String pckgname)
            throws ClassNotFoundException {
        return getClassesForPackage(pckgname, null, false);
    }

    public static Set<Class<?>> getClassesForPackage(String pckgname,
            ClassLoader loader, boolean includeInnerClasses)
            throws ClassNotFoundException {
        // This will hold a list of directories matching the pckgname.
        // There may be more than one if a package is split over multiple
        // jars/paths
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        ArrayList<File> directories = new ArrayList<File>();
        try {
            ClassLoader cld = loader != null ? loader : Thread.currentThread()
                    .getContextClassLoader();
            if (cld == null) {
                throw new ClassNotFoundException("Can't get class loader.");
            }
            // Ask for all resources for the path
            String pkgPath = pckgname.replace('.', '/');
            Enumeration<URL> resources = cld.getResources(pkgPath);
            while (resources.hasMoreElements()) {
                URL res = resources.nextElement();
                if (res.getProtocol().equalsIgnoreCase("jar")) {
                    JarURLConnection conn = (JarURLConnection) res
                            .openConnection();
                    JarFile jar = conn.getJarFile();
                    for (JarEntry e : Collections.list(jar.entries())) {

                        if (e.getName().startsWith(pkgPath)
                                && e.getName().endsWith(".class")) {
                            String className = e.getName().replace("/", ".")
                                    .substring(0, e.getName().length() - 6);

                            // Innerclass hanling
                            if (className.contains("$") && !includeInnerClasses) {
                                continue;
                            }

                            try {
                                classes.add(loader != null ? loader
                                        .loadClass(className) : Class
                                        .forName(className));
                            } catch (NoClassDefFoundError ignored) {
                            } catch (UnsatisfiedLinkError ignored) {
                            }
                        }
                    }
                } else {
                    directories.add(new File(URLDecoder.decode(res.getPath(),
                            "UTF-8")));
                }
            }
        } catch (NullPointerException x) {
            throw new ClassNotFoundException(pckgname
                    + " does not appear to be "
                    + "a valid package (Null pointer exception)");
        } catch (UnsupportedEncodingException encex) {
            throw new ClassNotFoundException(pckgname
                    + " does not appear to be "
                    + "a valid package (Unsupported encoding)");
        } catch (IOException ioex) {
            throw new ClassNotFoundException(
                    "IOException was thrown when trying "
                            + "to get all resources for " + pckgname);
        }

        // For every directory identified capture all the .class files
        for (File directory : directories) {
            if (directory.exists()) {
                // Get the list of the files contained in the package
                String[] files = directory.list();
                for (String file : files) {
                    // we are only interested in .class files
                    if (file.endsWith(".class")) {
                        // removes the .class extension
                        classes.add(Class.forName(pckgname + '.'
                                + file.substring(0, file.length() - 6)));
                    }
                }
            } else {
                throw new ClassNotFoundException(pckgname + " ("
                        + directory.getPath()
                        + ") does not appear to be a valid package");
            }
        }
        return classes;
    }

    public static List<Class<?>> getClassessOfInterface(String thePackage,
            Class<?> theInterface) throws ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        for (Class<?> discovered : getClassesForPackage(thePackage)) {
            if (Arrays.asList(discovered.getInterfaces())
                    .contains(theInterface)) {
                classList.add(discovered);
            }
        }

        return classList;
    }

    public static boolean isValidEmail(String text) {
        return !isEmpty(text) && containsOnly(text, VALID_EMAIL_CHARACTERS)
                && countOf("@",text) == 1 && text.indexOf('@') > 0
                && text.indexOf('@') < text.lastIndexOf('.');
    }

    public static boolean containsOnly(String text, String valid) {
        if (valid == null || text == null) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (valid.indexOf(text.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count number of string matches in another string.
     * 
     * @param lookFor
     * @param inString
     * @return
     */
    public static int countOf(String lookFor, String inString) {
        if (lookFor == null || inString == null) {
            return 0;
        }
        int lfLen = lookFor.length();
        int count = 0;
        int pos = 0;
        while ((pos = inString.indexOf(lookFor, pos)) >= 0) {
            count++;
            pos += lfLen;
        }
        return count;
    }

    public static String toHexString(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static byte[] base64(byte[] data) {
        return Base64.encode(data);
    }

    public static byte[] SHA1(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(data, 0, data.length);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String SHA1Base64(String data) {
        try {
            return base64String(SHA1(data.getBytes(DEFAULT_STRING_ENCODING)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] base64Decode(byte[] data) {
        ByteChunk bchunk = new ByteChunk();
        try {
            bchunk.append(data, 0, data.length);
            CharChunk ret = new CharChunk();
            Base64.decode(bchunk, ret);
            return ret.toStringInternal().getBytes(DEFAULT_STRING_ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] base64Decode(String data) {
        try {
            return base64Decode(data.getBytes(DEFAULT_STRING_ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String base64String(byte[] data) {
        try {
            return new String(base64(data), DEFAULT_STRING_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String URLEncode(String str) {
        try {
            return URLEncoder.encode(str, DEFAULT_STRING_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String URLDecode(String str) {
        try {
            return URLDecoder.decode(str, DEFAULT_STRING_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Serialize properties into single (multiline) string. This is not meant to
     * be human readable
     * 
     * @see #stringToProperties(String)
     * @param props
     *            Properties to be convert.
     * @return String containing properties as multiline String.
     */
    public static String propertiesToString(Properties props) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            props.store(out, null);
        } catch (IOException gnored) {
        } finally {
            try {
                out.close();
            } catch (IOException ignored) {
            }
        }
        return out.toString();
    }

    /**
     * Load properties from single (multiline) string.
     * 
     * @param string
     *            String containing properties.
     * @return Properties from given string
     */
    public static Properties stringToProperties(String string) {
        Properties props = new Properties();
        if (string == null) {
            return props;
        }
        String converted = string.replaceAll("\\\\", "\\\\\\\\");
        try {
            props.load(new ByteArrayInputStream(converted.getBytes()));
        } catch (IOException ignored) {
        }
        return props;
    }

    /**
     * Serialize properties into single (multiline) string. This is method
     * creates sorted, human readable (non-escaped) string suitable for UI.
     * 
     * @see #stringToProperties(String)
     * @param props
     *            Properties to be convert.
     * @return String containing properties as multiline String.
     */
    @SuppressWarnings("unchecked")
    public static String propertiesToList(Properties props) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter p = new PrintWriter(out);

        // Take snapshot of properties and sort
        List snapshot = new ArrayList(props.keySet());
        Collections.sort(snapshot);

        for (Iterator i = snapshot.iterator(); i.hasNext();) {
            String key = (String) i.next();
            p.print(key);
            p.print("=");
            p.print(props.getProperty(key));
            p.print("\n");
        }
        p.close();
        return out.toString();
    }

    /**
     * Create a string by repeating the given string.
     * 
     * @param string
     *            The String to repeat
     * @param count
     *            Repeat count 1 or greater.
     * @return Returns null if original string was null, empty string if count
     *         was 0 and repeated string otherwise.
     */
    public static String repeat(String string, int count) {
        if (string == null)
            return null;
        StringBuffer res = new StringBuffer();
        for (int j = 0; j < count; j++) {
            res.append(string);
        }
        return res.toString();
    }

    // public static int parseInt(TextField tf, int defaultValue) {
    // return parseInt("" + (tf != null ? tf.getValue() : ""), defaultValue);
    // }
    //
    // public static int parseInt(String str, int defaultValue) {
    // if (str != null) {
    // try {
    // return Integer.parseInt(str.trim());
    // } catch (NumberFormatException ignored) {
    // }
    // }
    // return defaultValue;
    // }

}
