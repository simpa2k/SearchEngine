package index;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import multiTreeMap.MultiTreeMap;

public class Index {

    private Set<Map.Entry<String, String>> documentsById;
    private Indexer indexer = new Indexer();

    MultiTreeMap<String, IndexEntry<String>> index = new MultiTreeMap<>();

    public Index(Set<Map.Entry<String, String>> documentsById) {
        this.documentsById = documentsById;
    }

    private void insertIntoIndex(Set<Map.Entry<String, IndexEntry<String>>> entrySet) {

        for(Map.Entry<String, IndexEntry<String>> entry : entrySet) {
            
            String term = (String) entry.getKey();
            IndexEntry<String> indexEntry = (IndexEntry<String>) entry.getValue();

            index.insert(term, indexEntry);

        }
    }

    public void index() {
        insertIntoIndex(indexer.index(documentsById));
    }

    public HashSet<IndexEntry<String>> get(String term) {
       return index.get(term); 
    }

    public double[] getDocumentTermVector(String documentId) {
        
        for(Map.Entry<String, IndexEntry<String>> entriesByTerm : index.entrySet()) {

            
        }
    }
}
