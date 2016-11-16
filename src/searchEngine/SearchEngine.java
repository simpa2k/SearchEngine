package searchEngine;

import indexer.Index;

public class SearchEngine {

    private Index index;

    private void index() {

        String[] documents = {"This is is a document", "Another document"};
        index = new Index(documents);
        index.index();

    }

    private void search(String query) {

        
        
    }

    public static void main(String[] args) {
        
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.index();

        String query = "a document";

        searchEngine.search(query);

    }
}
