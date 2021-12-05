package com.uber.model;

import java.io.File;
import java.util.*;

public class FileLineIterator implements Iterator<String> {

    private File currentFile;
    private long currentLineNo = 0;
    private Queue<String> pathsRemaining = new LinkedList<>();

    public FileLineIterator(String path) {
        if (!currentFile.exists())
        {
            throw new RuntimeException("File Not Found!!");
        }

        pathsRemaining.add(path);
    }

    // returns null if line not exists or file not exists
    private String getLine(String path, long lineNumber)
    {
        return null;
    }

    @Override
    public boolean hasNext() {
       if (currentFile == null)
       {
           return hasNextFurther();
       }
       else
       {
           if (getLine(currentFile.getPath(), currentLineNo) != null)
               return true;
           else
               return hasNextFurther();
       }
    }

    // return null if nothing found .
    // return line and increment lineIndex
    @Override
    public String next() {
        return getLine(currentFile.getPath(), currentLineNo++);
    }

    private boolean hasNextFurther() {

        String path = pathsRemaining.poll();
        while (path == null || !(currentFile = new File(path)).exists()) {
            if (path == null) {
                return false;
            }

            path = pathsRemaining.poll();
        }

        while (currentFile.isDirectory() || getLine(path, 0) == null)
        {
            if (currentFile.isDirectory()) {
                String[] contents = currentFile.list();
                if (contents != null) {
                    pathsRemaining.addAll(Arrays.asList(contents));
                }
            }

            path = pathsRemaining.poll();
            if (path != null)
            {
                currentFile = new File(path);
            }
            else
            {
                return false;
            }
        }

        return true;
    }
}
