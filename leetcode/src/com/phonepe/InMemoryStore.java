package com.phonepe;

import com.phonepe.exception.StoreFullException;

import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class InMemoryStore {

    private final byte[] store;
    private final int storeSize;
    private final Map<String, FileMetadata> metadataStore = new HashMap<>();

    private int currFreeIdx = 0;

    public InMemoryStore(int bytesSize) {
        storeSize = bytesSize;
        this.store = new byte[bytesSize];
    }

    public synchronized void createFile(String fileName, byte[] content) throws StoreFullException {
        int fileLength = content.length;
        int availableSize = storeSize - currFreeIdx;
        if (fileLength <= availableSize)
        {
            System.arraycopy(content, 0, store, currFreeIdx, fileLength);
            currFreeIdx += fileLength;
            metadataStore.put(fileName, new FileMetadata(currFreeIdx, fileLength));
        }
        else
        {
            throw new StoreFullException(availableSize, fileLength);
        }
    }

    public synchronized byte[] readFile(String fileName) throws FileNotFoundException {
        if (metadataStore.containsKey(fileName))
        {
            FileMetadata fileMetadata = metadataStore.get(fileName);
            int fileSize = fileMetadata.getSize();
            int fileStartIdx = fileMetadata.getStartIdx();

            byte[] file = new byte[fileSize];
            System.arraycopy(store, fileStartIdx, file, 0, fileSize);

            return file;
        }
        else
        {
            throw new FileNotFoundException(MessageFormat.format("File with name : {0} not found!", fileName));
        }
    }

    public synchronized void deleteFile(String fileName) throws FileNotFoundException {
        if (metadataStore.containsKey(fileName))
        {
            FileMetadata fileMetadata = metadataStore.get(fileName);
            int fileSize = fileMetadata.getSize();
            int fileStartIdx = fileMetadata.getStartIdx();

            int nextFileStartIdx = fileStartIdx + fileSize;
            IntStream.range(fileStartIdx, nextFileStartIdx)
                    .boxed()
                    .forEach(i -> store[i] = 0);

            System.arraycopy(store, nextFileStartIdx, store, fileStartIdx, currFreeIdx - nextFileStartIdx);
            currFreeIdx -= fileSize;
            metadataStore.remove(fileName);
        }
        else
        {
            throw new FileNotFoundException(MessageFormat.format("File with name : {0} not found!", fileName));
        }
    }

    public synchronized void updateFile(String fileName, byte[] content) throws StoreFullException, FileNotFoundException {
        if (metadataStore.containsKey(fileName))
        {
            deleteFile(fileName);
        }

        createFile(fileName, content);
    }
}
