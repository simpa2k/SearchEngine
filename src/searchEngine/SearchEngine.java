package searchEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import index.Index;
import index.IndexEntry;
import index.Indexer;

public class SearchEngine {

    private Index index;

    private void index() {

        String[] documents = {"This is is a document", "Another document"};
        index = new Index(documents);
        index.index();

    }

    private Set<Map.Entry<String, IndexEntry<String>>> indexQuery(String query) {

        Indexer indexer = new Indexer();
        return indexer.index("query", query);
        
    }

    private void compareQueryToDocuments(Set<Map.Entry<String, IndexEntry<String>>> indexedQuery) {

        for(Map.Entry<String, IndexEntry<String>> queryTermEntry : indexedQuery) {
            
            String queryTerm = (String) queryTermEntry.getKey();
            IndexEntry<String> indexedTerm = queryTermEntry.getValue();

            HashSet<IndexEntry<String>> documentsIncludingQueryTerm = index.get(queryTerm);
            System.out.println(documentsIncludingQueryTerm);

        }
        
    }

    public static void main(String[] args) {
        
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.index();

        String query = "a document";

        Set<Map.Entry<String, IndexEntry<String>>> indexedQuery = searchEngine.indexQuery(query);

        searchEngine.compareQueryToDocuments(indexedQuery);

    }
}
