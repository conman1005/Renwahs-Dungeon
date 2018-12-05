/*
 * Made By: Conner Cullity
 * Date: 
 * Description: 
 */
package cullity.renwahsdungeon;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Conner
 */
public class data {
    
    //name = 30
    //level = 4
    
    int size = 34;
    
    private String name;
    private int level;
    
    public void setName(String fn) {
        StringBuffer buff = new StringBuffer(fn);
        buff.setLength(15);
        name = buff.toString();
    }

    public String getName() {
        return name.trim();
    }
    
    public void setLevel(int lvl) {
        level = lvl;
    }

    public int getLevel() {
        return level;
    }
    
    public void save(String file, int record) {
        try {
            RandomAccessFile save = new RandomAccessFile(file, "rw");
            save.seek(record * size);
            save.writeChars(name);
            save.writeInt(level);

            save.close();
        } catch (IOException io) {

        }
    }
    
    public void open(String file, int record) {
        try {
            RandomAccessFile open = new RandomAccessFile(file, "r");
            open.seek(record * size);
            char first[] = new char[15];
            for (int i = 0; i < first.length; i++) {
                first[i] = open.readChar();
            }
            name = new String(first);

            level = open.readInt();

            open.close();
        } catch (IOException io) {

        }
    }
    
    public int numRecord(String file) {
        int numR = 0;
        try {
            RandomAccessFile recordFile = new RandomAccessFile(file, "r");
            numR = (int) (recordFile.length() / size);

        } catch (Exception ex) {
        }
        return numR;
    }
    
}
