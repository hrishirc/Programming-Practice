package com.phonepe;

import com.phonepe.exception.StoreFullException;

import java.io.FileNotFoundException;
import java.util.stream.IntStream;

public class Runner {

    private static InMemoryStore inMemoryStore = new InMemoryStore(100);

    public static void main(String[] args) throws StoreFullException, FileNotFoundException {
        update();
    }

    /**
     * Test Cases
     */

    private static void simpleAdditionAndRead(String fileName) throws StoreFullException, FileNotFoundException {

        inMemoryStore.createFile(fileName, "123".getBytes());
        String reading = new String(inMemoryStore.readFile(fileName));
        System.out.println("Added Successfully (and Read)" + fileName);
    }

    private static void fillStore(int repeats) throws StoreFullException, FileNotFoundException {

        IntStream.rangeClosed(1, repeats)
                .boxed()
                .forEach(i -> {
                    try {
                        simpleAdditionAndRead("content" + i);
                    } catch (StoreFullException | FileNotFoundException e) {
                        System.out.println("Store Full at " + i);
                    }
                });
    }

    private static void delete() throws StoreFullException, FileNotFoundException {
        simpleAdditionAndRead("1");
        inMemoryStore.deleteFile("1");
        System.out.println("Successfully Deleted");
        try
        {
            inMemoryStore.readFile("1");
        }
        catch (Exception e)
        {
            System.out.println("Successfully Didn't Read");
        }
    }

    private static void update() throws StoreFullException, FileNotFoundException {
        fillStore(3);
        System.out.println(new String(inMemoryStore.readFile("content3")));
        inMemoryStore.updateFile("content2", "blahblahblah".getBytes());
        System.out.println(new String(inMemoryStore.readFile("content2")));
        System.out.println(new String(inMemoryStore.readFile("content1")));
        System.out.println(new String(inMemoryStore.readFile("content3")));

    }
}
