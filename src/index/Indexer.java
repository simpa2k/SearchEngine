package index;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import multiTreeMap.MultiMap;

public class Indexer {

    public Set<Map.Entry<String, IndexEntry<String>>> index(Set<Map.Entry<String, String>> documentsById) {

        Set<Map.Entry<String, IndexEntry<String>>> indexedDocuments = new HashSet<>();

        for (Map.Entry<String, String> documentById : documentsById) {

            String documentId = documentById.getKey();
            String document = documentById.getValue();

            indexedDocuments.addAll(index(documentId, document));

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

        HashMap<String, IndexEntry<String>> entriesByTerm = new HashMap<>();
        MultiMap<String, IndexEntry<String>> entriesByDocument = new MultiMap<>();

        for(int i = 0; i < terms.length; i++) {
            
            String lowercaseTerm = terms[i].toLowerCase();
            IndexEntry<String> termIndex = entriesByTerm.get(lowercaseTerm);

            if(termIndex == null) {

                IndexEntry<String> indexEntry = new IndexEntry<>(documentId, documentWordCount, 1, i);
                entriesByTerm.put(lowercaseTerm, indexEntry);

            } else {
                
                double termWeight = termIndex.getTermWeight();
                termWeight++;
                termIndex.setTermWeight(termWeight);

            }
            entriesByDocument.put(documentId, termIndex);

        }

        return HashMap<String, IndexEntry<String>>[] {entriesByTerm, entriesByDocument.toHashMap()};
    }
}
