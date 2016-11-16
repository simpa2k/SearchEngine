package indexer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import multiTreeMap.MultiTreeMap;

public class Index {

    private String[] documents;
    MultiTreeMap<String, String, IndexEntry<String>> index = new MultiTreeMap<>();

    public Index(String[] documents) {
        this.documents = documents;
    }

    private void indexTerms(int documentId, String[] terms) {

        HashMap<String, IndexEntry<String>> indexedTerms = new HashMap<>();

        for(int i = 0; i < terms.length; i++) {
            
            IndexEntry<String> termIndex = indexedTerms.get(terms[i]);

            if(termIndex == null) {

                IndexEntry<String> indexEntry = new IndexEntry<String>("" + documentId, 1, i);
                indexedTerms.put(terms[i], indexEntry);

            } else {
                
                double termWeight = termIndex.getTermWeight();
                termWeight++;
                termIndex.setTermWeight(termWeight);

            }

            index.insert(terms[i], "" + documentId, indexedTerms.get(terms[i]));

        }
    }

    public void index() {

        for (int i = 0; i < documents.length; i++) {

            String[] terms = documents[i].split(" ");
            indexTerms(i, terms);

        }
        System.out.println(index);
    }
}
