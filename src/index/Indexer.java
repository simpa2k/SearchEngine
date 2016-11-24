package index;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Indexer {

    public Set<Map.Entry<String, IndexEntry<String>>> index(String[] documents) {

        Set<Map.Entry<String, IndexEntry<String>>> indexedDocuments = new HashSet<>();

        for (int i = 0; i < documents.length; i++) {
            indexedDocuments.addAll(index("" + i, documents[i]));
        }

        return indexedDocuments;
    }

    public Set<Map.Entry<String, IndexEntry<String>>> index(String documentId, String document) {

            String[] terms = document.split(" ");
            int documentWordCount = terms.length;

            HashMap<String, IndexEntry<String>> indexedTerms = indexTerms(documentId, documentWordCount, terms);

            return indexedTerms.entrySet();
            
    }

    private HashMap<String, IndexEntry<String>> indexTerms(String documentId, int documentWordCount, String[] terms) {

        HashMap<String, IndexEntry<String>> indexedTerms = new HashMap<>();

        for(int i = 0; i < terms.length; i++) {
            
            String lowercaseTerm = terms[i].toLowerCase();
            IndexEntry<String> termIndex = indexedTerms.get(lowercaseTerm);

            if(termIndex == null) {

                IndexEntry<String> indexEntry = new IndexEntry<>(documentId, documentWordCount, 1, i);
                indexedTerms.put(lowercaseTerm, indexEntry);

            } else {
                
                double termWeight = termIndex.getTermWeight();
                termWeight++;
                termIndex.setTermWeight(termWeight);

            }
        }
        return indexedTerms;
    }
}
