package capers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;


/** Assorted utilities.
 *  @author P. N. Hilfinger
 */
class Utils {

    /* READING AND WRITING FILE CONTENTS */

    /** Return the entire contents of FILE as a byte array.  FILE must
     *  be a normal file.  Throws IllegalArgumentException
     *  in case of problems. */
    static byte[] readContents(File file) {
        //checks the file to ensure the files is a normal file (i.e not a
        // directory), and the pathname exists
        if (!file.isFile()) {
            //extends runtime exception, indicates that the method has been
            // passed an illegal argument.
            throw new IllegalArgumentException("must be a normal file");
        }
        try {
            //file.toPath() - returns a file path object (object used to
            // locate a file in a file system)
            //files.readAllBytes - reads all the bytes from a file (returns
            // an array of bytes) or invalid path exception if path object
            // cannot be constructed.
            return Files.readAllBytes(file.toPath());
        } catch (IOException excp) {
            //IO Exception is the base class for exceptions thrown while
            // accessing information using streams, files, and directories.
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** Return the entire contents of FILE as a String.  FILE must
     *  be a normal file.  Throws IllegalArgumentException
     *  in case of problems. */
    static String readContentsAsString(File file) {
        //file must be normal(File.isFile()), otherwise it throws
        // illArgException
        
        //constructs new string by decoding the specified array of bytes
        // using the specified charset.
        return new String(readContents(file), StandardCharsets.UTF_8);
    }

    /** Write the result of concatenating the bytes in CONTENTS to FILE,
     *  creating or overwriting it as needed.  Each object in CONTENTS may be
     *  either a String or a byte array.  Throws IllegalArgumentException
     *  in case of problems. */
    
    //Object... contents - indicates an arbitrary number of parameters using
    // an array under the hood.
    static void writeContents(File file, Object... contents) {
        try {
            //if the file is a directory, and the pathname exists
            if (file.isDirectory()) {
                throw
                        new IllegalArgumentException("cannot overwrite directory");
            }
            //BufferedOutputstream - application can write bytes to the
            // underlying output stream without necessarily calling the
            // underlying system for each byte written
            
            //Files.newOutputStream  - opens or creates a file, returning an
            // output stream that may be used to write bytes to the file
            //we have saved the stream as a bufferedOutPut to optimize
            // performance,
            BufferedOutputStream str =
                    new BufferedOutputStream(Files.newOutputStream(file.toPath()));
            //iterate through the array of items we wish to add to our file
            for (Object obj : contents) {
                //if the current obj is an instance of an array of bytes
                if (obj instanceof byte[]) {
                    //we write the specified byte to the buffered output
                    // stream after casting the obj as an array of bytes
                    str.write((byte[]) obj);
                } else {
                    //cast the obj into a string, then encode the string into
                    // a seq of bytes and keeps it in an array
                    str.write(((String) obj).getBytes(StandardCharsets.UTF_8));
                }
            }
            str.close();
        } catch (IOException | ClassCastException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** Return an object of type T read from FILE, casting it to EXPECTEDCLASS.
     *  Throws IllegalArgumentException in case of problems. */
    static <T extends Serializable> T readObject(File file,
                                                 Class<T> expectedClass) {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(file));
            //reads objects sand safe casts the object to get the desired
            // classType
            T result = expectedClass.cast(in.readObject());
            in.close();
            return result;
        } catch (IOException | ClassCastException
                | ClassNotFoundException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** Write OBJ to FILE. */
    static void writeObject(File file, Serializable obj) {
        //calls the write contents method, passing in file and byte array of
        // objects
        writeContents(file, serialize(obj));
    }


    /* OTHER FILE UTILITIES */

    /** Return the concatentation of FIRST and OTHERS into a File designator,
     *  analogous to the {@link java.nio.file.Paths.#get(String, String[])}
     *  method. */
    static File join(String first, String... others) {
        return Paths.get(first, others).toFile();
    }

    /** Return the concatentation of FIRST and OTHERS into a File designator,
     *  analogous to the {@link java.nio.file.Paths.#get(String, String[])}
     *  method. */
    static File join(File first, String... others) {
        return Paths.get(first.getPath(), others).toFile();
    }


    /* SERIALIZATION UTILITIES */

    /** Returns a byte array containing the serialized contents of OBJ. */
    static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(obj);
            objectStream.close();
            return stream.toByteArray();
        } catch (IOException excp) {
            throw error("Internal error serializing commit.");
        }
    }



    /* MESSAGES AND ERROR REPORTING */

    /**
     * Prints out MESSAGE and exits with error code -1.
     * Note:
     *     The functionality for erroring/exit codes is different within Gitlet
     *     so DO NOT use this as a reference.
     *     Refer to the spec for more information.
     * @param message message to print
     */
    public static void exitWithError(String message) {
        if (message != null && !message.equals("")) {
            System.out.println(message);
        }
        System.exit(-1);
    }

    /** Return a RuntimeException whose message is composed from MSG and ARGS as
     *  for the String.format method. */
    static RuntimeException error(String msg, Object... args) {
        return new RuntimeException(String.format(msg, args));
    }

}
