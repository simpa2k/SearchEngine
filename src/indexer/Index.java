package indexer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import multiTreeMap.MultiTreeMap;

public class Index {

    private String[] documents;
    MultiTreeMap<String, String, IndexEntry<String>> index = new MultiTreeMap<>();

    public Index(String[] documents) {
        this.documents = documents;
    }

    public HashMap<String, IndexEntry<String>> indexTerms(String documentId, String[] terms) {

        HashMap<String, IndexEntry<String>> indexedTerms = new HashMap<>();

        for(int i = 0; i < terms.length; i++) {
            
            IndexEntry<String> termIndex = indexedTerms.get(terms[i]);

            if(termIndex == null) {

                IndexEntry<String> indexEntry = new IndexEntry<String>(documentId, 1, i);
                indexedTerms.put(terms[i], indexEntry);

            } else {
                
                double termWeight = termIndex.getTermWeight();
                termWeight++;
                termIndex.setTermWeight(termWeight);

            }
            //index.insert(terms[i], "" + documentId, indexedTerms.get(terms[i]));
        }
        return indexedTerms;

    }

    private void insertIntoIndex(Set<Map.Entry<String, IndexEntry<String>>> entrySet) {

        for(Map.Entry<String, IndexEntry<String>> entry : entrySet) {
            
            String term = (String) entry.getKey();
            IndexEntry<String> indexEntry = (IndexEntry<String>) entry.getValue();

            index.insert(term, indexEntry.getDocumentId(), indexEntry);

        }
            
    }

    public void index() {

        for (int i = 0; i < documents.length; i++) {

            String[] terms = documents[i].split(" ");
            HashMap<String, IndexEntry<String>> indexedTerms = indexTerms("" + i, terms);

            insertIntoIndex(indexedTerms.entrySet());

        }
        System.out.println(index);
    }
}
