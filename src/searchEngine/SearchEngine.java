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

        String documentA = "This is DSV homepage DSV is a joint department between SU and KTH We have many good students here";
        String documentB = "Hi This is Eriks homepage at DSV a joint department owned by SU and KTH I don't play football but I have a link to Nikos at DSV";
        String documentC = "This is studentexpedition at DSV We have nothing about football sorry But we have a link to Eriks page instead";
        String documentD = "Hi This is Nikos homepage at DSV I like DSV but even more I like football I Nikos am a big fan of football I play football all the time And I also have a Nikos' link to Eriks page";
        String documentE = "This is AIK website We all play football here We think football rocks And we have no links to anybody else we think we are best anyway";

        //String[] documents = {"Another document", "Not relevant", "A somewhat relevant document"};
        String[] documents = {documentA, documentB, documentC, documentD, documentE};
        index = new Index(documents);
        index.index();

    }

    private Set<Map.Entry<String, IndexEntry<String>>> indexQuery(String query) {

        Indexer indexer = new Indexer();
        return indexer.index("query", query);
        
    }
    private void normalize(Map<String, Double> relevanceByDocument) {

        for(Map.Entry<String, Double> documentRelevanceEntry : relevanceByDocument.entrySet()) {
            
            String documentId = (String) documentRelevanceEntry.getKey();
            Double documentRelevance = (Double) documentRelevanceEntry.getValue();

            normalizedSimilarity = documentRelevance / 

        }
        
    }

    private void calculateNormalizedSimilarity(Set<Map.Entry<String, IndexEntry<String>>> indexedQuery) {
 
        Map<String, Double> relevanceByDocument = new HashMap<String, Double>();

        for(Map.Entry<String, IndexEntry<String>> queryTermEntry : indexedQuery) {
            
            String queryTerm = (String) queryTermEntry.getKey();
            IndexEntry<String> indexedQueryTerm = (IndexEntry<String>) queryTermEntry.getValue();

            HashSet<IndexEntry<String>> documentsIncludingQueryTerm = index.get(queryTerm);

            for(IndexEntry<String> document : documentsIncludingQueryTerm) {

                double termWeight = document.getTermWeight() * indexedQueryTerm.getTermWeight();

                String documentId = document.getDocumentId();
                Double documentRelevance = relevanceByDocument.get(documentId);

                if(documentRelevance == null) {
                    relevanceByDocument.put(documentId, termWeight);
                } else {
                    relevanceByDocument.put(documentId, termWeight + documentRelevance);
                }

            }

        }

        normalize(relevanceByDocument);
        System.out.println(relevanceByDocument);
       
    }

    private void calculateSimilarity(Set<Map.Entry<String, IndexEntry<String>>> indexedQuery) {

        calculateNormalizedSimilarity(indexedQuery);
        
    }

    public static void main(String[] args) {
        
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.index();

        String query = "Nikos DSV";
        Set<Map.Entry<String, IndexEntry<String>>> indexedQuery = searchEngine.indexQuery(query);

        searchEngine.calculateSimilarity(indexedQuery);

    }
}
