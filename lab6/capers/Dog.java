package capers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import static capers.CapersRepository.CAPERS_FOLDER;
import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * @author Imma Duverger
*/
public class Dog implements Serializable {

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = Utils.join(CAPERS_FOLDER, ".dogs");
    
    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
        Dog retrievedDog = null;
        try {
            File dogExists = Utils.join(DOG_FOLDER, name);
            if (!dogExists.isFile()) {
                throw new FileNotFoundException();
            } else {
               retrievedDog = readObject(dogExists, Dog.class);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        return retrievedDog;
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() {
        try {
            //check if there is a file already with the current instance's name
            File dogExists = join(DOG_FOLDER, this.name);
           if (!dogExists.isFile()) {
               //ensure we create the individual file for the dog if it does
               // not exist
               dogExists.createNewFile();
           }
            //save the current obj into the file
            Utils.writeObject(dogExists, this);
        } catch(IOException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }

}
