package capers;

import java.io.File;
import java.io.IOException;

import static capers.Dog.DOG_FOLDER;
import static capers.Utils.*;

/** A repository for Capers 
 * @author Imma Duverger
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(CWD, ".capers");
    
    static final File STORY = Utils.join(".capers", "story" );
    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        try {
            if (!CAPERS_FOLDER.isDirectory()) {
                CAPERS_FOLDER.mkdir();
            }
            if(!DOG_FOLDER.isDirectory()) {
                DOG_FOLDER.mkdir();
            }
            if(!STORY.isFile()) {
                STORY.createNewFile();
            }
        } catch(IOException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        //first retrieve story bytes from the story file
        String story = Utils.readContentsAsString(STORY);
        //write the story, but make sure we read the previous contents first
        Utils.writeContents(STORY, story, text + "\n");
        //save the appended story in a variable
        String appendedStory = Utils.readContentsAsString(STORY);
        //print the story w/ appended text
        System.out.println(appendedStory);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog newDog = new Dog(name, breed, age);
        newDog.saveDog();
        System.out.println(newDog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog retrievedDog = Dog.fromFile(name);
        retrievedDog.haveBirthday();
        retrievedDog.toString();
        //now overwrite the file for the current dog
        retrievedDog.saveDog();
    }
}
