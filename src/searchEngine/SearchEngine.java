package searchEngine;

import indexer.Index;
import indexer.IndexEntry;

import multiTreeMap.MultiTreeMap;

public class SearchEngine {

    private void index() {

        String[] documents = {"This is is a document"};
        Index index = new Index(documents);
        index.index();

    }

    public static void main(String[] args) {
        
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.index();

    }
}
